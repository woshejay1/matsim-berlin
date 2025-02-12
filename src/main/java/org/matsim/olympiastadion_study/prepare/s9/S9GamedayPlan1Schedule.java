package org.matsim.olympiastadion_study.prepare.s9;

import org.matsim.api.core.v01.Id;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.vehicles.Vehicles;

import static org.matsim.olympiastadion_study.prepare.Utilities.*;

public class S9GamedayPlan1Schedule {
    public static void prepare(TransitSchedule transitSchedule, Vehicles transitVehicles) {


        // prepare s9 gameday schedule
        TransitLine s9Transitline = transitSchedule.getTransitLines().get(Id.create("S9---10170_109", TransitLine.class));


        TransitRoute s9GamedayRoute4 = s9Transitline.getRoutes().get(Id.create("S9---10170_109_4", TransitRoute.class));
        //remove Depature for Route 4(Spandau-Flughafen)
        removeDeparturesWithinTimeRange(s9GamedayRoute4, 12 * 3600, 14 * 3600 + 20 * 60);
        //create Depature
        createDepartures(s9GamedayRoute4, transitSchedule, transitVehicles,
                12 * 3600 + 8 * 60, 14 * 3600 + 18 * 60, 600,
                "s9GamedayPlan1_", "pt_S9---10170_109_4_", "S-Bahn_veh_type", 0);




    }

}
