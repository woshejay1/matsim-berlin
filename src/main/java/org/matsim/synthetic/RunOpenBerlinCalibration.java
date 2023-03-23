package org.matsim.synthetic;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.application.MATSimAppCommand;
import org.matsim.application.MATSimApplication;
import org.matsim.application.options.SampleOptions;
import org.matsim.application.prepare.CreateLandUseShp;
import org.matsim.application.prepare.network.CleanNetwork;
import org.matsim.application.prepare.network.CreateNetworkFromSumo;
import org.matsim.application.prepare.population.CleanPopulation;
import org.matsim.application.prepare.population.DownSamplePopulation;
import org.matsim.application.prepare.population.MergePopulations;
import org.matsim.application.prepare.population.SplitActivityTypesDuration;
import org.matsim.application.prepare.pt.CreateTransitScheduleFromGtfs;
import org.matsim.contrib.cadyts.car.CadytsCarModule;
import org.matsim.contrib.cadyts.car.CadytsContext;
import org.matsim.contrib.cadyts.general.CadytsScoring;
import org.matsim.contrib.locationchoice.frozenepsilons.FrozenTastes;
import org.matsim.contrib.locationchoice.frozenepsilons.FrozenTastesConfigGroup;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.StrategyConfigGroup;
import org.matsim.core.config.groups.VspExperimentalConfigGroup;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.replanning.choosers.ForceInnovationStrategyChooser;
import org.matsim.core.replanning.choosers.StrategyChooser;
import org.matsim.core.replanning.strategies.DefaultPlanStrategiesModule;
import org.matsim.core.router.AnalysisMainModeIdentifier;
import org.matsim.core.router.RoutingModeMainModeIdentifier;
import org.matsim.core.scoring.ScoringFunction;
import org.matsim.core.scoring.ScoringFunctionFactory;
import org.matsim.core.scoring.SumScoringFunction;
import org.matsim.core.scoring.functions.ScoringParameters;
import org.matsim.core.scoring.functions.ScoringParametersForPerson;
import org.matsim.prepare.berlinCounts.CreateCountsFromOpenData;
import org.matsim.prepare.berlinCounts.CreateCountsFromVMZ;
import org.matsim.run.Activities;
import org.matsim.run.RunOpenBerlinScenario;
import org.matsim.synthetic.download.DownloadCommuterStatistic;
import picocli.CommandLine;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This scenario class is used for run a MATSim scenario in various stages of the calibration process.
 */
@CommandLine.Command(header = ":: Open Berlin Calibration ::", version = RunOpenBerlinScenario.VERSION, mixinStandardHelpOptions = true)
@MATSimApplication.Prepare({
		CreateLandUseShp.class, CreateBerlinPopulation.class, CreateBrandenburgPopulation.class, MergePopulations.class,
		LookupRegioStaR.class, ExtractFacilityShp.class, DownSamplePopulation.class, DownloadCommuterStatistic.class,
		AssignCommuters.class, RunActitopp.class, CreateNetworkFromSumo.class, CreateTransitScheduleFromGtfs.class,
		CleanNetwork.class, CreateMATSimFacilities.class, InitLocationChoice.class, FilterRelevantAgents.class,
		CreateCountsFromOpenData.class, CreateCountsFromVMZ.class, ReprojectNetwork.class, RunActivitySampling.class,
		MergePlans.class, SplitActivityTypesDuration.class, CleanPopulation.class, CleanAttributes.class
})
public class RunOpenBerlinCalibration extends MATSimApplication {

	private static final Logger log = LogManager.getLogger(RunOpenBerlinCalibration.class);

	/**
	 * Flexible activities, which need to be known for location choice and during generation.
	 * A day can not end on a flexible activity.
	 */
	static final Set<String> FLEXIBLE_ACTS = Set.of("shop_daily", "shop_other", "leisure", "dining");

	@CommandLine.Option(names = "--mode", description = "Calibration mode that should be run.")
	private CalibrationMode mode;

	@CommandLine.Option(names = "--weight", description = "Strategy weight for calibration config.", defaultValue = "1")
	private double weight;

	@CommandLine.Option(names = "--population", description = "Path to population")
	private Path populationPath;

	@CommandLine.Mixin
	private final SampleOptions sample = new SampleOptions(100, 25, 10, 1);

	@CommandLine.Option(names = "--scale-factor", description = "Scale factor for counts.", defaultValue = "1")
	private double scaleFactor;

	public static void main(String[] args) {
		MATSimApplication.run(RunOpenBerlinCalibration.class, args);
	}

	public RunOpenBerlinCalibration() {
		super("input/v6.0/berlin-v6.0-base-calib.config.xml");
	}

	@Override
	protected Config prepareConfig(Config config) {

		if (populationPath == null) {
			throw new IllegalArgumentException("Population path is required [--population]");
		}

		config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);

		log.info("Running {} calibration {}", mode, populationPath);

		config.plans().setInputFile(populationPath.toString());
		config.controler().setRunId(mode.toString());

		// Location choice does not work with the split types
		Activities.addScoringParams(config, mode != CalibrationMode.locationChoice);

		if (sample.isSet()) {
			config.qsim().setFlowCapFactor(sample.getSize() / 100d);
			config.qsim().setStorageCapFactor(sample.getSize() / 100d);

			config.plans().setInputFile(sample.adjustName(config.plans().getInputFile()));
		}


		// Required for all calibration strategies
		config.strategy().addStrategySettings(
				new StrategyConfigGroup.StrategySettings()
						.setStrategyName(DefaultPlanStrategiesModule.DefaultSelector.ChangeExpBeta)
						.setWeight(1.0)
						.setSubpopulation("person")
		);

