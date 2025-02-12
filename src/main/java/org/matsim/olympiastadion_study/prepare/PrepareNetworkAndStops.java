package org.matsim.olympiastadion_study.prepare;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.NetworkWriter;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.CoordUtils;
import org.matsim.pt.transitSchedule.api.*;

import java.util.Set;

public class PrepareNetworkAndStops {
    public static void main(String[] args) {

        // Create configurations and scenarios
        Config config = ConfigUtils.loadConfig("G:/matsim-berlin/scenarios/berlin-v5.5-10pct/input/berlin-v5.5-10pct.config.xml");

        // Using the original network (read from SVN)
        String inputNetworkFile = "https://svn.vsp.tu-berlin.de/repos/public-svn/matsim/scenarios/countries/de/berlin/berlin-v5.5-10pct/input/berlin-v5.5-network.xml.gz";
        config.network().setInputFile(inputNetworkFile);

        // Using the original transitSchedule (read from SVN)
        String inputTransitScheduleFile = "https://svn.vsp.tu-berlin.de/repos/public-svn/matsim/scenarios/countries/de/berlin/berlin-v5.5-10pct/input/berlin-v5.5-transit-schedule.xml.gz";
        config.transit().setTransitScheduleFile(inputTransitScheduleFile);

        // Using the Plan-50000 Fans
        String inputPlansFile = "G:/Masterarbeit/Plan/3.0/testing-50000-fans-12pm.plans.xml.gz";
        config.plans().setInputFile(inputPlansFile);

        Scenario scenario = ScenarioUtils.loadScenario(config);
        Network network = scenario.getNetwork();
        TransitSchedule transitSchedule = scenario.getTransitSchedule();
        TransitScheduleFactory transitScheduleFactory = transitSchedule.getFactory();

        //Get Node from Network
        Node nodeZoologischeGarten = network.getNodes().get(Id.createNodeId("pt_060023201256"));
        Node nodeOlympiaStadion = network.getNodes().get(Id.createNodeId("pt_060025321431"));

        // Add the transit stops corresponding to the above nodes to the transitSchedule
        transitSchedule.addStopFacility(createTransitStop("060023201256.1", nodeZoologischeGarten.getCoord(), false, transitScheduleFactory, "S+U Zoologischer Garten Bhf (Berlin)", "pt_950826", "060023201256"));
        transitSchedule.addStopFacility(createTransitStop("060025321431.1", nodeOlympiaStadion.getCoord(), false, transitScheduleFactory, "S Olympiastadion (Berlin)", "pt_950827", "060025321431"));

        // Add Link for the loop
        //S+U Zoologischer Garten Bhf (loop)
        network.addLink(createLink(nodeZoologischeGarten, nodeZoologischeGarten, network, "pt_950826"));
        //S Olympiastadion (loop)
        network.addLink(createLink(nodeOlympiaStadion, nodeOlympiaStadion, network, "pt_950827"));


        String outputNetworkFilePath = "G:/Masterarbeit/Version5.6.1/updated_network.xml";
        String outputTransitScheduleFilePath = "G:/Masterarbeit/Version5.6.1/intermediate_transitSchedule.xml";
        new NetworkWriter(network).write(outputNetworkFilePath);
        new TransitScheduleWriter(transitSchedule).writeFile(outputTransitScheduleFilePath);

    }

    private static TransitStopFacility createTransitStop(String idString, Coord coord, boolean isBlocking,
                                                         TransitScheduleFactory transitScheduleFactory, String name, String linkIdStr, String stopAreaId) {
        Id<TransitStopFacility> transitStopFacilityId = Id.create(idString, TransitStopFacility.class);
        TransitStopFacility stopFacility = transitScheduleFactory.createTransitStopFacility(transitStopFacilityId, coord, isBlocking);
        stopFacility.setName(name);
        stopFacility.setLinkId(Id.createLinkId(linkIdStr));
        Id<TransitStopArea> transitStopAreaId = Id.create(stopAreaId, TransitStopArea.class);
        stopFacility.setStopAreaId(transitStopAreaId);


        return stopFacility;
    }

    private static Link createLink(Node fromNode, Node toNode, Network network, String linkId) {
        double link1Length = CoordUtils.calcEuclideanDistance(fromNode.getCoord(), toNode.getCoord());
        Link link = NetworkUtils.createLink(Id.createLinkId(linkId), fromNode, toNode, network, link1Length, 9.786, 10000, 1);
        link.setAllowedModes(Set.of(TransportMode.pt));

        return link;
    }

}
