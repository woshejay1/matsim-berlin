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

public class S3GamedaySchedule {
    public static void prepare(Scenario scenario, TransitSchedule transitSchedule, Vehicles transitVehicles) {

        TransitScheduleFactory transitScheduleFactory = transitSchedule.getFactory();


        // prepare s3 gameday schedule
        TransitLine s3Transitline = transitSchedule.getTransitLines().get(Id.create("S3---10148_109", TransitLine.class));

        //create route 12: S+U Zoologischer Garten Bhf-S Olympia-Stadion
        Id<TransitRoute> s3GamedayRoute12Id = Id.create("S3---10148_109_12", TransitRoute.class);

        //create network routes
        NetworkRoute s3GamedayNetworkRoute12 = RouteUtils.createLinkNetworkRouteImpl(
                Id.createLinkId("pt_950826"), //S+U Zoologischer Garten Bhf
                List.of(
                        Id.createLinkId("pt_42570"), //S+U Zoologischer Garten Bhf-S Savignyplatz
                        Id.createLinkId("pt_42571"), //S Savignyplatz-S Charlottenburg Bhf
                        Id.createLinkId("pt_42586"), //S Charlottenburg Bhf-S Westkreuz
                        Id.createLinkId("pt_42587"), //S Westkreuz-S Messe Süd
                        Id.createLinkId("pt_42588") //S Messe Süd-S Heerstr
                ),
                Id.createLinkId("pt_42589") //S Heerstr-S Olympia-Stadion
        );


        //create stops
        List<TransitRouteStop> s3GamedayStops12 = new ArrayList<>();
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060023201256.1", TransitStopFacility.class),
                0.0d, 0.0d)); //S+U Zoologischer Garten Bhf
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060024203304", TransitStopFacility.class),
                60.0d, 60.0d)); //S Savignyplatz
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060024101338", TransitStopFacility.class),
                180.0d, 180.0d)); //S Charlottenburg Bhf
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060024102371", TransitStopFacility.class),
                300.0d, 360.0d)); //S Westkreuz
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060025321432", TransitStopFacility.class),
                720.0d, 720.0d)); //S Olympia-Stadion

        //create complete route
        TransitRoute s3GamedayRoute12 = transitScheduleFactory.createTransitRoute(s3GamedayRoute12Id, s3GamedayNetworkRoute12, s3GamedayStops12, "rail");
        //create depature
        createDepartures(s3GamedayRoute12, transitSchedule, transitVehicles,
                11 * 3600 + 15 * 60, 72 * 3600, 1200,
                "950826_", "pt_S3---10148_109_12_", "S-Bahn_veh_type");


        //create route 13: S Olympia-Stadion-S+U Zoologischer Garten Bhf
        Id<TransitRoute> s3GamedayRoute13Id = Id.create("S3---10148_109_13", TransitRoute.class);

        //create network routes
        NetworkRoute s3GamedayNetworkRoute13 = RouteUtils.createLinkNetworkRouteImpl(
                Id.createLinkId("pt_950827"), //S Olympia-Stadion
                List.of(
                        Id.createLinkId("pt_42538"), //S Olympiastadion-S Heerstr
                        Id.createLinkId("pt_42539"), //S Heerstr-S Messe Süd
                        Id.createLinkId("pt_42540"), //S Messe Süd-S Westkreuz
                        Id.createLinkId("pt_42541"), //S Westkreuz-S Charlottenburg Bhf
                        Id.createLinkId("pt_42517") //S Charlottenburg Bhf-S Savignyplatz
                ),
                Id.createLinkId("pt_42518") //S Savignyplatz-S+U Zoologischer Garten Bhf
        );


        //create stops
        List<TransitRouteStop> s3GamedayStops13 = new ArrayList<>();
        s3GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060025321431.1", TransitStopFacility.class),
                0.0d, 0.0d)); //S Olympia-Stadion
        s3GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060024102374", TransitStopFacility.class),
                360.0d, 420.0d)); //S Westkreuz
        s3GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060024101336.1", TransitStopFacility.class),
                600.0d, 600.0d)); //S Charlottenburg Bhf
        s3GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060024203303", TransitStopFacility.class),
                720.0d, 720.0d)); //S Savignyplatz
        s3GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060023201255", TransitStopFacility.class),
                840.0d, 840.0d)); //S+U Zoologischer Garten Bhf

        //create complete route
        TransitRoute s3GamedayRoute13 = transitScheduleFactory.createTransitRoute(s3GamedayRoute13Id, s3GamedayNetworkRoute13, s3GamedayStops13, "rail");
        //create depatures
        createDepartures(s3GamedayRoute13, transitSchedule, transitVehicles,
                11 * 3600 + 15 * 60, 72 * 3600, 1200,
                "950827_", "pt_S3---10148_109_13_", "S-Bahn_veh_type");

        s3Transitline.addRoute(s3GamedayRoute12);
        s3Transitline.addRoute(s3GamedayRoute13);


    }
}
