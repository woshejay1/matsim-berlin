package org.matsim.olympiastadion_study.prepare;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.olympiastadion_study.prepare.s3.*;
import org.matsim.olympiastadion_study.prepare.s5.S5GamedaySchedule;
import org.matsim.olympiastadion_study.prepare.u2.U2GamedaySchedule;
import org.matsim.olympiastadion_study.prepare.u2.U2SpecialSchedule;
import org.matsim.pt.transitSchedule.api.TransitSchedule;
import org.matsim.pt.transitSchedule.api.TransitScheduleWriter;
import org.matsim.vehicles.MatsimVehicleWriter;
import org.matsim.vehicles.Vehicles;

public class PrepareBaseSchedule {
    public static void main(String[] args) {
        Config config = ConfigUtils.loadConfig("G:/matsim-berlin/scenarios/berlin-v5.5-10pct/input/berlin-v5.5-10pct.config.xml");
        config.plans().setInputFile("G:/Masterarbeit/Plan/2.0/50000-fans-12pm-new.plans.xml.gz");
        config.transit().setTransitScheduleFile("G:/Masterarbeit/Version5.6.1/intermediate_transitSchedule.xml");
//        config.transit().setTransitScheduleFile("https://svn.vsp.tu-berlin.de/repos/public-svn/matsim/scenarios/countries/de/berlin/berlin-v5.5-10pct/input/berlin-v5.5-transit-schedule.xml.gz");
        config.transit().setVehiclesFile("https://svn.vsp.tu-berlin.de/repos/public-svn/matsim/scenarios/countries/de/berlin/berlin-v5.5-10pct/input/berlin-v5.5-transit-vehicles.xml.gz");
        config.network().setInputFile("G:/Masterarbeit/Version5.6.1/updated_network.xml");
//        config.network().setInputFile("https://svn.vsp.tu-berlin.de/repos/public-svn/matsim/scenarios/countries/de/berlin/berlin-v5.5-10pct/input/berlin-v5.5-network.xml.gz");
        Scenario scenario = ScenarioUtils.loadScenario(config);
        TransitSchedule transitSchedule = scenario.getTransitSchedule();
        Vehicles transitVehicles = scenario.getTransitVehicles();


        //GamedayBase(S3 Special Olympiastadion-Charlottenburg 12:03-14:00 interval:10min/
        //            S5 Olympiastadion-Strausburg 9:21-24:01 interval:20min/
        //            S5 Olympiastadion-Mahlsdorf 9:11-24:01 interval:20min/
        //            U2 12:11-14:00 interval:8min
        //            U2 Special 12:07-13:00 interval:8min
//        S5GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
//        S3GamedayBaseSchedule.prepare(scenario, transitSchedule, transitVehicles);
//        U2GamedaySchedule.prepare(transitSchedule, transitVehicles);


//      GamedayPlan1 (S3 Special Olympiastadion-Charlottenburg 12:03-14:00 interval:10min/
//                    S5 Olympiastadion-Strausburg 9:21-24:01 interval:20min/
//                    S5 Olympiastadion-Mahlsdorf 9:11-24:01 interval:20min/)
//        S3GamedayBaseSchedule.prepare(scenario,transitSchedule, transitVehicles);
//        S5GamedaySchedule.prepare(scenario,transitSchedule, transitVehicles);
//

        //GamedayPlan2 (S3 Special Olympiastadion-Ostbahnhof 12:13-14:00 interval:10min/
        //              S5 Olympiastadion-Strausburg 9:21-24:01 interval:20min/
        //              S5 Olympiastadion-Mahlsdorf 9:11-24:01 interval:20min/ )
//                      U2 12:11-14:00 interval:8min
//                      U2 Special 12:09-13:00 interval:8min)
//        S5GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
//        S3GamedayOstbahnhofSchedule.prepare(scenario, transitSchedule, transitVehicles);
//        U2GamedaySchedule.prepare(transitSchedule, transitVehicles);


        //GamedayPlan3 (S3 Special Olympiastadion-Westkreuz 12:03-14:00 interval:10min
        //              S5 Olympiastadion-Strausburg 9:21-24:01 interval:20min/
        //              S5 Olympiastadion-Mahlsdorf 9:11-24:01 interval:20min/
        //              U2 12:11-14:00 interval:8min
        //              U2 Special 12:09-13:00 interval:8min)
//        S5GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
//        S3GamedayWestkreuzSchedule.prepare(scenario, transitSchedule, transitVehicles);
//        U2GamedaySchedule.prepare(transitSchedule, transitVehicles);


        //GamedayPlan4 (S3 Special Olympiastadion-Charlottenburg 12:03-14:00 interval:10min/
        //              S5 Olympiastadion-Strausburg 9:21-24:01 interval:20min/
        //              S5 Olympiastadion-Mahlsdorf 9:11-24:01 interval:20min/
//                      U2 12:11-14:00 interval:8min
        //              U2 Special Olympiastadion-DeutscheOper 12:09-13:00 interval:8min
        S5GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
        S3GamedayBaseSchedule.prepare(scenario, transitSchedule, transitVehicles);
        U2SpecialSchedule.prepare(scenario, transitSchedule, transitVehicles);




        String outputTransitScheduleFilePath = "G:/Masterarbeit/Version5.6.1/base_transitSchedule.xml";
        new TransitScheduleWriter(transitSchedule).writeFile(outputTransitScheduleFilePath);
        String outputTransitVehiclesFilePath = "G:/Masterarbeit/Version5.6.1/base_transitVehicles.xml";
        new MatsimVehicleWriter(transitVehicles).writeFile(outputTransitVehiclesFilePath);

    }

}
