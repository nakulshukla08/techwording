package com.techwording.practice.yelp;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;

public class BusinessWithGivenEventNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventDetails eventDetails1 = new EventDetails();

		List<String> events1 = new ArrayList<String>();
		events1.add("christmas");
		events1.add("holi");
		events1.add("diwali");

		eventDetails1.setEventName(events1);
		eventDetails1.setBusinessId("1");

		EventDetails eventDetails2 = new EventDetails();

		List<String> events2 = new ArrayList<String>();
		events2.add("christmas");
		events2.add("holi");

		eventDetails2.setEventName(events2);
		eventDetails2.setBusinessId("2");

		List<EventDetails> events = new ArrayList<>();
		events.add(eventDetails2);
		events.add(eventDetails1);

		System.out.println(findBusiness(events));

	}

	private static String findBusiness(List<EventDetails> events) {

		Integer allEventsSum = events.stream()
			.map(e -> e.getEventName()
				.size())
			.reduce(0, Integer::sum);

		IntSummaryStatistics summaryStatistics = events.stream()
			.map(e -> e.getEventName()
				.size())
			.mapToInt(x -> x)
			.summaryStatistics();

		System.out.println(summaryStatistics);

		int avg = allEventsSum / events.size();

		int max = Integer.MIN_VALUE;
		for (EventDetails e : events) {

		}

		return "x";
	}

}

class EventDetails {
	private List<String> eventName;

	private String occurance;

	private String businessId;

	public List<String> getEventName() {

		return eventName;
	}

	public void setEventName(List<String> eventName) {

		this.eventName = eventName;
	}

	public String getOccurance() {

		return occurance;
	}

	public void setOccurance(String occurance) {

		this.occurance = occurance;
	}

	public String getBusinessId() {

		return businessId;
	}

	public void setBusinessId(String businessId) {

		this.businessId = businessId;
	}

}
