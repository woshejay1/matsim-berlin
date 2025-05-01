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
        removeDeparturesWithinTimeRange(u2GamedayRoute10, 12 * 3600, 14 * 3600);
        //create Depature
        //12:03:30-14:00 interval: 8 min
        createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
                12 * 3600 + 03 * 60 + 30 , 14 * 3600, 480,
                "950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 0);


        //Adjust Route 13 Theodor-Heuss-Platz-Pankow
        TransitRoute u2GamedayRoute13 = u2Transitline.getRoutes().get(Id.create("U2---17514_400_13", TransitRoute.class));
        //remove Depature
        removeDeparturesWithinTimeRange(u2GamedayRoute13, 12 * 3600, 15 * 3600);
        //create Depature
        //13:01-15:01 interval: 8 min
        createDepartures(u2GamedayRoute13, transitSchedule, transitVehicles,
                13 * 3600 + 1 * 60, 15 * 3600 + 1 * 60, 480,
                "950831_", "pt_U2---17514_400_13_", "U-Bahn_veh_type", 0);


        //Adjust Route 5 Olympiastadion-Pankow
        TransitRoute u2GamedayRoute05 = u2Transitline.getRoutes().get(Id.create("U2---17514_400_5", TransitRoute.class));
        //remove Depature
        removeDeparturesWithinTimeRange(u2GamedayRoute05, 0 * 3600, 30 * 3600);

        //create Depature
        //12:09-13:00 interval: 8 min
        createDepartures(u2GamedayRoute05, transitSchedule, transitVehicles,
                12 * 3600 + 9 * 60, 13 * 3600, 480,
                "u2Special_", "pt_U2---17514_400_5_", "U-Bahn_veh_type", 0);


    }
}
