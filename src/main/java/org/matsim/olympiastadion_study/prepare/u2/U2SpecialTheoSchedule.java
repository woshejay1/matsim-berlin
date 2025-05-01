package org.matsim.olympiastadion_study.prepare.u2;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.population.routes.NetworkRoute;
import org.matsim.core.population.routes.RouteUtils;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.vehicles.Vehicles;

import java.util.ArrayList;
import java.util.List;

import static org.matsim.olympiastadion_study.prepare.Utilities.*;

public class U2SpecialTheoSchedule {
	public static void prepare(Scenario scenario, TransitSchedule transitSchedule, Vehicles transitVehicles){

		TransitScheduleFactory transitScheduleFactory = transitSchedule.getFactory();

		// prepare u2 gameday schedule
		TransitLine u2Transitline = transitSchedule.getTransitLines().get(Id.create("U2---17514_400", TransitLine.class));


		//Adjust Route 10 Ruhleben-Pankow
		TransitRoute u2GamedayRoute10 = u2Transitline.getRoutes().get(Id.create("U2---17514_400_10", TransitRoute.class));
		//remove Depature
		removeDeparturesWithinTimeRange(u2GamedayRoute10, 0 * 3600, 100 * 3600);
		//create Depature
		//4:23-6:33 interval: 10 min
		createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
				4 * 3600 + 23 * 60, 6 * 3600 + 33 * 60, 600,
				"950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 0);
		//6:42-9:33 interval: 9 min
		createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
				6 * 3600 + 42 * 60, 9 * 3600 + 33 * 60, 540,
				"950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 14);
		//9:43-14:00 interval: 10 min
		createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
				9 * 3600 + 43 * 60, 14 * 3600 + 0 * 60, 600,
				"950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 34);
		//14:03-18:33 interval: 9 min
		createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
				14 * 3600 + 3 * 60, 18 * 3600 + 33 * 60, 540,
				"950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 60);
		//18:43-25:43 interval: 10 min
		createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
				18 * 3600 + 43 * 60, 25 * 3600 + 43 * 60, 600,
				"950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 91);
		//25:58-30:00 interval: 15 min
		createDepartures(u2GamedayRoute10, transitSchedule, transitVehicles,
				25 * 3600 + 58 * 60, 30 * 3600 + 0 * 60, 900,
				"950830_", "pt_U2---17514_400_10_", "U-Bahn_veh_type", 134);


		//Adjust Route 13 Theodor-Heuss-Platz-Pankow
		TransitRoute u2GamedayRoute13 = u2Transitline.getRoutes().get(Id.create("U2---17514_400_13", TransitRoute.class));
		//remove Depature
		removeDeparturesWithinTimeRange(u2GamedayRoute13, 0 * 3600, 30 * 3600);
		//create Depature
		//6:04-6:34 interval: 10 min
		createDepartures(u2GamedayRoute13, transitSchedule, transitVehicles,
				6 * 3600 + 4 * 60, 6 * 3600 + 34 * 60, 600,
				"950831_", "pt_U2---17514_400_13_", "U-Bahn_veh_type", 0);
		//6:43-9:34 interval: 9 min
		createDepartures(u2GamedayRoute13, transitSchedule, transitVehicles,
				6 * 3600 + 43 * 60, 9 * 3600 + 34 * 60, 540,
				"950831_", "pt_U2---17514_400_13_", "U-Bahn_veh_type", 4);
		//9:44-14:00 interval: 10 min
		createDepartures(u2GamedayRoute13, transitSchedule, transitVehicles,
				9 * 3600 + 44 * 60, 14 * 3600 + 0 * 60, 600,
				"950831_", "pt_U2---17514_400_13_", "U-Bahn_veh_type", 24);
		//14:04-18:44 interval: 9 min
		createDepartures(u2GamedayRoute13, transitSchedule, transitVehicles,
				14 * 3600 + 04 * 60, 18 * 3600 + 34 * 60, 540,
				"950831_", "pt_U2---17514_400_13_", "U-Bahn_veh_type", 50);
		//18:44-22:34 interval: 10 min
		createDepartures(u2GamedayRoute13, transitSchedule, transitVehicles,
				18 * 3600 + 44 * 60, 22 * 3600 + 34 * 60, 600,
				"950831_", "pt_U2---17514_400_13_", "U-Bahn_veh_type", 81);



		// create route 36: U Olympia-Stadion (Berlin)-U TheodorHeussPlatz (Berlin)
		Id<TransitRoute> u2GamedayRoute36Id = Id.create("U2---17514_400_36", TransitRoute.class);

		// create network routes
		NetworkRoute u2GamedayNetworkRoute36 = RouteUtils.createLinkNetworkRouteImpl(
				Id.createLinkId("pt_43032"), //U Olympia-Stadion Bstggl.2
				List.of(
						Id.createLinkId("pt_43033") //U Olympia-Stadion Bstggl.2-U Neu-Westend Bstggl.2


				),
				Id.createLinkId("pt_43034") //U Neu-Westend Bstggl.2-U Theodor-Heuss-Platz Bstggl.2
		);

		// create stops
		List<TransitRouteStop> u2SpecialStops36 = new ArrayList<>();
		u2SpecialStops36.add(createTransitRouteStop(scenario, Id.create("070201024703.1", TransitStopFacility.class),
				0.0d, 0.0d)); //U Olympia-Stadion Bstggl.2
		u2SpecialStops36.add(createTransitRouteStop(scenario, Id.create("070201024602", TransitStopFacility.class),
				60.0d, 60.0d)); //U Neu-Westend Bstggl.2
		u2SpecialStops36.add(createTransitRouteStop(scenario, Id.create("070201024502", TransitStopFacility.class),
				180.0d, 180.0d)); //U Theodor-Heuss-Platz Bstggl.2



		// create route
		TransitRoute u2GamedayRoute36 = transitScheduleFactory.createTransitRoute(u2GamedayRoute36Id, u2GamedayNetworkRoute36, u2SpecialStops36, "rail");

		// create departures
		// 12:07-14:00 interval: 10 min
		createDepartures(u2GamedayRoute36, transitSchedule, transitVehicles,
				12 * 3600 + 10 * 60, 14 * 3600, 600,
				"U2_Theo_", "pt_U2---17514_400_", "U-Bahn_veh_type", 0);


		u2Transitline.addRoute(u2GamedayRoute36);
	}
}
