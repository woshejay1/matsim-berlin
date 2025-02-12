package org.matsim.olympiastadion_study.prepare.s3;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.population.routes.NetworkRoute;
import org.matsim.core.population.routes.RouteUtils;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.vehicles.Vehicles;

import java.util.ArrayList;
import java.util.List;

import static org.matsim.olympiastadion_study.prepare.Utilities.createDepartures;
import static org.matsim.olympiastadion_study.prepare.Utilities.createTransitRouteStop;

public class S3GamedayBase1Schedule {
    public static void prepare(Scenario scenario, TransitSchedule transitSchedule, Vehicles transitVehicles) {

        TransitScheduleFactory transitScheduleFactory = transitSchedule.getFactory();


        // prepare s3 gameday schedule
        TransitLine s3Transitline = transitSchedule.getTransitLines().get(Id.create("S3---10148_109", TransitLine.class));


        //create route 12: S Olympia-Stadion-S+U Zoologischer Garten Bhf
        Id<TransitRoute> s3GamedayRoute12Id = Id.create("S3---10148_109_12", TransitRoute.class);

        //create network routes
        NetworkRoute s3GamedayNetworkRoute12 = RouteUtils.createLinkNetworkRouteImpl(
                Id.createLinkId("pt_950827"), //S Olympia-Stadion
                List.of(
                        Id.createLinkId("pt_42538"), //S Olympiastadion-S Heerstr
                        Id.createLinkId("pt_42539"), //S Heerstr-S Messe Süd
                        Id.createLinkId("pt_42540") //S Messe Süd-S Westkreuz


                ),
                Id.createLinkId("pt_42541") //S Westkreuz-S Charlottenburg Bhf
        );


        //create stops
        List<TransitRouteStop> s3GamedayStops12 = new ArrayList<>();
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060025321431.1", TransitStopFacility.class),
                0.0d, 0.0d)); //S Olympia-Stadion
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060024102374", TransitStopFacility.class),
                300.0d, 360.0d)); //S Westkreuz
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060024101336.1", TransitStopFacility.class),
                480.0d, 480.0d)); //S Charlottenburg Bhf


        //create complete route
        TransitRoute s3GamedayRoute12 = transitScheduleFactory.createTransitRoute(s3GamedayRoute12Id, s3GamedayNetworkRoute12, s3GamedayStops12, "rail");
        //create depatures
        createDepartures(s3GamedayRoute12, transitSchedule, transitVehicles,
                12 * 3600 + 03 * 60, 14 * 3600 , 600,
                "950827_", "pt_S3---10148_109_12_", "S-Bahn_veh_type", 0);


        s3Transitline.addRoute(s3GamedayRoute12);


    }
}
