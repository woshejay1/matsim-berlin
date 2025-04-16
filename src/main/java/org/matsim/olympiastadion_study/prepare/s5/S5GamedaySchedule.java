package org.matsim.olympiastadion_study.prepare.s5;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.population.routes.NetworkRoute;
import org.matsim.core.population.routes.RouteUtils;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.vehicles.Vehicles;

import java.util.ArrayList;
import java.util.List;

import static org.matsim.olympiastadion_study.prepare.Utilities.*;

public class S5GamedaySchedule {
    public static void prepare(Scenario scenario, TransitSchedule transitSchedule, Vehicles transitVehicles) {

        TransitScheduleFactory transitScheduleFactory = transitSchedule.getFactory();


        // prepare s5 gameday schedule
        TransitLine s5Transitline10157 = transitSchedule.getTransitLines().get(Id.create("S5---10157_109", TransitLine.class));
        TransitLine s5Transitline10158 = transitSchedule.getTransitLines().get(Id.create("S5---10158_109", TransitLine.class));

        //create route 13: S Strausberg Nord-S Olympia-Stadion
        Id<TransitRoute> s5GamedayRoute13Id = Id.create("S5---10157_109_13", TransitRoute.class);

        //create network routes
        NetworkRoute s5GamedayNetworkRoute13 = RouteUtils.createLinkNetworkRouteImpl(
                Id.createLinkId("pt_42824"), //S Strausberg Nord
                List.of(
                        Id.createLinkId("pt_42825"), //S Strausberg Nord-S Strausberg Stadt
                        Id.createLinkId("pt_42821"), //S Strausberg Stadt-S Hegermühle
                        Id.createLinkId("pt_42822"), //S Hegermühle-S Strausberg Bhf
                        Id.createLinkId("pt_42812"), //S Strausberg Bhf-S Petershagen Nord
                        Id.createLinkId("pt_42813"), //S Petershagen Nord-S Fredersdorf
                        Id.createLinkId("pt_42814"), //S Fredersdorf-S Neuenhagen
                        Id.createLinkId("pt_42815"), //S Neuenhagen-S Hoppegarten
                        Id.createLinkId("pt_42816"), //S Hoppegarten-S Birkenstein
                        Id.createLinkId("pt_42817"), //S Birkenstein-S Mahlsdorf
                        Id.createLinkId("pt_42818"), //S Mahlsdorf-S Kaulsdorf
                        Id.createLinkId("pt_42796"), //S Kaulsdorf-S+U Wuhletal
                        Id.createLinkId("pt_42797"), //S+U Wuhletal-S Biesdorf
                        Id.createLinkId("pt_42798"), //S Biesdorf-S Friedrichsfelde Ost
                        Id.createLinkId("pt_42799"), //S Friedrichsfelde Ost-S+U Lichtenberg Bhf
                        Id.createLinkId("pt_42792"), //S+U Lichtenberg Bhf-S Nöldnerplatz
                        Id.createLinkId("pt_42793"), //S Nöldnerplatz-S Ostkreuz
                        Id.createLinkId("pt_42560"), //S Ostkreuz-S+U Warschauer Str.
                        Id.createLinkId("pt_42561"), //S+U Warschauer Str.-S Ostbahnhof
                        Id.createLinkId("pt_42562"), //S Ostbahnhof-S+U Jannowitzbrücke
                        Id.createLinkId("pt_42563"), //S+U Jannowitzbrücke-S+U Alexanderplatz Bhf
                        Id.createLinkId("pt_42564"), //S+U Alexanderplatz Bhf-S Hackescher Markt
                        Id.createLinkId("pt_42565"), //S Hackescher Markt-S+U Friedrichstr. Bhf
                        Id.createLinkId("pt_42566"), //S+U Friedrichstr. Bhf-S Berlin Hbf
                        Id.createLinkId("pt_42567"), //S Berlin Hbf-S Bellevue
                        Id.createLinkId("pt_42568"), //S Bellevue-S Tiergarten
                        Id.createLinkId("pt_42569"), //S Tiergarten-S+U Zoologischer Garten Bhf
                        Id.createLinkId("pt_42570"), //S+U Zoologischer Garten Bhf-S Savignyplatz
                        Id.createLinkId("pt_42571"), //S Savignyplatz-S Charlottenburg Bhf
                        Id.createLinkId("pt_42586"), //S Charlottenburg Bhf-S Westkreuz
                        Id.createLinkId("pt_42587"), //S Westkreuz-S Messe Süd
                        Id.createLinkId("pt_42588") //S Messe Süd-S Heerstr
                ),
                Id.createLinkId("pt_42589") //S Heerstr-S Olympiastadion
        );

        //create stops
        List<TransitRouteStop> s5GamedayStops13 = new ArrayList<>();
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320001001.1", TransitStopFacility.class),
                0.0d, 0.0d)); //S Strausberg Nord
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320002001.2", TransitStopFacility.class),
                120.0d, 120.0d)); //S Strausberg Stadt
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320003001.1", TransitStopFacility.class),
                360.0d, 360.0d)); //S Hegermühle
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320004531.2", TransitStopFacility.class),
                540.0d, 600.0d)); //S Strausberg Bhf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320005001.1", TransitStopFacility.class),
                840.0d, 840.0d)); //S Petershagen Nord
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320006016", TransitStopFacility.class),
                960.0d, 1020.0d)); //S Fredersdorf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320007001.1", TransitStopFacility.class),
                1260.0d, 1320.0d)); //S Neuenhagen
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320008005", TransitStopFacility.class),
                1440.0d, 1500.0d)); //S Hoppegarten
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320026001", TransitStopFacility.class),
                1620.0d, 1620.0d)); //S Birkenstein
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060176001002", TransitStopFacility.class),
                1800.0d, 1860.0d)); //S Mahlsdorf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060175002001.1", TransitStopFacility.class),
                1980.0d, 2040.0d)); //S Kaulsdorf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060175001011", TransitStopFacility.class),
                2100.0d, 2160.0d)); //S+U Wuhletal
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060171001001", TransitStopFacility.class),
                2280.0d, 2280.0d)); //S Biesdorf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060171002002", TransitStopFacility.class),
                2460.0d, 2460.0d)); //S Friedrichsfelde Ost
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060160004002.1", TransitStopFacility.class),
                2520.0d, 2580.0d)); //S+U Lichtenberg Bhf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060160003682", TransitStopFacility.class),
                2640.0d, 2700.0d)); //S Nöldnerplatz
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060120003654.1", TransitStopFacility.class),
                2820.0d, 2820.0d)); //S Ostkreuz
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060120004624", TransitStopFacility.class),
                2940.0d, 3000.0d)); //S+U Warschauer Str.
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060120005011", TransitStopFacility.class),
                3060.0d, 3120.0d)); //S Ostbahnhof
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060100004704", TransitStopFacility.class),
                3240.0d, 3240.0d)); //S+U Jannowitzbrücke
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060100003724", TransitStopFacility.class),
                3360.0d, 3420.0d)); //S+U Alexanderplatz Bhf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060100002734", TransitStopFacility.class),
                3480.0d, 3480.0d)); //S Hackescher Markt
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060100001756", TransitStopFacility.class),
                3600.0d, 3660.0d)); //S+U Friedrichstr. Bhf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060003201214", TransitStopFacility.class),
                3780.0d, 3780.0d)); //S Berlin Hbf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060003102224", TransitStopFacility.class),
                3900.0d, 3960.0d)); //S Bellevue
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060003103234", TransitStopFacility.class),
                4020.0d, 4080.0d)); //S Tiergarten
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060023201256", TransitStopFacility.class),
                4140.0d, 4200.0d)); //S+U Zoologischer Garten Bhf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060024203304", TransitStopFacility.class),
                4260.0d, 4320.0d)); //S Savignyplatz
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060024101338", TransitStopFacility.class),
                4380.0d, 4440.0d)); //S Charlottenburg Bhf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060024102371", TransitStopFacility.class),
                4560.0d, 4560.0d)); //S Westkreuz
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060025423402", TransitStopFacility.class),
                4680.0d, 4740.0d)); //S Messe Süd
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060025321432", TransitStopFacility.class),
                4980.0d, 4980.0d)); //S Olympiastadion

        //create complete route
        TransitRoute s5GamedayRoute13 = transitScheduleFactory.createTransitRoute(s5GamedayRoute13Id, s5GamedayNetworkRoute13, s5GamedayStops13, "rail");
        //create depatures
        createDepartures(s5GamedayRoute13, transitSchedule, transitVehicles,
                7 * 3600 + 50 * 60, 24 * 3600 + 1 * 60, 1200,
                "S5Gameday1_", "pt_S5---10157_109_13_", "S-Bahn_veh_type", 0);

        //create network routes

        //create route 14: S Olympia-Stadion-S Strausberg Nord
        Id<TransitRoute> s5GamedayRoute14Id = Id.create("S5---10157_109_14", TransitRoute.class);

        //create network routes
        NetworkRoute s5GamedayNetworkRoute14 = RouteUtils.createLinkNetworkRouteImpl(
                Id.createLinkId("pt_950827"), //S Olympiastadion
                List.of(
                        Id.createLinkId("pt_42538"), //S Olympiastadion-S Heerstr
                        Id.createLinkId("pt_42539"), //S Heerstr-S Messe Süd
                        Id.createLinkId("pt_42540"), //S Messe Süd-S Westkreuz
                        Id.createLinkId("pt_42541"), //S Westkreuz-S Charlottenburg Bhf
                        Id.createLinkId("pt_42517"), //S Charlottenburg Bhf-S Savignyplatz
                        Id.createLinkId("pt_42518"), //S Savignyplatz-S+U Zoologischer Garten Bhf
                        Id.createLinkId("pt_42519"), //S+U Zoologischer Garten Bhf-S Tiergarten
                        Id.createLinkId("pt_42520"), //S Tiergarten-S Bellevue
                        Id.createLinkId("pt_42521"), //S Bellevue-S Berlin Hbf
                        Id.createLinkId("pt_42522"), //S Berlin Hbf-S+U Friedrichstr. Bhf
                        Id.createLinkId("pt_42523"), //S+U Friedrichstr. Bhf-S Hackescher Markt
                        Id.createLinkId("pt_42524"), //S Hackescher Markt-S+U Alexanderplatz Bhf
                        Id.createLinkId("pt_42525"), //S+U Alexanderplatz Bhf-S+U Jannowitzbrücke
                        Id.createLinkId("pt_42526"), //S+U Jannowitzbrücke-S Ostbahnhof
                        Id.createLinkId("pt_42527"), //S Ostbahnhof-S+U Warschauer Str.
                        Id.createLinkId("pt_42528"), //S+U Warschauer Str.-S Ostkreuz
                        Id.createLinkId("pt_42784"), //S Ostkreuz-S Nöldnerplatz
                        Id.createLinkId("pt_42785"), //S Nöldnerplatz-S+U Lichtenberg Bhf
                        Id.createLinkId("pt_42786"), //S+U Lichtenberg Bhf-S Friedrichsfelde Ost
                        Id.createLinkId("pt_42787"), //S Friedrichsfelde Ost-S Biesdorf
                        Id.createLinkId("pt_42788"), //S Biesdorf-S+U Wuhletal
                        Id.createLinkId("pt_42789"), //S+U Wuhletal-S Kaulsdorf
                        Id.createLinkId("pt_42790"), //S Kaulsdorf-S Mahlsdorf
                        Id.createLinkId("pt_42805"), //S Mahlsdorf-S Birkenstein
                        Id.createLinkId("pt_42806"), //S Birkenstein-S Hoppegarten
                        Id.createLinkId("pt_42807"), //S Hoppegarten-S Neuenhagen
                        Id.createLinkId("pt_42808"), //S Neuenhagen-S Fredersdorf
                        Id.createLinkId("pt_42809"), //S Fredersdorf-S Petershagen Nord
                        Id.createLinkId("pt_42810"), //S Petershagen Nord-S Strausberg Bhf
                        Id.createLinkId("pt_42801"), //S Strausberg Bhf-S Hegermühle
                        Id.createLinkId("pt_42802") //S Hegermühle-S Strausberg Stadt
                ),
                Id.createLinkId("pt_42803") //S Strausberg Stadt-S Strausberg Nord
        );

        //create stops
        List<TransitRouteStop> s5GamedayStops14 = new ArrayList<>();
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060025321431.1", TransitStopFacility.class),
                0.0d, 0.0d)); //S Olympiastadion
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060025423401", TransitStopFacility.class),
                240.0d, 240.0d)); //S Messe Süd
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060024102374", TransitStopFacility.class),
                360.0d, 360.0d)); //S Westkreuz
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060024101336.1", TransitStopFacility.class),
                480.0d, 540.0d)); //S Charlottenburg Bhf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060024203303", TransitStopFacility.class),
                600.0d, 660.0d)); //S Savignyplatz
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060023201255", TransitStopFacility.class),
                720.0d, 780.0d)); //S+U Zoologischer Garten Bhf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060003103233", TransitStopFacility.class),
                840.0d, 900.0d)); //S Tiergarten
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060003102223", TransitStopFacility.class),
                960.0d, 1020.0d)); //S Bellevue
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060003201213", TransitStopFacility.class),
                1140.0d, 1200.0d)); //S Berlin Hbf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060100001755", TransitStopFacility.class),
                1320.0d, 1320.0d)); //S+U Friedrichstr. Bhf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060100002733", TransitStopFacility.class),
                1440.0d, 1440.0d)); //S Hackescher Markt
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060100003723", TransitStopFacility.class),
                1560.0d, 1560.0d)); //S+U Alexanderplatz Bhf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060100004703", TransitStopFacility.class),
                1680.0d, 1680.0d)); //S+U Jannowitzbrücke
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060120005008", TransitStopFacility.class),
                1800.0d, 1860.0d)); //S Ostbahnhof
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060120004623", TransitStopFacility.class),
                1980.0d, 1980.0d)); //S+U Warschauer Str.
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060120003653", TransitStopFacility.class),
                2100.0d, 2160.0d)); //S Ostkreuz
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060160003681", TransitStopFacility.class),
                2220.0d, 2280.0d)); //S Nöldnerplatz
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060160004001", TransitStopFacility.class),
                2400.0d, 2400.0d)); //S+U Lichtenberg Bhf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060171002001", TransitStopFacility.class),
                2520.0d, 2520.0d)); //S Friedrichsfelde Ost
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060171001002", TransitStopFacility.class),
                2700.0d, 2700.0d)); //S Biesdorf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060175001012", TransitStopFacility.class),
                2820.0d, 2880.0d)); //S+U Wuhletal
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060175002002", TransitStopFacility.class),
                3000.0d, 3000.0d)); //S Kaulsdorf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060176001003", TransitStopFacility.class),
                3060.0d, 3120.0d)); //S Mahlsdorf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320026002", TransitStopFacility.class),
                3360.0d, 3360.0d)); //S Birkenstein
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320008004", TransitStopFacility.class),
                3420.0d, 3480.0d)); //S Hoppegarten
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320007001", TransitStopFacility.class),
                3660.0d, 3660.0d)); //S Neuenhagen
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320006015", TransitStopFacility.class),
                3900.0d, 3960.0d)); //S Fredersdorf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320005001", TransitStopFacility.class),
                4140.0d, 4140.0d)); //S Petershagen Nord
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320004531.1", TransitStopFacility.class),
                4320.0d, 4380.0d)); //S Strausberg Bhf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320003001", TransitStopFacility.class),
                4620.0d, 4680.0d)); //S Hegermühle
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320002001", TransitStopFacility.class),
                4860.0d, 4860.0d)); //S Strausberg Stadt
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320001001", TransitStopFacility.class),
                5040.0d, 5040.0d)); //S Strausberg Nord

        //create complete route
        TransitRoute s5GamedayRoute14 = transitScheduleFactory.createTransitRoute(s5GamedayRoute14Id, s5GamedayNetworkRoute14, s5GamedayStops14, "rail");
        createDepartures(s5GamedayRoute14, transitSchedule, transitVehicles,
                9 * 3600 + 21 * 60, 24 * 3600 + 1 * 60, 1200,
                "S5Gameday2_", "pt_S5---10157_109_14_", "S-Bahn_veh_type", 0);

        //create route 15: S Mahlsdorf Bhf-S Olympia-Stadion
        Id<TransitRoute> s5GamedayRoute15Id = Id.create("S5---10157_109_15", TransitRoute.class);

        //create network routes
        NetworkRoute s5GamedayNetworkRoute15 = RouteUtils.createLinkNetworkRouteImpl(
                Id.createLinkId("pt_42833"), //S Mahlsdorf Bhf
                List.of(

                        Id.createLinkId("pt_42834"), //S Mahlsdorf-S Kaulsdorf
                        Id.createLinkId("pt_42796"), //S Kaulsdorf-S+U Wuhletal
                        Id.createLinkId("pt_42797"), //S+U Wuhletal-S Biesdorf
                        Id.createLinkId("pt_42798"), //S Biesdorf-S Friedrichsfelde Ost
                        Id.createLinkId("pt_42799"), //S Friedrichsfelde Ost-S+U Lichtenberg Bhf
                        Id.createLinkId("pt_42792"), //S+U Lichtenberg Bhf-S Nöldnerplatz
                        Id.createLinkId("pt_42793"), //S Nöldnerplatz-S Ostkreuz
                        Id.createLinkId("pt_42560"), //S Ostkreuz-S+U Warschauer Str.
                        Id.createLinkId("pt_42561"), //S+U Warschauer Str.-S Ostbahnhof
                        Id.createLinkId("pt_42562"), //S Ostbahnhof-S+U Jannowitzbrücke
                        Id.createLinkId("pt_42563"), //S+U Jannowitzbrücke-S+U Alexanderplatz Bhf
                        Id.createLinkId("pt_42564"), //S+U Alexanderplatz Bhf-S Hackescher Markt
                        Id.createLinkId("pt_42565"), //S Hackescher Markt-S+U Friedrichstr. Bhf
                        Id.createLinkId("pt_42566"), //S+U Friedrichstr. Bhf-S Berlin Hbf
                        Id.createLinkId("pt_42567"), //S Berlin Hbf-S Bellevue
                        Id.createLinkId("pt_42568"), //S Bellevue-S Tiergarten
                        Id.createLinkId("pt_42569"), //S Tiergarten-S+U Zoologischer Garten Bhf
                        Id.createLinkId("pt_42570"), //S+U Zoologischer Garten Bhf-S Savignyplatz
                        Id.createLinkId("pt_42571"), //S Savignyplatz-S Charlottenburg Bhf
                        Id.createLinkId("pt_42586"), //S Charlottenburg Bhf-S Westkreuz
                        Id.createLinkId("pt_42587"), //S Westkreuz-S Messe Süd
                        Id.createLinkId("pt_42588") //S Messe Süd-S Heerstr
                ),
                Id.createLinkId("pt_42589") //S Heerstr-S Olympiastadion
        );

        //create stops
        List<TransitRouteStop> s5GamedayStops15 = new ArrayList<>();
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060176001001.1", TransitStopFacility.class),
                0.0d, 0.0d)); //S Mahlsdorf
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060175002001.2", TransitStopFacility.class),
                120.0d, 180.0d)); //S Kaulsdorf
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060175001011", TransitStopFacility.class),
                240.0d, 300.0d)); //S+U Wuhletal
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060171001001", TransitStopFacility.class),
                420.0d, 420.0d)); //S Biesdorf
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060171002002", TransitStopFacility.class),
                600.0d, 600.0d)); //S Friedrichsfelde Ost
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060160004002.1", TransitStopFacility.class),
                720.0d, 780.0d)); //S+U Lichtenberg Bhf
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060160003682", TransitStopFacility.class),
                840.0d, 900.0d)); //S Nöldnerplatz
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060120003654.1", TransitStopFacility.class),
                1020.0d, 1020.0d)); //S Ostkreuz
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060120004624", TransitStopFacility.class),
                1140.0d, 1200.0d)); //S+U Warschauer Str.
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060120005011", TransitStopFacility.class),
                1260.0d, 1320.0d)); //S Ostbahnhof
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060100004704", TransitStopFacility.class),
                1440.0d, 1440.0d)); //S+U Jannowitzbrücke
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060100003724", TransitStopFacility.class),
                1560.0d, 1620.0d)); //S+U Alexanderplatz Bhf
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060100002734", TransitStopFacility.class),
                1680.0d, 1680.0d)); //S Hackescher Markt
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060100001756", TransitStopFacility.class),
                1800.0d, 1860.0d)); //S+U Friedrichstr. Bhf
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060003201214", TransitStopFacility.class),
                1980.0d, 1980.0d)); //S Berlin Hbf
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060003102224", TransitStopFacility.class),
                2100.0d, 2160.0d)); //S Bellevue
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060003103234", TransitStopFacility.class),
                2220.0d, 2280.0d)); //S Tiergarten
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060023201256", TransitStopFacility.class),
                2340.0d, 2400.0d)); //S+U Zoologischer Garten Bhf
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060024203304", TransitStopFacility.class),
                2460.0d, 2520.0d)); //S Savignyplatz
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060024101338", TransitStopFacility.class),
                2580.0d, 2640.0d)); //S Charlottenburg Bhf
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060024102371", TransitStopFacility.class),
                2760.0d, 2820.0d)); //S Westkreuz
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060025423402", TransitStopFacility.class),
                2940.0d, 3000.0d)); //S Messe Süd
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060026105402", TransitStopFacility.class),
                3120.0d, 3180.0d)); //S Heerstr.
        s5GamedayStops15.add(createTransitRouteStop(scenario, Id.create("060025321432", TransitStopFacility.class),
                3360.0d, 3360.0d)); //S Olympiastadion

        //create complete route
        TransitRoute s5GamedayRoute15 = transitScheduleFactory.createTransitRoute(s5GamedayRoute15Id, s5GamedayNetworkRoute15, s5GamedayStops15, "rail");
        //create depatures
        createDepartures(s5GamedayRoute15, transitSchedule, transitVehicles,
                8 * 3600 + 11 * 60, 24 * 3600 + 1 * 60, 1200,
                "S5Gameday3_", "pt_S5---10157_109_15_", "S-Bahn_veh_type", 0);

        //create network routes

        //create route 16: S Olympia-Stadion-S Mahlsdorf Bhf
        Id<TransitRoute> s5GamedayRoute16Id = Id.create("S5---10157_109_16", TransitRoute.class);

        //create network routes
        NetworkRoute s5GamedayNetworkRoute16 = RouteUtils.createLinkNetworkRouteImpl(
                Id.createLinkId("pt_950827"), //S Olympiastadion
                List.of(
                        Id.createLinkId("pt_42538"), //S Olympiastadion-S Heerstr
                        Id.createLinkId("pt_42539"), //S Heerstr-S Messe Süd
                        Id.createLinkId("pt_42540"), //S Messe Süd-S Westkreuz
                        Id.createLinkId("pt_42541"), //S Westkreuz-S Charlottenburg Bhf
                        Id.createLinkId("pt_42517"), //S Charlottenburg Bhf-S Savignyplatz
                        Id.createLinkId("pt_42518"), //S Savignyplatz-S+U Zoologischer Garten Bhf
                        Id.createLinkId("pt_42519"), //S+U Zoologischer Garten Bhf-S Tiergarten
                        Id.createLinkId("pt_42520"), //S Tiergarten-S Bellevue
                        Id.createLinkId("pt_42521"), //S Bellevue-S Berlin Hbf
                        Id.createLinkId("pt_42522"), //S Berlin Hbf-S+U Friedrichstr. Bhf
                        Id.createLinkId("pt_42523"), //S+U Friedrichstr. Bhf-S Hackescher Markt
                        Id.createLinkId("pt_42524"), //S Hackescher Markt-S+U Alexanderplatz Bhf
                        Id.createLinkId("pt_42525"), //S+U Alexanderplatz Bhf-S+U Jannowitzbrücke
                        Id.createLinkId("pt_42526"), //S+U Jannowitzbrücke-S Ostbahnhof
                        Id.createLinkId("pt_42527"), //S Ostbahnhof-S+U Warschauer Str.
                        Id.createLinkId("pt_42528"), //S+U Warschauer Str.-S Ostkreuz
                        Id.createLinkId("pt_42784"), //S Ostkreuz-S Nöldnerplatz
                        Id.createLinkId("pt_42785"), //S Nöldnerplatz-S+U Lichtenberg Bhf
                        Id.createLinkId("pt_42786"), //S+U Lichtenberg Bhf-S Friedrichsfelde Ost
                        Id.createLinkId("pt_42787"), //S Friedrichsfelde Ost-S Biesdorf
                        Id.createLinkId("pt_42788"), //S Biesdorf-S+U Wuhletal
                        Id.createLinkId("pt_42789") //S+U Wuhletal-S Kaulsdorf


                ),
                Id.createLinkId("pt_42790") //S Kaulsdorf-S Mahlsdorf
        );

        //create stops
        List<TransitRouteStop> s5GamedayStops16 = new ArrayList<>();
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060025321431.1", TransitStopFacility.class),
                0.0d, 0.0d)); //S Olympiastadion
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060025423401", TransitStopFacility.class),
                240.0d, 240.0d)); //S Messe Süd
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060024102374", TransitStopFacility.class),
                360.0d, 360.0d)); //S Westkreuz
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060024101336.1", TransitStopFacility.class),
                480.0d, 540.0d)); //S Charlottenburg Bhf
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060024203303", TransitStopFacility.class),
                600.0d, 660.0d)); //S Savignyplatz
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060023201255", TransitStopFacility.class),
                720.0d, 780.0d)); //S+U Zoologischer Garten Bhf
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060003103233", TransitStopFacility.class),
                840.0d, 900.0d)); //S Tiergarten
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060003102223", TransitStopFacility.class),
                960.0d, 1020.0d)); //S Bellevue
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060003201213", TransitStopFacility.class),
                1140.0d, 1200.0d)); //S Berlin Hbf
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060100001755", TransitStopFacility.class),
                1320.0d, 1320.0d)); //S+U Friedrichstr. Bhf
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060100002733", TransitStopFacility.class),
                1440.0d, 1440.0d)); //S Hackescher Markt
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060100003723", TransitStopFacility.class),
                1560.0d, 1560.0d)); //S+U Alexanderplatz Bhf
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060100004703", TransitStopFacility.class),
                1680.0d, 1680.0d)); //S+U Jannowitzbrücke
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060120005008", TransitStopFacility.class),
                1800.0d, 1860.0d)); //S Ostbahnhof
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060120004623", TransitStopFacility.class),
                1980.0d, 1980.0d)); //S+U Warschauer Str.
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060120003653", TransitStopFacility.class),
                2100.0d, 2160.0d)); //S Ostkreuz
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060160003681", TransitStopFacility.class),
                2220.0d, 2280.0d)); //S Nöldnerplatz
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060160004001", TransitStopFacility.class),
                2400.0d, 2400.0d)); //S+U Lichtenberg Bhf
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060171002001", TransitStopFacility.class),
                2520.0d, 2520.0d)); //S Friedrichsfelde Ost
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060171001002", TransitStopFacility.class),
                2700.0d, 2700.0d)); //S Biesdorf
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060175001012", TransitStopFacility.class),
                2820.0d, 2880.0d)); //S+U Wuhletal
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060175002002", TransitStopFacility.class),
                3000.0d, 3060.0d)); //S Kaulsdorf
        s5GamedayStops16.add(createTransitRouteStop(scenario, Id.create("060176001003", TransitStopFacility.class),
                3180.0d, 3180.0d)); //S Mahlsdorf


        //create complete route
        TransitRoute s5GamedayRoute16 = transitScheduleFactory.createTransitRoute(s5GamedayRoute16Id, s5GamedayNetworkRoute16, s5GamedayStops16, "rail");
        createDepartures(s5GamedayRoute16, transitSchedule, transitVehicles,
                9 * 3600 + 11 * 60, 24 * 3600 + 1 * 60, 1200,
                "S5Gameday4_", "pt_S5---10157_109_16_", "S-Bahn_veh_type", 0);




        //remove Route
        TransitRoute s5transitRoute6 = s5Transitline10157.getRoutes().get(Id.create("S5---10157_109_6", TransitRoute.class));

        TransitRoute s5transitRoute12 = s5Transitline10157.getRoutes().get(Id.create("S5---10157_109_12", TransitRoute.class));

        TransitRoute s5transitRoute02 = s5Transitline10158.getRoutes().get(Id.create("S5---10158_109_2", TransitRoute.class));

        TransitRoute s5transitRoute04 = s5Transitline10158.getRoutes().get(Id.create("S5---10158_109_4", TransitRoute.class));

        TransitRoute s5transitRoute11 = s5Transitline10158.getRoutes().get(Id.create("S5---10158_109_11", TransitRoute.class));

        TransitRoute s5transitRoute15 = s5Transitline10158.getRoutes().get(Id.create("S5---10158_109_15", TransitRoute.class));


        s5Transitline10157.addRoute(s5GamedayRoute13);
        s5Transitline10157.addRoute(s5GamedayRoute14);
        s5Transitline10157.addRoute(s5GamedayRoute15);
        s5Transitline10157.addRoute(s5GamedayRoute16);
        s5Transitline10157.removeRoute(s5transitRoute6);
        s5Transitline10157.removeRoute(s5transitRoute12);
        s5Transitline10158.removeRoute(s5transitRoute02);
        s5Transitline10157.removeRoute(s5transitRoute04);
        s5Transitline10157.removeRoute(s5transitRoute11);
        s5Transitline10157.removeRoute(s5transitRoute15);


    }
}
