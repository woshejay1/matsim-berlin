package org.matsim.olympiastadion_study.prepare.u2;

import org.matsim.api.core.v01.Id;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.vehicles.Vehicles;

import static org.matsim.olympiastadion_study.prepare.Utilities.createDepartures;
import static org.matsim.olympiastadion_study.prepare.Utilities.removeDeparturesWithinTimeRange;

public class U2GamedaySchedule {
    public static void prepare(TransitSchedule transitSchedule, Vehicles transitVehicles) {


        // prepare u2 gameday schedule
        TransitLine u2Transitline = transitSchedule.getTransitLines().get(Id.create("U2---17514_400", TransitLine.class));


        //Adjust Route 10 Ruhleben-Pankow
        TransitRoute u2GamedayRoute10 = u2Transitline.getRoutes().get(Id.create("U2---17514_400_10", TransitRoute.class));
        //remove Depature
        removeDeparturesWithinTimeRange(u2GamedayRoute10, 0 * 3600, 100 * 3600);
        //create Depature
        //4:23-6:33 interval: 10 min
        createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
                4 * 3600 + 23 * 60, 6 * 3600 + 33 * 60, 600,
                "950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 0);
        //6:42-9:33 interval: 9 min
        createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
                6 * 3600 + 42 * 60, 9 * 3600 + 33 * 60, 540,
                "950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 14);
        //9:43-12:03 interval: 10 min
        createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
                9 * 3600 + 43 * 60, 12 * 3600 + 3 * 60, 600,
                "950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 34);
        //12:11-14:00(After Game) interval: 8 min
        createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
                12 * 3600 + 11 * 60, 14 * 3600, 480,
                "950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 49);
        //14:03-18:33 interval: 9 min
        createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
                14 * 3600 + 3 * 60, 18 * 3600 + 33 * 60, 540,
                "950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 63);
        //18:43-25:43 interval: 10 min
        createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
                18 * 3600 + 43 * 60, 25 * 3600 + 43 * 60, 600,
                "950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 94);
        //25:58-30:00 interval: 15 min
        createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
                25 * 3600 + 58 * 60, 30 * 3600 + 0 * 60, 900,
                "950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 137);


        //Adjust Route 13 Theodor-Heuss-Platz-Pankow
        TransitRoute u2GamedayRoute13 = u2Transitline.getRoutes().get(Id.create("U2---17514_400_13", TransitRoute.class));
        //remove Depature
        removeDeparturesWithinTimeRange(u2GamedayRoute13, 0 * 3600, 30 * 3600);
        //create Depature
        //6:04-6:34 interval: 10 min
        createDepartures(u2GamedayRoute13, transitSchedule, transitVehicles,
                6 * 3600 + 4 * 60, 6 * 3600 + 34 * 60, 600,
                "950831_", "pt_U2---17514_400_13_", "U-Bahn_veh_type", 0);
        //6:43-9:34 interval: 9 min
        createDepartures(u2GamedayRoute13, transitSchedule, transitVehicles,
                6 * 3600 + 43 * 60, 9 * 3600 + 34 * 60, 540,
                "950831_", "pt_U2---17514_400_13_", "U-Bahn_veh_type", 4);
        //9:44-12:00 interval: 10 min
        createDepartures(u2GamedayRoute13, transitSchedule, transitVehicles,
                9 * 3600 + 44 * 60, 12 * 3600 + 0 * 60, 600,
                "950831_", "pt_U2---17514_400_13_", "U-Bahn_veh_type", 24);
        //14:04-18:44 interval: 9 min
        createDepartures(u2GamedayRoute13, transitSchedule, transitVehicles,
                14 * 3600 + 04 * 60, 18 * 3600 + 34 * 60, 540,
                "950831_", "pt_U2---17514_400_13_", "U-Bahn_veh_type", 38);
        //18:44-22:34 interval: 10 min
        createDepartures(u2GamedayRoute13, transitSchedule, transitVehicles,
                18 * 3600 + 44 * 60, 22 * 3600 + 34 * 60, 600,
                "950831_", "pt_U2---17514_400_13_", "U-Bahn_veh_type", 69);

        //Adjust Route 5 Olympiastadion-Pankow
        TransitRoute u2GamedayRoute05 = u2Transitline.getRoutes().get(Id.create("U2---17514_400_5", TransitRoute.class));
        //remove Depature
        removeDeparturesWithinTimeRange(u2GamedayRoute05, 0 * 3600, 30 * 3600);
        //create Depature
        //12:05-14:00 interval: 10 min
        createDepartures(u2GamedayRoute05, transitSchedule, transitVehicles,
                12 * 3600 + 7 * 60, 14 * 3600, 600,
                "u2Special_", "pt_U2---17514_400_5_", "U-Bahn_veh_type", 0);


    }
}
