package org.matsim.olympiastadion_study.prepare.u2;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.population.routes.NetworkRoute;
import org.matsim.core.population.routes.RouteUtils;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.vehicles.Vehicle;
import org.matsim.vehicles.Vehicles;

import java.util.ArrayList;
import java.util.List;

import static org.matsim.olympiastadion_study.prepare.Utilities.createDepartures;
import static org.matsim.olympiastadion_study.prepare.Utilities.createTransitRouteStop;

public class U2SpecialSchedule {
	public static void prepare(Scenario scenario, TransitSchedule transitSchedule, Vehicles transitVehicles){

		TransitScheduleFactory transitScheduleFactory = transitSchedule.getFactory();

		// prepare u2 schedule
		TransitLine u2TransitLine = transitSchedule.getTransitLines().get(Id.create("U2---17514_400", TransitLine.class));


		// create route 36: U Olympia-Stadion (Berlin)-U Deutsche Oper (Berlin)
		Id<TransitRoute> u2GamedayRoute36Id = Id.create("U2---17514_400_36", TransitRoute.class);

		// create network routes
		NetworkRoute u2GamedayNetworkRoute36 = RouteUtils.createLinkNetworkRouteImpl(
				Id.createLinkId("pt_43032"), //U Olympia-Stadion Bstggl.2
				List.of(
						Id.createLinkId("pt_43033"), //U Olympia-Stadion Bstggl.2-U Neu-Westend Bstggl.2
						Id.createLinkId("pt_43034"), //U Neu-Westend Bstggl.2-U Theodor-Heuss-Platz Bstggl.2
						Id.createLinkId("pt_43035"), //U Theodor-Heuss-Platz Bstggl.2-U Kaiserdamm Bstggl.2
						Id.createLinkId("pt_43036"), //U Kaiserdamm Bstggl.2-U Sophie-Charlotte-Platz Bstggl.2
						Id.createLinkId("pt_43037") //U Sophie-Charlotte-Platz Bstggl.2-U Bismarckstr.(oben) Bstggl.2


				),
				Id.createLinkId("pt_43038") //U Bismarckstr.(oben) Bstggl.2-U Deutsche Oper Bstggl.2
		);

		// create stops
		List<TransitRouteStop> u2SpecialStops36 = new ArrayList<>();
		u2SpecialStops36.add(createTransitRouteStop(scenario, Id.create("070201024703.1", TransitStopFacility.class),
				0.0d, 0.0d)); //U Olympia-Stadion Bstggl.2
		u2SpecialStops36.add(createTransitRouteStop(scenario, Id.create("070201024602", TransitStopFacility.class),
				60.0d, 60.0d)); //U Neu-Westend Bstggl.2
		u2SpecialStops36.add(createTransitRouteStop(scenario, Id.create("070201024502", TransitStopFacility.class),
				180.0d, 180.0d)); //U Theodor-Heuss-Platz Bstggl.2
		u2SpecialStops36.add(createTransitRouteStop(scenario, Id.create("070201024402", TransitStopFacility.class),
				300.0d, 300.0d)); //U Kaiserdamm Bstggl.2
		u2SpecialStops36.add(createTransitRouteStop(scenario, Id.create("070201024302", TransitStopFacility.class),
				360.0d, 360.0d)); //U Sophie-Charlotte-Platz Bstggl.2
		u2SpecialStops36.add(createTransitRouteStop(scenario, Id.create("070201024202", TransitStopFacility.class),
				480.0d, 480.0d)); //U Bismarckstr.(oben) Bstggl.2
		u2SpecialStops36.add(createTransitRouteStop(scenario, Id.create("070201024102", TransitStopFacility.class),
				540.0d, 540.0d)); //U Deutsche Oper Bstggl.2


		// create route
		TransitRoute u2GamedayRoute36 = transitScheduleFactory.createTransitRoute(u2GamedayRoute36Id, u2GamedayNetworkRoute36, u2SpecialStops36, "rail");

		// create departures
		createDepartures(u2GamedayRoute36, transitSchedule, transitVehicles,
				12 * 3600 + 18 * 60, 12 * 3600 + 40 * 60, 600,
				"U2_DeutscheOper_", "pt_U2---17514_400_", "U-Bahn_veh_type", 0);


		u2TransitLine.addRoute(u2GamedayRoute36);
	}
}
