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

public class S3GamedayOstbahnhofSchedule {
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
                        Id.createLinkId("pt_42540"), //S Messe Süd-S Westkreuz
                        Id.createLinkId("pt_42541"), //S Westkreuz-S Charlottenburg Bhf
                        Id.createLinkId("pt_42517"), //S Charlottenburg Bhf-S Savignyplatz
                        Id.createLinkId("pt_42518"), //S Savignyplatz-S+U Zoologischer Garten Bhf
                        Id.createLinkId("pt_42519"), //S+U Zoologischer Garten Bhf-S Tiergarten
                        Id.createLinkId("pt_42520"), //S Tiergarten-S Bellevue
                        Id.createLinkId("pt_42521"), //S Bellevue-S+U Berlin Hauptbahnhof
                        Id.createLinkId("pt_42522"), //S+U Berlin Hauptbahnhof-S+U Friedrichstr.Bhf
                        Id.createLinkId("pt_42523"), //S+U Friedrichstr.Bhf-S Hackescher Markt
                        Id.createLinkId("pt_42524"), //S Hackescher Markt-S+U Alexanderplatz Bhf
                        Id.createLinkId("pt_42525") //S+U Alexanderplatz Bhf-S+U Jannowitzbruecke


                ),
                Id.createLinkId("pt_42526") //S+U Jannowitzbruecke-S Ostbahnhof
        );


        //create stops
        List<TransitRouteStop> s3GamedayStops12 = new ArrayList<>();
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060025321431.1", TransitStopFacility.class),
                0.0d, 0.0d)); //S Olympia-Stadion
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060024102374", TransitStopFacility.class),
                360.0d, 420.0d)); //S Westkreuz
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060024101336.1", TransitStopFacility.class),
                600.0d, 600.0d)); //S Charlottenburg Bhf
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060024203303", TransitStopFacility.class),
                720.0d, 720.0d)); //S Savignyplatz
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060023201255", TransitStopFacility.class),
                840.0d, 840.0d)); //S+U Zoologischer Garten Bhf
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060003103233", TransitStopFacility.class),
                840.0d, 840.0d)); //S Tiergarten
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060003102223", TransitStopFacility.class),
                960.0d, 960.0d)); //S Bellevue
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060003201213", TransitStopFacility.class),
                1080.0d, 1140.0d)); //S+U Berlin Hauptbahnhof
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060100001755", TransitStopFacility.class),
                1260.0d, 1260.0d)); //S+U Friedrichstr.Bhf
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060100002733", TransitStopFacility.class),
                1380.0d, 1440.0d)); //S Hackescher Markt
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060100003723", TransitStopFacility.class),
                1500.0d, 1500.0d)); //S+U Alexanderplatz Bhf
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060100004703", TransitStopFacility.class),
                1620.0d, 1680.0d)); //S+U Jannowitzbruecke
        s3GamedayStops12.add(createTransitRouteStop(scenario, Id.create("060120005008", TransitStopFacility.class),
                1740.0d, 1740.0d)); //S Ostbahnhof


        //create complete route
        TransitRoute s3GamedayRoute12 = transitScheduleFactory.createTransitRoute(s3GamedayRoute12Id, s3GamedayNetworkRoute12, s3GamedayStops12, "rail");
        //create depatures
        createDepartures(s3GamedayRoute12, transitSchedule, transitVehicles,
                12 * 3600 + 3 * 60, 14 * 3600, 600,
                "S3Ostbahnhof_", "pt_S3---10148_109_12_", "S-Bahn_veh_type", 0);


        s3Transitline.addRoute(s3GamedayRoute12);


    }
}
