package com.techwording.general;

// "static void main" must be defined in a public class.
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Solution {
	/**
	 * class Station
	 * <p>
	 * Respresents Station in the rail network. Each station is identified by unique name. Station is connected with other
	 * stations - this information is stored in the 'neighbours' field. Two station objects with the same name are equal therefore
	 * they are considered to be same station.
	 */
	private static class Station {
		private String name;
		private List<Station> neighbours;

		public Station(String name) {

			this.name = name;
			this.neighbours = new ArrayList<>(3);
		}

		String getName() {

			return name;
		}

		void addNeighbour(Station v) {

			this.neighbours.add(v);
		}

		List<Station> getNeighbours() {

			return this.neighbours;
		}

		@Override
		public boolean equals(Object obj) {

			return obj instanceof Station && this.name.equals(((Station) obj).getName());
		}

		@Override
		public int hashCode() {

			return Objects.hash(this.name);
		}
	}

	/**
	 * class TrainMap
	 * <p>
	 * Respresents whole rail network - consists of number of the Station objects. Stations in the map are bi-directionally
	 * connected. Distance between any 2 stations is of same constant distance unit. This implies that shortest distance between
	 * any 2 stations depends only on number of stations in between
	 */
	private static class TrainMap {

		private HashMap<String, Station> stations;

		public TrainMap() {

			this.stations = new HashMap<>();
		}

		public TrainMap addStation(String name) {

			Station s = new Station(name);
			this.stations.putIfAbsent(name, s);
			return this;
		}

		public Station getStation(String name) {

			return this.stations.get(name);
		}

		public TrainMap connectStations(Station fromStation, Station toStation) {

			if (fromStation == null) {
				throw new IllegalArgumentException("From station is null");
			}
			if (toStation == null) {
				throw new IllegalArgumentException("From station is null");
			}
			fromStation.addNeighbour(toStation);
			toStation.addNeighbour(fromStation);
			return this;
		}

		public List<Station> shortestPath(String from, String to) {

			if (stations.get(from) == null || stations.get(to) == null) {
				throw new IllegalArgumentException("No to or from station found");
			}

			Station fromStation = stations.get(from);
			// Use DFS to reach destination
			Deque<Station> queue = new ArrayDeque<>();
			queue.add(fromStation);

			// Keep tracker as well as avoid visiting the same node twice.
			Map<Station, Station> howWeReachDest = new HashMap<>();
			howWeReachDest.put(fromStation, null);

			while (!queue.isEmpty()) {
				Station current = queue.remove();

				if (current.getName()
					.equals(to)) {
					break;
				}

				for (Station neighbour : current.getNeighbours()) {
					if (!howWeReachDest.containsKey(neighbour)) {
						queue.add(neighbour);
						howWeReachDest.put(neighbour, current);
					}
				}
			}

			// Back track all the way from destination to source,
			// Note source is mapped to null, Loop will break once source is encountered.
			List<Station> shortestPath = new ArrayList<>();

			Station destination = stations.get(to);

			while (destination != null) {
				shortestPath.add(destination);
				destination = howWeReachDest.get(destination);
			}

			Collections.reverse(shortestPath);

			return shortestPath;
		}

		public static String convertPathToStringRepresentation(List<Station> path) {

			if (path.isEmpty()) {
				return "";
			}
			return path.stream()
				.map(Station::getName)
				.reduce((s1, s2) -> s1 + "->" + s2)
				.get();
		}
	}

	public static boolean doTestsPass() {

		// todo: implement more tests, please
		// feel free to make testing more elegant
		TrainMap trainMap = new TrainMap();

		trainMap.addStation("King's Cross St Pancras")
			.addStation("Angel")
			.addStation("Old Street")
			.addStation("Moorgate")
			.addStation("Farringdon")
			.addStation("Barbican")
			.addStation("Russel Square")
			.addStation("Holborn")
			.addStation("Chancery Lane")
			.addStation("St Paul's")
			.addStation("Bank");

		trainMap.connectStations(trainMap.getStation("King's Cross St Pancras"), trainMap.getStation("Angel"))
			.connectStations(trainMap.getStation("King's Cross St Pancras"), trainMap.getStation("Farringdon"))
			.connectStations(trainMap.getStation("King's Cross St Pancras"), trainMap.getStation("Russel Square"))
			.connectStations(trainMap.getStation("Russel Square"), trainMap.getStation("Holborn"))
			.connectStations(trainMap.getStation("Holborn"), trainMap.getStation("Chancery Lane"))
			.connectStations(trainMap.getStation("Chancery Lane"), trainMap.getStation("St Paul's"))
			.connectStations(trainMap.getStation("St Paul's"), trainMap.getStation("Bank"))
			.connectStations(trainMap.getStation("Angel"), trainMap.getStation("Old Street"))
			.connectStations(trainMap.getStation("Old Street"), trainMap.getStation("Moorgate"))
			.connectStations(trainMap.getStation("Moorgate"), trainMap.getStation("Bank"))
			.connectStations(trainMap.getStation("Farringdon"), trainMap.getStation("Barbican"))
			.connectStations(trainMap.getStation("Barbican"), trainMap.getStation("Moorgate"));

		String solution = "King's Cross St Pancras->Russel Square->Holborn->Chancery Lane->St Paul's";

		return solution.equals(TrainMap.convertPathToStringRepresentation(trainMap.shortestPath("King's Cross St Pancras",
			"St Paul's")));
	}

	public static void main(String[] args) {

		if (doTestsPass()) {
			System.out.println("All tests pass");
		}
		else {
			System.out.println("Tests fail.");
		}
	}
}
