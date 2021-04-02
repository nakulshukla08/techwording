package com.techwording.practice.dp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CoinChange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] coins = { 1, 2, 3, 4 };

		int expectedSum = 7;
		int counter = 0;
		int result = 0;

		for (int i : coins) {
			System.out.println("i : " + i);
			List<Integer> linkedList = new LinkedList<Integer>();
			linkedList.add(i);
			int possibleCoins = possibleCoins(coins, expectedSum - i, 0, linkedList);

			dp.put(expectedSum - i, new CoinChange().new CalculationHolder(possibleCoins, linkedList));
			result = result + possibleCoins;
			System.out.println("next iter");
			counter++;
		}

		// dp = new HashMap<>();
		System.out.println(dp);
		System.out.println(result);
		System.out.println(finalList);

		System.out.println("second turn");

		counter = 1;
		for (int i : coins) {
			System.out.println("i : " + i);
			List<Integer> linkedList = new LinkedList<Integer>();
			linkedList.add(i);
			int possibleCoins = possibleCoins(coins, expectedSum - i, counter, linkedList);

			dp.put(expectedSum - i, new CoinChange().new CalculationHolder(possibleCoins, linkedList));
			result = result + possibleCoins;
			System.out.println("next iter");
			counter++;
		}

		System.out.println(dp);
		System.out.println(result);
		System.out.println(finalList);

	}

	static List<List<Integer>> finalList = new LinkedList<>();
	static Map<Integer, CalculationHolder> dp = new HashMap<>();

	class CalculationHolder {

		int ways;
		List<Integer> track;

		public CalculationHolder(int ways, List<Integer> track) {

			super();
			this.ways = ways;
			this.track = track;
		}

		public int getWays() {

			return ways;
		}

		public void setWays(int ways) {

			this.ways = ways;
		}

		public List<Integer> getTrack() {

			return track;
		}

		public void setTrack(List<Integer> track) {

			this.track = track;
		}

		@Override
		public String toString() {

			return "CalculationHolder [ways=" + ways + ", track=" + track + "]";
		}

	}

	private static int possibleCoins(int[] arr, int sum, int pos, List<Integer> linkedList) {

		System.out.println("ways to make sum : " + sum);
		CalculationHolder check = dp.get(sum);
		if (check != null) {
			if (check.getWays() == 1) {
				finalList.add(check.getTrack());
			}
			System.out.println("found precalculated for : " + sum);
			return dp.get(sum)
				.getWays();
		}

		if (sum < 0 || pos >= arr.length) {
			return 0;
		}

		if (sum == 0) {
			finalList.add(linkedList);
			return 1;
		}
		linkedList.add(arr[pos]);

		Integer value = possibleCoins(arr, sum - arr[pos], pos, linkedList);

		dp.put(sum, new CoinChange().new CalculationHolder(value, linkedList));
		return value;

	}

}
