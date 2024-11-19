package org.matsim.olympiastadion_study.prepare.s5;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.population.routes.NetworkRoute;
import org.matsim.core.population.routes.RouteUtils;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.vehicles.Vehicles;
import org.matsim.vehicles.VehiclesFactory;

import java.util.ArrayList;
import java.util.List;

import static org.matsim.olympiastadion_study.prepare.Utilities.*;

public class S5GamedaySchedule {
    public static void prepare(Scenario scenario, TransitSchedule transitSchedule, Vehicles transitVehicles) {

        TransitScheduleFactory transitScheduleFactory = transitSchedule.getFactory();
        VehiclesFactory vehiclesFactory = transitVehicles.getFactory();

        // prepare s5 gameday schedule
        TransitLine s5Transitline = transitSchedule.getTransitLines().get(Id.create("S5---10157_109", TransitLine.class));

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
                180.0d, 180.0d)); //S Strausberg Stadt
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320003001.1", TransitStopFacility.class),
                420.0d, 420.0d)); //S Hegermühle
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320004531.2", TransitStopFacility.class),
                660.0d, 660.0d)); //S Strausberg Bhf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320005001.1", TransitStopFacility.class),
                900.0d, 900.0d)); //S Petershagen Nord
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320006016", TransitStopFacility.class),
                1080.0d, 1080.0d)); //S Fredersdorf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320007001.1", TransitStopFacility.class),
                1380.0d, 1380.0d)); //S Neuenhagen
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320008005", TransitStopFacility.class),
                1500.0d, 1500.0d)); //S Hoppegarten
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060320026001", TransitStopFacility.class),
                1680.0d, 1680.0d)); //S Birkenstein
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060176001002", TransitStopFacility.class),
                1860.0d, 1920.0d)); //S Mahlsdorf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060175002001.1", TransitStopFacility.class),
                2100.0d, 2100.0d)); //S Kaulsdorf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060175001011", TransitStopFacility.class),
                2220.0d, 2220.0d)); //S+U Wuhletal
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060171001001", TransitStopFacility.class),
                2340.0d, 2340.0d)); //S Biesdorf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060171002002", TransitStopFacility.class),
                2520.0d, 2520.0d)); //S Friedrichsfelde Ost
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060160004002.1", TransitStopFacility.class),
                2700.0d, 2700.0d)); //S+U Lichtenberg Bhf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060160003682", TransitStopFacility.class),
                2820.0d, 2820.0d)); //S Nöldnerplatz
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060120003654.1", TransitStopFacility.class),
                2940.0d, 2940.0d)); //S Ostkreuz
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060120004624", TransitStopFacility.class),
                3120.0d, 3120.0d)); //S+U Warschauer Str.
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060120005011", TransitStopFacility.class),
                3240.0d, 3240.0d)); //S Ostbahnhof
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060100004704", TransitStopFacility.class),
                3360.0d, 3360.0d)); //S+U Jannowitzbrücke
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060100003724", TransitStopFacility.class),
                3540.0d, 3540.0d)); //S+U Alexanderplatz Bhf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060100002734", TransitStopFacility.class),
                3600.0d, 3600.0d)); //S Hackescher Markt
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060100001756", TransitStopFacility.class),
                3780.0d, 3780.0d)); //S+U Friedrichstr. Bhf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060003201214", TransitStopFacility.class),
                3900.0d, 3900.0d)); //S Berlin Hbf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060003102224", TransitStopFacility.class),
                4080.0d, 4080.0d)); //S Bellevue
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060003103234", TransitStopFacility.class),
                4200.0d, 4200.0d)); //S Tiergarten
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060023201256", TransitStopFacility.class),
                4260.0d, 4320.0d)); //S+U Zoologischer Garten Bhf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060024203304", TransitStopFacility.class),
                4440.0d, 4440.0d)); //S Savignyplatz
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060024101338", TransitStopFacility.class),
                4560.0d, 4560.0d)); //S Charlottenburg Bhf
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060024102371", TransitStopFacility.class),
                4680.0d, 4740.0d)); //S Westkreuz
        s5GamedayStops13.add(createTransitRouteStop(scenario, Id.create("060025321432", TransitStopFacility.class),
                5100.0d, 5100.0d)); //S Olympiastadion

        //create complete route
        TransitRoute s5GamedayRoute13 = transitScheduleFactory.createTransitRoute(s5GamedayRoute13Id, s5GamedayNetworkRoute13, s5GamedayStops13, "rail");
        //create depatures
        createDepartures(s5GamedayRoute13, transitSchedule, transitVehicles,
                17 * 3600 + 7 * 60, 30 * 3600, 600,
                "950828_", "pt_S5---10157_109_13_", "S-Bahn_veh_type");

        //create network routes

        //create route 14: S Strausberg Nord-S Olympia-Stadion
        Id<TransitRoute> s5GamedayRoute40Id = Id.create("S5---10157_109_14", TransitRoute.class);

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
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060024102374", TransitStopFacility.class),
                360.0d, 360.0d)); //S Westkreuz
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060024101336.1", TransitStopFacility.class),
                540.0d, 540.0d)); //S Charlottenburg Bhf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060024203303", TransitStopFacility.class),
                660.0d, 660.0d)); //S Savignyplatz
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060023201255", TransitStopFacility.class),
                720.0d, 780.0d)); //S+U Zoologischer Garten Bhf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060003103233", TransitStopFacility.class),
                900.0d, 900.0d)); //S Tiergarten
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060003102223", TransitStopFacility.class),
                1020.0d, 1020.0d)); //S Bellevue
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060003201213", TransitStopFacility.class),
                1200.0d, 1200.0d)); //S Berlin Hbf
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
                2280.0d, 2280.0d)); //S Nöldnerplatz
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060160004001", TransitStopFacility.class),
                2400.0d, 2400.0d)); //S+U Lichtenberg Bhf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060171002001", TransitStopFacility.class),
                2520.0d, 2520.0d)); //S Friedrichsfelde Ost
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060171001002", TransitStopFacility.class),
                2700.0d, 2700.0d)); //S Biesdorf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060175001012", TransitStopFacility.class),
                2880.0d, 2880.0d)); //S+U Wuhletal
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060175002002", TransitStopFacility.class),
                3000.0d, 3000.0d)); //S Kaulsdorf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060176001003", TransitStopFacility.class),
                3060.0d, 3120.0d)); //S Mahlsdorf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320026002", TransitStopFacility.class),
                3360.0d, 3360.0d)); //S Birkenstein
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320008004", TransitStopFacility.class),
                3480.0d, 3480.0d)); //S Hoppegarten
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320007001", TransitStopFacility.class),
                3660.0d, 3660.0d)); //S Neuenhagen
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320006015", TransitStopFacility.class),
                3960.0d, 3960.0d)); //S Fredersdorf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320005001", TransitStopFacility.class),
                4140.0d, 4140.0d)); //S Petershagen Nord
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320004531.1", TransitStopFacility.class),
                4380.0d, 4380.0d)); //S Strausberg Bhf
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320003001", TransitStopFacility.class),
                4620.0d, 4620.0d)); //S Hegermühle
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320002001", TransitStopFacility.class),
                4860.0d, 4860.0d)); //S Strausberg Stadt
        s5GamedayStops14.add(createTransitRouteStop(scenario, Id.create("060320001001", TransitStopFacility.class),
                4980.0d, 4980.0d)); //S Strausberg Nord

        //create complete route
        TransitRoute s5GamedayRoute14 = transitScheduleFactory.createTransitRoute(s5GamedayRoute40Id, s5GamedayNetworkRoute14, s5GamedayStops14, "rail");
        createDepartures(s5GamedayRoute14, transitSchedule, transitVehicles,
                17 * 3600 + 7 * 60, 30 * 3600, 600,
                "950829_", "pt_S5---10157_109_14_", "S-Bahn_veh_type");


        //remove Depature from 17:00-30:00
        TransitRoute s5transitRoute6 = s5Transitline.getRoutes().get(Id.create("S5---10157_109_6", TransitRoute.class));
        removeDeparturesWithinTimeRange(s5transitRoute6, 17 * 3600, 30 * 3600);

        //remove Depature from 17:00-30:00
        TransitRoute s5transitRoute12 = s5Transitline.getRoutes().get(Id.create("S5---10157_109_12", TransitRoute.class));
        removeDeparturesWithinTimeRange(s5transitRoute12, 17 * 3600, 30 * 3600);


        s5Transitline.addRoute(s5GamedayRoute13);
        s5Transitline.addRoute(s5GamedayRoute14);

    }
}
