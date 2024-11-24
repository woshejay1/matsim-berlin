package org.matsim.olympiastadion_study.prepare;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.olympiastadion_study.prepare.s3.S3GamedaySchedule;
import org.matsim.olympiastadion_study.prepare.s5.S5GamedaySchedule;
import org.matsim.olympiastadion_study.prepare.s9.S9GamedaySchedule;
import org.matsim.olympiastadion_study.prepare.u2.U2GamedaySchedule;
import org.matsim.pt.transitSchedule.api.TransitSchedule;
import org.matsim.pt.transitSchedule.api.TransitScheduleWriter;
import org.matsim.vehicles.MatsimVehicleWriter;
import org.matsim.vehicles.Vehicles;

public class PrepareBaseSchedule {
    public static void main(String[] args) {
        Config config = ConfigUtils.loadConfig("G:/matsim-berlin/scenarios/berlin-v5.5-10pct/input/berlin-v5.5-10pct.config.xml");
        config.plans().setInputFile("G:/Masterarbeit/Plan/3.0/testing-50000-fans-12pm.plans.xml.gz");
        config.transit().setTransitScheduleFile("G:/Masterarbeit/Version5.6.1/intermediate_transitSchedule.xml");
        config.transit().setVehiclesFile("https://svn.vsp.tu-berlin.de/repos/public-svn/matsim/scenarios/countries/de/berlin/berlin-v5.5-10pct/input/berlin-v5.5-transit-vehicles.xml.gz");
        config.network().setInputFile("G:/Masterarbeit/Version5.6.1/updated_network.xml");
        Scenario scenario = ScenarioUtils.loadScenario(config);
        TransitSchedule transitSchedule = scenario.getTransitSchedule();
        Vehicles transitVehicles = scenario.getTransitVehicles();

        U2GamedaySchedule.prepare(transitSchedule, transitVehicles);
        S3GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
        S5GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
        S9GamedaySchedule.prepare(transitSchedule, transitVehicles);


        String outputTransitScheduleFilePath = "G:/Masterarbeit/Version5.6.1/base_transitSchedule.xml";
        new TransitScheduleWriter(transitSchedule).writeFile(outputTransitScheduleFilePath);
        String outputTransitVehiclesFilePath = "G:/Masterarbeit/Version5.6.1/base_transitVehicles.xml";
        new MatsimVehicleWriter(transitVehicles).writeFile(outputTransitVehiclesFilePath);

    }

}
