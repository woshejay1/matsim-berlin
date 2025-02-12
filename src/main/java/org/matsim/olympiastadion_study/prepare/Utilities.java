package org.matsim.olympiastadion_study.prepare;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.api.internal.HasVehicleId;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.vehicles.Vehicle;
import org.matsim.vehicles.VehicleType;
import org.matsim.vehicles.Vehicles;
import org.matsim.vehicles.VehiclesFactory;

import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static TransitRouteStop createTransitRouteStop(Scenario scenario, Id<TransitStopFacility> stop,
                                                          double arrivalOffset, double departureOffset) {


        TransitRouteStop transitRouteStop = scenario.getTransitSchedule().getFactory().createTransitRouteStop(
                scenario.getTransitSchedule().getFacilities().get(stop),
                arrivalOffset,
                departureOffset);
        transitRouteStop.setAwaitDepartureTime(true);
        return transitRouteStop;

    }


    public static void removeDeparturesWithinTimeRange(TransitRoute transitRoute, int startTime, int endTime) {
        List<Departure> toRemove = new ArrayList<>();

        // Collect departures within the specified time range
        for (Departure departure : transitRoute.getDepartures().values()) {
            if (departure.getDepartureTime() >= startTime && departure.getDepartureTime() < endTime) {
                toRemove.add(departure);
            }
        }

        // Remove the collected departures
        for (Departure departure : toRemove) {
            transitRoute.removeDeparture(departure);
        }
    }

    public static void createDepartures(TransitRoute transitRoute, TransitSchedule transitSchedule, Vehicles transitVehicles,
                                        double firstDepartureTime, double lastDepartureTime, double interval,
                                        String departureIdCommonPart, String vehicleIdCommonPart, String vehicleTypeIdString, int depatureStartCounter) {
        // create departures
        TransitScheduleFactory transitScheduleFactory = transitSchedule.getFactory();
        VehiclesFactory vehiclesFactory = transitVehicles.getFactory();

        int departureCounter = depatureStartCounter;
        for (double departureTime = firstDepartureTime; departureTime <= lastDepartureTime; departureTime += interval) {
            Departure departure = transitScheduleFactory.createDeparture(Id.create(departureIdCommonPart + departureCounter, Departure.class), departureTime);
            Id<Vehicle> vehicleId = Id.createVehicleId(vehicleIdCommonPart + departureCounter);
            if (transitVehicles.getVehicles().get(vehicleId) == null) {
                transitVehicles.addVehicle(
                        vehiclesFactory.createVehicle(vehicleId,
                                transitVehicles.getVehicleTypes().get(Id.create(vehicleTypeIdString, VehicleType.class))
                        ));
            }
            departure.setVehicleId(vehicleId);
            transitRoute.addDeparture(departure);
            departureCounter++;

        }
    }
}
