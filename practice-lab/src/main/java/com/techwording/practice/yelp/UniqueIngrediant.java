package com.techwording.practice.yelp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class UniqueIngrediant {

	/*
	 *
	 * Example: Input: [ { "cuisine" : "American", "dish" : ["lettuce", "cheese", "olives", "tomato"] }, { "cuisine" : "Mexican",
	 * "dish" : ["lettuce", "cheese", "pepper", "tomato"] }, { "cuisine" : "French", "dish" : ["lettuce", "cheese", "pepper",
	 * "tomato"] }, { "cuisine" : "Continental", "dish" : ["lettuce", "cheese", "olives", "tomato"] }, ]
	 *
	 * Output: 2
	 *
	 * Because there are two unique ingredient-dishes; {Mexican, French} and {American, Continental}.
	 *
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UniqueIngrediant solution = new UniqueIngrediant();
		String[] americanDishes = { "lettuce", "cheese", "olives", "tomato" };
		String[] mexicanDishes = { "lettuce", "cheese", "pepper", "tomato" };
		String[] frenchDishes = { "lettuce", "cheese", "pepper", "tomato" };
		String[] continentalDishes = { "lettuce", "cheese", "olives", "tomato" };

		Meal meal1 = new Meal("American", Arrays.asList(americanDishes));
		Meal meal2 = new Meal("Mexican", Arrays.asList(mexicanDishes));
		Meal meal3 = new Meal("French", Arrays.asList(frenchDishes));
		Meal meal4 = new Meal("Continental", Arrays.asList(continentalDishes));

		List<Meal> list = new ArrayList<Meal>();

		list.add(meal1);
		list.add(meal2);
		list.add(meal3);
		list.add(meal4);
		solution.uniqueDishes(list);
	}

	private void uniqueDishes(List<Meal> mealList) {

		Map<List<String>, List<Meal>> collect = mealList.stream()
			.collect(Collectors.groupingBy(Meal::getDishes));

		collect.entrySet()
			.stream()
			.forEach(x -> System.out.println(x.getKey() + " : " + x.getValue()));
	}

}

class Meal {

	private String cuisine;

	private List<String> dishes;

	public Meal(String cuisine, List<String> dishes) {

		super();
		this.cuisine = cuisine;
		this.dishes = dishes;
	}

	public String getCuisine() {

		return cuisine;
	}

	public void setCuisine(String cuisine) {

		this.cuisine = cuisine;
	}

	public List<String> getDishes() {

		return dishes;
	}

	public void setDishes(List<String> dishes) {

		this.dishes = dishes;
	}

	@Override
	public int hashCode() {

		return Objects.hash(dishes);
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
		Meal other = (Meal) obj;
		return compareDishes(dishes, other.dishes);
	}

	private boolean compareDishes(List<String> dishes1, List<String> dishes2) {

		System.out.println("comparing " + dishes1 + " and " + dishes2);
		if (dishes1 == dishes2) {
			return true;
		}
		if (dishes1.size() != dishes2.size()) {
			return false;
		}

		Collections.sort(dishes1);
		Collections.sort(dishes2);

		for (int i = 0; i < dishes1.size(); i++) {
			if (dishes1.get(i) != dishes2.get(i)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {

		return "Meal [cuisine=" + cuisine + "]";
	}

}
