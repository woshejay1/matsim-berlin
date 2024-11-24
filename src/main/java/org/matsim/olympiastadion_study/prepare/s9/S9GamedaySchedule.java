package org.matsim.olympiastadion_study.prepare.s9;

import org.matsim.api.core.v01.Id;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.vehicles.Vehicles;

import static org.matsim.olympiastadion_study.prepare.Utilities.*;

public class S9GamedaySchedule {
    public static void prepare(TransitSchedule transitSchedule, Vehicles transitVehicles) {


        // prepare s9 gameday schedule
        TransitLine s9Transitline = transitSchedule.getTransitLines().get(Id.create("S9---10170_109", TransitLine.class));


        TransitRoute s9GamedayRoute4 = s9Transitline.getRoutes().get(Id.create("S9---10170_109_4", TransitRoute.class));
        //remove Depature for Route 4(Spandau-Flughafen)
        removeDeparturesWithinTimeRange(s9GamedayRoute4, 0 * 3600, 72 * 3600);
        //create Depature
        createDepartures(s9GamedayRoute4, transitSchedule, transitVehicles,
                11 * 3600 + 9 * 60, 72 * 3600, 600,
                "950832_", "pt_S9---10170_109_4_", "S-Bahn_veh_type");


        //remove Depature for Route 8 from 17:00-30:00(Flughafen-Spandau)
        TransitRoute s9transitRoute8 = s9Transitline.getRoutes().get(Id.create("S9---10170_109_8", TransitRoute.class));
        //remove Depature Flughafen-Spandau
        removeDeparturesWithinTimeRange(s9transitRoute8, 0 * 3600, 72 * 3600);
        //create Depature Spandau-Flughafen
        createDepartures(s9transitRoute8, transitSchedule, transitVehicles,
                11 * 3600 + 9 * 60, 72 * 3600, 600,
                "950833_", "pt_S9---10170_109_8_", "S-Bahn_veh_type");

    }

}
