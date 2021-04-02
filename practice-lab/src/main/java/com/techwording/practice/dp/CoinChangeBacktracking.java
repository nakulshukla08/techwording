package com.techwording.practice.dp;

import java.util.LinkedList;
import java.util.List;

public class CoinChangeBacktracking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] coins = { 1, 2, 5 };

		int expectedSum = 11;
		int counter = 0;
		int result = 0;

		for (int i : coins) {
			System.out.println("i : " + i);
			List<Integer> linkedList1 = new LinkedList<Integer>();
			linkedList1.add(i);
			int possibleCoins = possibleCoins(coins, expectedSum - i, 0, copyList(linkedList1), expectedSum);

			int possible = possibleCoins(coins, expectedSum - i, counter + 1, copyList(linkedList1), expectedSum);

			System.out.println("2nd way : " + possible);

			result = result + possibleCoins;
			System.out.println("next iter");
			counter++;
		}

		System.out.println(result);
		System.out.println(finalList);

		System.out.println("second turn");

		counter = 1;
		for (int i : coins) {
			System.out.println("i : " + i);
			List<Integer> linkedList = new LinkedList<Integer>();
			linkedList.add(i);
			int possibleCoins = possibleCoins(coins, expectedSum, counter, linkedList, expectedSum);

			result = result + possibleCoins;
			System.out.println("next iter");
			counter++;
		}

		System.out.println(result);
		System.out.println("final " + finalList);

		System.out.println("New approach");

		int count = 0;
		int possibleCoins = 0;
		for (int i : coins) {

			possibleCoins += possibleCoins(coins, count, expectedSum, "");
			count++;
		}

		System.out.println("answer : " + possibleCoins);

	}

	private static int possibleCoins(int[] arr, int pos, int sum, String result) {

		// System.out.println(result);
		if (sum == 0) {
			System.out.println("one possible way : " + result);
			return 1;
		}
		if (sum < 0 || pos >= arr.length) {
			return 0;
		}

		return possibleCoins(arr, pos, sum - arr[pos], result + " : " + arr[pos]) + possibleCoins(arr, pos + 1, sum, result);
	}

	private static int possibleCoins(int[] arr, int sum, int pos, List<Integer> linkedList, int originalSum) {

		System.out.println("ways to make sum : " + sum);

		if (sum < 0 || pos >= arr.length) {
			return 0;
		}

		if (sum == 0) {
			System.out.println("found a way : " + linkedList);
			finalList.add(linkedList);
			return 1;
		}
		linkedList.add(arr[pos]);

		Integer value = possibleCoins(arr, sum - arr[pos], pos, copyList(linkedList), originalSum);

		int possibleCoins = possibleCoins(arr, originalSum, pos + 1, linkedList, originalSum);

		return value + possibleCoins;

	}

	private static <T> List<T> copyList(List<T> source) {

		List<T> target = new LinkedList<>();

		for (T t : source) {
			target.add(t);
		}

		return target;

	}

	static List<List<Integer>> finalList = new LinkedList<>();

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

}