		if (mode == null)
			throw new IllegalArgumentException("Calibration mode [--mode} not set!");

		if (mode == CalibrationMode.locationChoice) {

			// Flow capacities are increased for the calibration
			config.qsim().setFlowCapFactor(config.qsim().getFlowCapFactor() * 4);
			config.qsim().setStorageCapFactor(config.qsim().getStorageCapFactor() * 4);

			config.strategy().addStrategySettings(new StrategyConfigGroup.StrategySettings()
					.setStrategyName(FrozenTastes.LOCATION_CHOICE_PLAN_STRATEGY)
					.setWeight(weight)
					.setSubpopulation("person")
			);

			config.strategy().setFractionOfIterationsToDisableInnovation(0.8);
			config.planCalcScore().setFractionOfIterationsToStartScoreMSA(0.8);

			FrozenTastesConfigGroup dccg = ConfigUtils.addOrGetModule(config, FrozenTastesConfigGroup.class);

			dccg.setEpsilonScaleFactors(FLEXIBLE_ACTS.stream().map(s -> "1.0").collect(Collectors.joining(",")));
			dccg.setAlgorithm(FrozenTastesConfigGroup.Algotype.bestResponse);
			dccg.setFlexibleTypes(String.join(",", FLEXIBLE_ACTS));
			dccg.setTravelTimeApproximationLevel(FrozenTastesConfigGroup.ApproximationLevel.localRouting);
			dccg.setRandomSeed(2);
			dccg.setDestinationSamplePercent(25);

		} else if (mode == CalibrationMode.cadyts) {

			config.strategy().addStrategySettings(new StrategyConfigGroup.StrategySettings()
					.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.ReRoute)
					.setWeight(weight)
					.setSubpopulation("person")
			);

			config.counts().setInputFile("./berlin-v6.0-counts-car-vmz.xml.gz");

			config.controler().setRunId("cadyts");
			config.controler().setOutputDirectory("./output/cadyts-" + scaleFactor);

			// Counts are scaled with sample size and reduced for missing commercial traffic
			config.counts().setCountsScaleFactor(scaleFactor * sample.getSize() / 100d);

			// No innovation switch-off needed
			config.planCalcScore().setFractionOfIterationsToStartScoreMSA(1.0);
			config.strategy().setFractionOfIterationsToDisableInnovation(1.0);
			config.vspExperimental().setVspDefaultsCheckingLevel(VspExperimentalConfigGroup.VspDefaultsCheckingLevel.ignore);

		} else if (mode == CalibrationMode.routeChoice) {

			config.strategy().addStrategySettings(new StrategyConfigGroup.StrategySettings()
					.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.ReRoute)
					.setWeight(weight)
					.setSubpopulation("person")
			);

			config.strategy().addStrategySettings(new StrategyConfigGroup.StrategySettings()
					.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.TimeAllocationMutator)
					.setWeight(weight)
					.setSubpopulation("person")
			);

		} else
			throw new IllegalStateException("Mode not implemented:" + mode);

		return config;
	}

	@Override
	protected void prepareScenario(Scenario scenario) {

	}

	@Override
	protected void prepareControler(Controler controler) {

		if (mode == CalibrationMode.locationChoice) {
			FrozenTastes.configure(controler);

			controler.addOverridingModule(new AbstractModule() {
				@Override
				public void install() {
					binder().bind(new TypeLiteral<StrategyChooser<Plan, Person>>() {}).toInstance(new ForceInnovationStrategyChooser<>(5, ForceInnovationStrategyChooser.Permute.no));
				}
			});

		} else if (mode == CalibrationMode.cadyts) {

			controler.addOverridingModule(new CadytsCarModule());
			controler.setScoringFunctionFactory(new ScoringFunctionFactory() {
				@Inject
				private CadytsContext cadytsContext;
				@Inject
				ScoringParametersForPerson parameters;

				@Override
				public ScoringFunction createNewScoringFunction(Person person) {
					SumScoringFunction sumScoringFunction = new SumScoringFunction();

					Config config = controler.getConfig();

					final ScoringParameters params = parameters.getScoringParameters(person);

					// Only cadyts is scored
//					sumScoringFunction.addScoringFunction(new CharyparNagelLegScoring(params, controler.getScenario().getNetwork()));
//					sumScoringFunction.addScoringFunction(new CharyparNagelActivityScoring(params));
//					sumScoringFunction.addScoringFunction(new CharyparNagelAgentStuckScoring(params));

					final CadytsScoring<Link> scoringFunction = new CadytsScoring<>(person.getSelectedPlan(), config, cadytsContext);
					scoringFunction.setWeightOfCadytsCorrection(weight * config.planCalcScore().getBrainExpBeta());
					sumScoringFunction.addScoringFunction(scoringFunction);

					return sumScoringFunction;
				}
			});

		}

		controler.addOverridingModule(new AbstractModule() {
			@Override
			public void install() {
				bind(AnalysisMainModeIdentifier.class).to(RoutingModeMainModeIdentifier.class);
			}
		});
	}

	@Override
	protected List<MATSimAppCommand> preparePostProcessing(Path outputFolder, String runId) {
		return List.of(
				new CleanPopulation().withArgs(
						"--plans", outputFolder.resolve(runId + ".output_plans.xml.gz").toString(),
						"--output", outputFolder.resolve(runId + ".output_selected_plans.xml.gz").toString(),
						"--remove-unselected-plans"
				)
		);
	}

	/**
	 * Different calibration stages.
	 */
	public enum CalibrationMode {
		locationChoice,
		cadyts,
		routeChoice
	}

}