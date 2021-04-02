package com.techwording.practice.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// fizzBuzz(15);

		Double calculateNav = calculateNav("20190101");
		System.out.println(calculateNav);

	}

	public static void fizzBuzz(int n) {
		// Write your code here

		for (int i = 1; i <= n; i++) {
			if (i % 15 == 0) {
				System.out.println("FizzBuzz");
			} else if (i % 3 == 0) {
				System.out.println("Fizz");
			} else if (i % 5 == 0) {
				System.out.println("Buzz");
			} else {
				System.out.println(i);
			}
		}

	}

	private static String holdingApi = "https://raw.githubusercontent.com/arcjsonapi/HoldingValueCalculator/master/api/holding";

	private static String pricingApi = "https://raw.githubusercontent.com/arcjsonapi/HoldingValueCalculator/master/api/pricing";
	
	public static double calculateNav(String date) {

		if (date == null || date.isEmpty()) {
			throw new IllegalArgumentException("Input date cannot be null");
		}
		try {

			

			StringBuilder response = invokeRestApi(holdingApi);
			Holding[] holdings = getJsonObject(response.toString(), Holding[].class);
			List<Holding> holdingsList = Arrays.asList(holdings);

			StringBuilder pricingResponse = invokeRestApi(pricingApi);
			Pricing[] pricingArr = getJsonObject(pricingResponse.toString(), Pricing[].class);
			List<Pricing> pricingList = Arrays.asList(pricingArr);
			Map<String, List<Holding>> dateGroupedHoldings = holdingsList.stream()
					.collect(Collectors.groupingBy(Holding::getDate));
			
			Map<String, List<Pricing>> dateGroupedPricings = pricingList.stream()
					.collect(Collectors.groupingBy(Pricing::getDate));
			List<Holding> holdingsForADate = dateGroupedHoldings.get(date);
			List<Pricing> pricingsForADate = dateGroupedPricings.get(date);

			Double nav = 0d;

			System.out.println(holdingsForADate);
			Map<String, Integer> SecurityQuntityMap = prepareSecurityQuantityMap(holdingsForADate);

			nav = calculateNavFromGroupedMaps(pricingsForADate, nav, SecurityQuntityMap);
			return nav;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalArgumentException("Something went wrong while calculating NAV for given date", e);
		}
	}

	private static Double calculateNavFromGroupedMaps(List<Pricing> pricingsForADate, Double nav,
			Map<String, Integer> SecurityQuntityMap) {
		for (Pricing datePrice : pricingsForADate) {
			Integer integer = SecurityQuntityMap.get(datePrice.getSecurity());
			if (integer != null) {
				Double price = datePrice.getPrice();
				nav = nav + integer * price;
			}

		}
		return nav;
	}

	private static Map<String, Integer> prepareSecurityQuantityMap(List<Holding> holdingsForADate) {
		Map<String, Integer> SecurityQuntityMap = new HashMap<>();
		for (Holding datedHolding : holdingsForADate) {
			Integer integer = SecurityQuntityMap.get(datedHolding.getSecurity());
			if (integer != null) {
				Integer newQuant = integer + datedHolding.getQuantity();
				SecurityQuntityMap.put(datedHolding.getSecurity(), newQuant);
			} else {
				SecurityQuntityMap.put(datedHolding.getSecurity(), datedHolding.getQuantity());
			}
		}
		return SecurityQuntityMap;
	}

	private static <T> T getJsonObject(String body, Class<T> type) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(body.toString(), type);
	}

	private static final String GET_METHOD = "GET";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String ACCEPT = "Accept";
	private static final String APP_JSON = "application/json";
	private static final String UTF_8 = "utf-8";

	private static StringBuilder invokeRestApi(String holdingApi)
			throws MalformedURLException, IOException, ProtocolException, UnsupportedEncodingException {
		URL url = new URL(holdingApi);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(GET_METHOD);
		con.setRequestProperty(CONTENT_TYPE, APP_JSON + ";" + UTF_8);
		con.setRequestProperty(ACCEPT, APP_JSON);
		con.setDoOutput(true);

		StringBuilder response = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), UTF_8))) {
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			// System.out.println(response.toString());
		}
		return response;
	}

	class Holding {
		private String date;
		private String security;
		private Integer quantity;
		private String portfolio;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getSecurity() {
			return security;
		}

		public void setSecurity(String security) {
			this.security = security;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public String getPortfolio() {
			return portfolio;
		}

		public void setPortfolio(String portfolio) {
			this.portfolio = portfolio;
		}

	}

	class Pricing {

		private String date;
		private String security;
		private Double price;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getSecurity() {
			return security;
		}

		public void setSecurity(String security) {
			this.security = security;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

	}

}
