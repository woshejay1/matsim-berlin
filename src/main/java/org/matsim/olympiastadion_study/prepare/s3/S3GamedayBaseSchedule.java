package org.matsim.olympiastadion_study.prepare.s3;

import org.matsim.api.core.v01.Id;
import org.matsim.pt.transitSchedule.api.TransitLine;
import org.matsim.pt.transitSchedule.api.TransitRoute;
import org.matsim.pt.transitSchedule.api.TransitSchedule;
import org.matsim.vehicles.Vehicles;

import static org.matsim.olympiastadion_study.prepare.Utilities.createDepartures;

public class S3GamedayBaseSchedule {
    public static void prepare(TransitSchedule transitSchedule, Vehicles transitVehicles) {


        // prepare s3 gameday schedule
        TransitLine s3Transitline = transitSchedule.getTransitLines().get(Id.create("S3---10148_109", TransitLine.class));

        //adjust route 3: S+U Spandau-S Erkner
        TransitRoute s3GamedayRoute3 = s3Transitline.getRoutes().get(Id.create("S3---10148_109_3", TransitRoute.class));
        //create Depature
        //12:16-14:00 interval: 20 min
        createDepartures(s3GamedayRoute3, transitSchedule, transitVehicles,
                12 * 3600 + 16 * 60, 14 * 3600 + 0 * 60, 1200,
                "gamedayspecial_", "pt_S3---10148_109_3_", "S-Bahn_veh_type", 59);




    }
}
