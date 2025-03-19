package org.matsim.olympiastadion_study.prepare;

import org.locationtech.jts.geom.Geometry;
import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.population.*;
import org.matsim.application.MATSimAppCommand;
import org.matsim.application.options.ShpOptions;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.population.PopulationUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.geotools.MGC;
import org.opengis.feature.simple.SimpleFeature;
import picocli.CommandLine;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

public class PrepareHomeGoingPlans implements MATSimAppCommand {
    @CommandLine.Option(names = "--plans", description = "path to input plans (original plans)", required = true)
    private String inputPlans;

    @CommandLine.Option(names = "--num-of-fans", description = "number of football fans", defaultValue = "50000")
    private int numOfFans;

    @CommandLine.Option(names = "--match-end-time", description = "ending time of the match", defaultValue = "79200")
    private double matchEndTime;

    @CommandLine.Option(names = "--distribution-shp", description = "path to home location distribution shp file", required = true)
    private String distributionShpPath;

    @CommandLine.Option(names = "--residential-area-shp", description = "path to residential area shp file", defaultValue = "")
    private String residentialAreaShpPath;

    @CommandLine.Option(names = "--output", description = "path to output plans", required = true)
    private String outputPlansPath;

    @CommandLine.Option(names = "--seed", description = "path to output plans", defaultValue = "1")
    private long seed;

    // TODO read in distance distribution and adjust plans

    // TODO consider using a list of start location (to avoid car traffic congestion)
    private static final Coord OLYMPIASTADION_COORD = new Coord(4584569.8, 5820911.8);

    public static void main(String[] args) {
        new PrepareHomeGoingPlans().execute(args);
    }

    @Override
    public Integer call() throws Exception {
        Scenario scenario = ScenarioUtils.loadScenario(ConfigUtils.createConfig());
        Population population = scenario.getPopulation();
        PopulationFactory populationFactory = population.getFactory();

        Population berlinPopulation = PopulationUtils.readPopulation(inputPlans);
        Set<Coord> potentialHomeCoords = new HashSet<>();
        for (Person person : berlinPopulation.getPersons().values()) {
            Plan selectedPlan = person.getSelectedPlan();
            for (PlanElement planElement : selectedPlan.getPlanElements()) {
                if (planElement instanceof Activity && ((Activity) planElement).getType().contains("home")) {
                    Coord homeCoord = ((Activity) planElement).getCoord();
                    potentialHomeCoords.add(homeCoord);
                }
            }
        }

        // read in distribution
        ShpOptions distributionShp = new ShpOptions(Path.of(distributionShpPath), "EPSG:31468", StandardCharsets.UTF_8);
        List<SimpleFeature> features = distributionShp.readFeatures();
        Map<String, List<Coord>> zoneCoordsMap = new HashMap<>();
        List<String> zonesPool = new ArrayList<>();

        for (SimpleFeature feature : features) {
            String zoneName = feature.getAttribute("Gemeinde_n").toString();
            long fanNumber = (long) feature.getAttribute("Fansnumber");
            Geometry zoneGeometry = (Geometry) feature.getDefaultGeometry();

            Set<Coord> potentialHomeLocationsInZone = new HashSet<>();
            for (Coord potentialHomeCoord : potentialHomeCoords) {
                if (MGC.coord2Point(potentialHomeCoord).within(zoneGeometry)){
                    potentialHomeLocationsInZone.add(potentialHomeCoord);
                }
            }

            // extract links in the zone
            zoneCoordsMap.put(zoneName, new ArrayList<>(potentialHomeLocationsInZone));

            // add the zone x times into the list (will be used later for density draw)
            for (int i = 0; i < fanNumber; i++) {
                zonesPool.add(zoneName);
            }
        }
        Collections.shuffle(zonesPool, new Random(seed));

        for (String zoneName : zoneCoordsMap.keySet()) {
            if (!zoneCoordsMap.get(zoneName).isEmpty()) {
                System.out.println(zoneName + " has " + zoneCoordsMap.get(zoneName).size() + " potential coords for " +
                        "home activity to choose from");
            } else {
                System.err.println(zoneName + " has no home activity!!!");
            }

        }

        // generate plans
        Random random = new Random(seed);
        for (int i = 0; i < numOfFans; i++) {
            Person person = populationFactory.createPerson(Id.createPersonId("football_fan_" + i));

            double departureTime = random.nextInt(1800) + matchEndTime;
            String homeZone = zonesPool.get(random.nextInt(zonesPool.size()));
            List<Coord> potentialHomeCoordsInZone = zoneCoordsMap.get(homeZone);
            Coord homeCoord = potentialHomeCoordsInZone.get(random.nextInt(potentialHomeCoordsInZone.size()));

            Activity footballGameActivity = populationFactory.createActivityFromCoord("dummy", OLYMPIASTADION_COORD);
            footballGameActivity.setEndTime(departureTime);
            Leg leg = populationFactory.createLeg(TransportMode.pt);
            Activity home = populationFactory.createActivityFromCoord("dummy", homeCoord);
            Plan plan = populationFactory.createPlan();

            plan.addActivity(footballGameActivity);
            plan.addLeg(leg);
            plan.addActivity(home);
            person.addPlan(plan);

            population.addPerson(person);
        }

        new PopulationWriter(population).write(outputPlansPath);

        return 0;
    }
}
