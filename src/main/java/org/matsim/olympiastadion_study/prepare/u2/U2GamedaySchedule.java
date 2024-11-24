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
        createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
                11 * 3600 + 2 * 60, 100 * 3600, 180,
                "950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type");


        //Adjust Route 23 Pankow-Ruhleben
        TransitRoute u2GamedayRoute23 = u2Transitline.getRoutes().get(Id.create("U2---17514_400_23", TransitRoute.class));
        //remove Depature
        removeDeparturesWithinTimeRange(u2GamedayRoute23, 0 * 3600, 100 * 3600);
        //create Depature
        createDepartures(u2GamedayRoute23, transitSchedule, transitVehicles,
                11 * 3600 + 2 * 60, 100 * 3600, 180,
                "950831_", "pt_U2---17514_400_23_", "U-Bahn_veh_type");


    }
}
