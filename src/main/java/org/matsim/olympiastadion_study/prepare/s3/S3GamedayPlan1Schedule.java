package org.matsim.olympiastadion_study.prepare.s3;

import org.matsim.api.core.v01.Id;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.vehicles.Vehicles;

import static org.matsim.olympiastadion_study.prepare.Utilities.createDepartures;
import static org.matsim.olympiastadion_study.prepare.Utilities.removeDeparturesWithinTimeRange;

public class S3GamedayPlan1Schedule {
    public static void prepare(TransitSchedule transitSchedule, Vehicles transitVehicles) {


        // prepare s3 gameday schedule
        TransitLine s3Transitline = transitSchedule.getTransitLines().get(Id.create("S3---10148_109", TransitLine.class));

        //adjust route 3: S+U Spandau-S Erkner
        TransitRoute s3GamedayRoute3 = s3Transitline.getRoutes().get(Id.create("S3---10148_109_3", TransitRoute.class));
        removeDeparturesWithinTimeRange(s3GamedayRoute3, 12 * 3600 + 0 * 60, 14 * 3600 + 20 * 60);
        //create Depature
        //12:23-14:00 interval: 10 min
        createDepartures(s3GamedayRoute3, transitSchedule, transitVehicles,
                12 * 3600 + 03 * 60, 14 * 3600, 600,
                "s3GamedayPlan1_", "pt_S3---10148_109_3_", "S-Bahn_veh_type", 0);




    }
}
