package org.matsim.olympiastadion_study.run;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;
import org.matsim.core.config.groups.StrategyConfigGroup;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.replanning.strategies.DefaultPlanStrategiesModule;
import org.matsim.core.scenario.ScenarioUtils;

public class RunWithTestingPlans {
	public static void main(String[] args) {
		Config config = ConfigUtils.createConfig();
		//Base
//		config.network().setInputFile("https://svn.vsp.tu-berlin.de/repos/public-svn/matsim/scenarios/countries/de/berlin/berlin-v5.5-10pct/input/berlin-v5.5-network.xml.gz");
//		config.transit().setTransitScheduleFile("https://svn.vsp.tu-berlin.de/repos/public-svn/matsim/scenarios/countries/de/berlin/berlin-v5.5-10pct/input/berlin-v5.5-transit-schedule.xml.gz");
//		config.transit().setVehiclesFile("https://svn.vsp.tu-berlin.de/repos/public-svn/matsim/scenarios/countries/de/berlin/berlin-v5.5-10pct/input/berlin-v5.5-transit-vehicles.xml.gz");
		//Plan
		config.network().setInputFile("G:/Masterarbeit/Version5.6.1/updated_network.xml");
		config.transit().setTransitScheduleFile("G:/Masterarbeit/Version5.6.1/base_transitSchedule.xml");
		config.transit().setVehiclesFile("G:/Masterarbeit/Version5.6.1/base_transitVehicles.xml");
		config.plans().setInputFile("G:/Masterarbeit/Plan/testing-50000-fans.plans.xml.gz");
		config.transit().setUseTransit(true);

		config.qsim().setFlowCapFactor(1.0);
		config.qsim().setStorageCapFactor(1.0);

		PlanCalcScoreConfigGroup.ActivityParams dummyAct = new PlanCalcScoreConfigGroup.ActivityParams("dummy");
		dummyAct.setTypicalDuration(3600);
		config.planCalcScore().addActivityParams(dummyAct);

		config.global().setCoordinateSystem("EPSG:31468");

		config.strategy().addStrategySettings(
			new StrategyConfigGroup.StrategySettings()
				.setStrategyName(DefaultPlanStrategiesModule.DefaultSelector.ChangeExpBeta)
				.setWeight(0.9)
		);

		config.strategy().addStrategySettings(
			new StrategyConfigGroup.StrategySettings()
				.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.ReRoute)
				.setWeight(0.1)
		);

		config.strategy().addStrategySettings(
			new StrategyConfigGroup.StrategySettings()
				.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.TimeAllocationMutator)
				.setWeight(0)
		);

		config.strategy().addStrategySettings(
			new StrategyConfigGroup.StrategySettings()
				.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.ChangeSingleTripMode)
				.setWeight(0)
		);

		config.controler().setLastIteration(1);
		config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.overwriteExistingFiles);
		config.controler().setOutputDirectory("G:/Masterarbeit/Output/Testing/dummy-test-5.6.1/50000fans/gameday");

		Scenario scenario = ScenarioUtils.loadScenario(config);
		Controler controler = new Controler(scenario);
		controler.run();
	}
}
