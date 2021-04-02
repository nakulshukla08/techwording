package com.techwording.practice.yelp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReachableBusiness {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Business A = new Business();
		Business B = new Business();
		Business C = new Business();
		Business D = new Business();

		D.setName("D");

		B.setName("B");
		Map<Business, Integer> bMap = new HashMap<Business, Integer>();
		bMap.put(D, 5);
		B.setNearbyBusinesses(bMap);

		A.setName("A");
		Map<Business, Integer> aMap = new HashMap<Business, Integer>();
		aMap.put(B, 2);
		A.setNearbyBusinesses(aMap);

		C.setName("C");

		A.getNearbyBusinesses()
			.put(C, 4);

		List<String> findReachableBusinesses = findReachableBusinesses(A, 1);

		System.out.println(findReachableBusinesses);
	}

	private static List<String> findReachableBusinesses(Business startingBusiness, int distance) {

		List<String> list = new ArrayList<String>();

		if (distance <= 0) {
			return list;
		}

		if (startingBusiness == null || startingBusiness.getNearbyBusinesses() == null || startingBusiness.getNearbyBusinesses()
			.isEmpty()) {
			return list;
		}

		startingBusiness.getNearbyBusinesses();

		for (Map.Entry<Business, Integer> entry : startingBusiness.getNearbyBusinesses()
			.entrySet()) {
			if (entry.getValue() <= distance) {
				list.add(entry.getKey()
					.getName());
			}

			List<String> innerList = findReachableBusinesses(entry.getKey(), distance - entry.getValue());
			list.addAll(innerList);
		}

		return list;
	}

}

class Business {
	/**
	 * The name of the business.
	 */
	String name;

	/**
	 * A Map of nearbyBusinesses where the key is the nearby Business object and the value is distance from the current Business
	 * to the nearby Business.
	 */
	Map<Business, Integer> nearbyBusinesses;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Map<Business, Integer> getNearbyBusinesses() {

		return nearbyBusinesses;
	}

	public void setNearbyBusinesses(Map<Business, Integer> nearbyBusinesses) {

		this.nearbyBusinesses = nearbyBusinesses;
	}

	@Override
	public int hashCode() {

		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Business other = (Business) obj;
		return Objects.equals(name, other.name);
	}

}
