package org.matsim.olympiastadion_study.prepare;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.olympiastadion_study.prepare.s3.*;
import org.matsim.olympiastadion_study.prepare.s5.S5GamedaySchedule;
import org.matsim.olympiastadion_study.prepare.s9.S9GamedayPlan1Schedule;
import org.matsim.olympiastadion_study.prepare.u2.U2GamedaySchedule;
import org.matsim.olympiastadion_study.prepare.u2.U2GamedaySchedule3min;
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
        //            U2 Special 12:07-14:00 interval:8min
//        S5GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
//        S3GamedayBaseSchedule.prepare(scenario, transitSchedule, transitVehicles);
//        U2GamedaySchedule.prepare(transitSchedule, transitVehicles);


//        //GamedayPlan1 (S3 10min 12:13-14:13/ S9 10min between 12:08-14:18/U2 5min)
//        U2GamedaySchedule.prepare(transitSchedule, transitVehicles);
//        S3GamedayPlan1Schedule.prepare(transitSchedule, transitVehicles);
//        S9GamedayPlan1Schedule.prepare(transitSchedule, transitVehicles);

        //GamedayPlan2 (S3 Special Olympiastadion-Ostbahnhof 12:13-14:00 interval:10min/
        //              S5 Olympiastadion-Strausburg 9:21-24:01 interval:20min/
        //              S5 Olympiastadion-Mahlsdorf 9:11-24:01 interval:20min/ )
//                      U2 Olympiastadion-Pankow 12:05-14:00 interval:10min)
        S5GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
        S3GamedayOstbahnhofSchedule.prepare(scenario, transitSchedule, transitVehicles);
        U2GamedaySchedule.prepare(transitSchedule, transitVehicles);

        //GamedayPlan3 (U2 3min between 12:13-13:13)
//        U2GamedaySchedule3min.prepare(transitSchedule, transitVehicles);
//        S3GamedayBaseSchedule.prepare(transitSchedule, transitVehicles);

        //GamedayPlan4 (U2 3min/S3 10min 12:13-14:13/ S9 10min between 12:08-14:18)
//        U2GamedaySchedule3min.prepare(transitSchedule, transitVehicles);
//        S3GamedayPlan1Schedule.prepare(transitSchedule, transitVehicles);
//        S9GamedayPlan1Schedule.prepare(transitSchedule, transitVehicles);

        //GamedayPlan5 (U2 3min/S3 Special Olympiastadion-Ostbahnhof interval:10min between 12-14)
//        U2GamedaySchedule3min.prepare(transitSchedule, transitVehicles);
//        S3GamedayPlan2Schedule.prepare(scenario, transitSchedule, transitVehicles);

        //GamedayPlan6 (S3 Special Olympiastadion-Warschauer 12:03-14:00 interval:10min
        //              S5 Olympiastadion-Strausburg 9:21-24:01 interval:20min/
              //        S5 Olympiastadion-Mahlsdorf 9:11-24:01 interval:20min/)
//        S5GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
//        S3GamedayWarschauerSchedule.prepare(scenario, transitSchedule, transitVehicles);

        //GamedayPlan7 (S3 Special Olympiastadion-Westkreuz 12:03-14:00 interval:10min
        //              S5 Olympiastadion-Strausburg 9:21-24:01 interval:20min/
        //        S5 Olympiastadion-Mahlsdorf 9:11-24:01 interval:20min/
        //        U2 Olympiastadion-Pankow 12:05-14:00 10 min)
//        S5GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
//        S3GamedayWestkreuzSchedule.prepare(scenario, transitSchedule, transitVehicles);
//        U2GamedaySchedule.prepare(transitSchedule, transitVehicles);
//

        //GamedayPlan8 (S3 Special charlottenburg interval:10min/
        //              S5 Olympiastadion-Strausburg 9:21-24:01 interval:20min/
        //              S5 Olympiastadion-Mahlsdorf 9:11-24:01 interval:20min/
        //              U2 3min 12:13-13:13)
//        U2GamedaySchedule3min.prepare(transitSchedule, transitVehicles);
//        S5GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
//        S3GamedayBase1Schedule.prepare(scenario, transitSchedule, transitVehicles);

        //GamedayPlan9 (S3 Special Olympiastadion-Westkreuz 12:03-14:00 interval:10min
        //              S5 Olympiastadion-Strausburg 9:21-24:01 interval:20min/
        //              S5 Olympiastadion-Mahlsdorf 9:11-24:01 interval:20min/
        //              U2 3min 12:13-13:13)
//        S5GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
//        S3GamedayWestkreuzSchedule.prepare(scenario, transitSchedule, transitVehicles);
//        U2GamedaySchedule3min.prepare(transitSchedule, transitVehicles);

        //GamedayPlan10 (S3 Special Olympiastadion-Ostbahnhof 12:03-14:00 interval:10min
        //              S5 Olympiastadion-Strausburg 9:21-24:01 interval:20min/
        //        S5 Olympiastadion-Mahlsdorf 9:11-24:01 interval:20min/
        //        U2 3min 12:13-13:13)
//        S5GamedaySchedule.prepare(scenario, transitSchedule, transitVehicles);
//        S3GamedayOstbahnhofSchedule.prepare(scenario, transitSchedule, transitVehicles);
//        U2GamedaySchedule3min.prepare(transitSchedule, transitVehicles);

        //GamedayPlan11 (S3 Special Olympiastadion-Charlottenburg 12:03-14:00 interval:10min/
        //               U2 3 MIN 12:13-13:13

//        S3GamedayBaseSchedule.prepare(scenario, transitSchedule, transitVehicles);
//        U2GamedaySchedule3min.prepare(transitSchedule, transitVehicles);

        //GamedayPlan12 (S3 Special Olympiastadion-Westkreuz 12:03-14:00 interval:10min/
        //               U2 3 MIN 12:13-13:13

//        S3GamedayWestkreuzSchedule.prepare(scenario, transitSchedule, transitVehicles);
//        U2GamedaySchedule3min.prepare(transitSchedule, transitVehicles);

        //GamedayPlan13 (S3 Special Olympiastadion-Ostbahnhof 12:03-13:00 interval:10min/
        // U2 Special Olympiastadion-DeutscheOper 12:08-14:00 interval:10min

//        U2SpecialSchedule.prepare(scenario, transitSchedule, transitVehicles);
//        S3GamedayOstbahnhofSchedule.prepare(scenario, transitSchedule, transitVehicles);



        String outputTransitScheduleFilePath = "G:/Masterarbeit/Version5.6.1/base_transitSchedule.xml";
        new TransitScheduleWriter(transitSchedule).writeFile(outputTransitScheduleFilePath);
        String outputTransitVehiclesFilePath = "G:/Masterarbeit/Version5.6.1/base_transitVehicles.xml";
        new MatsimVehicleWriter(transitVehicles).writeFile(outputTransitVehiclesFilePath);

    }

}
