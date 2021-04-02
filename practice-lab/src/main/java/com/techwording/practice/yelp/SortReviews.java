package com.techwording.practice.yelp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class SortReviews {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Restaurant> restaurantList = getRestaurantList();

		TreeSet<Restaurant> set = new TreeSet<>();

		for (Restaurant rest : restaurantList) {
			set.add(rest);
		}

		set.stream()
			.forEach(x -> System.out.println(x));

		System.out.println(set);

		float jaccardIndex = jaccardIndex("Larry's Roof Roof Business", "Roof Business");

		System.out.println(jaccardIndex);

	}

	private static float jaccardIndex(String str1, String str2) {

		String[] split1 = str1.split(" ");
		String[] split2 = str2.split(" ");

		Map<String, Integer> commonalityMap = new HashMap<>();

		for (String str : split1) {
			commonalityMap.merge(str, 1, Integer::sum);
		}

		float commonality = 0f;

		for (String str : split2) {

			if (commonalityMap.get(str) != null) {
				commonality++;
			}
			commonalityMap.merge(str, 1, Integer::sum);
		}

		return commonality / commonalityMap.size();
	}

	private static List<Restaurant> getRestaurantList() {

		List<Restaurant> restaurants = new ArrayList<Restaurant>();

		Restaurant one = new Restaurant("1", 4);
		Restaurant two = new Restaurant("2", 5);
		Restaurant three = new Restaurant("3", 2);

		restaurants.add(three);
		restaurants.add(two);
		restaurants.add(one);

		return restaurants;

	}

}

class RatingComparator implements Comparator<Restaurant> {

	@Override
	public int compare(Restaurant o1, Restaurant o2) {

		System.out.println("Comparing : " + o1 + " | " + o2);
		// TODO Auto-generated method stub
		if (o1.getRating() > o2.getRating()) {
			return -11;
		}
		else if (o1.getRating() < o2.getRating()) {
			return 1;
		}
		else {
			return 0;
		}
	}

}

class Restaurant implements Comparable<Restaurant> {

	private String id;

	private Integer rating;

	public Restaurant(String id, Integer rating) {

		super();
		this.id = id;
		this.rating = rating;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public Integer getRating() {

		return rating;
	}

	public void setRating(Integer rating) {

		this.rating = rating;
	}

	@Override
	public String toString() {

		return "Restaurant [id=" + id + ", rating=" + rating + "]";
	}

	@Override
	public int compareTo(Restaurant o) {

		return rating - o.getRating();
	}

}
