package com.techwording.practice.algo.greedy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CoinChange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] coins = { 2, 5, 10, 17, 20, 40 };

		int expectedSum = 17;

		CoinChange solution = new CoinChange();

		List<List<Integer>> tracks = new ArrayList<>();

		int i = 0;

		int totalWays = 0;
		/*
		 * for (int coinValue : coins) { List<Integer> list = new LinkedList<Integer>(); // totalWays = totalWays +
		 * solution.waysToGiveChange(coins, i, expectedSum, list, tracks); tracks.add(list); i++; }
		 */

		List<Integer> list = new LinkedList<Integer>();
		solution.waysToGiveChange(coins, coins.length, expectedSum, list, tracks);

		solution.allPossibleWays(coins, 0, expectedSum);

		System.out.println(tracks.toString());
		System.out.println(totalWays);

	}

	private int allPossibleWays(int[] coins, int pos, int sum) {

		if (sum == 0) {
			return 1;
		}

		if (pos >= coins.length) {
			return 0;
		}

		if (sum < coins[pos]) {
			return 0;
		}
		else {
			int x = allPossibleWays(coins, pos + 1, sum);
			int y = allPossibleWays(coins, pos, sum - coins[pos]);

			System.out.println(x + " : " + y);
			return x + y;
		}
	}

	public int waysToGiveChange(int[] coins, int size, int sum, List<Integer> track, List<List<Integer>> finalList) {

		if (size <= 0 && sum > 0) {
			return 0;
		}

		if (sum < 0) {
			return 0;
		}

		if (sum == 0) {
			System.out.println("found");
			finalList.add(track);
			return 1;
		}

		if (coins[size - 1] <= sum) {
			track.add(coins[size - 1]);
			System.out.println("added : " + coins[size - 1]);

		}

		return waysToGiveChange(coins, size - 1, sum, copy(track), finalList) +
				waysToGiveChange(coins, size, sum - coins[size - 1],
					copy(track), finalList);

	}

	private <T> List<T> copy(List<T> source) {

		if (source == null) {
			return null;
		}

		List<T> target = new LinkedList<>();

		for (T t : source) {
			target.add(t);
		}

		return target;
	}

}
