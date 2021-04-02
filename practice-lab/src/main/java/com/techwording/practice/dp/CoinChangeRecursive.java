package com.techwording.practice.dp;

public class CoinChangeRecursive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] coins = { 1, 2, 3 };

		/*
		 * 1 1 1 1 1 1 1 1 2 1 2 2 2 3 3 1 1
		 *
		 *
		 */

		int expectedSum = 5;
		int counter = 0;
		int result = 0;
		int i = 0;

		int numberOfCoins = minCoin(coins, 3, 5);// numberOfCoins(coins, 0, 5, "");

		System.out.println(numberOfCoins);
	}

	private static int numberOfCoins(int[] coins, int m, int sum, String pattern) {

		System.out.println("sum : " + sum);

		if (sum == 0) {
			System.out.println(pattern);
			return 1;
		}
		if (sum < 0 || m >= coins.length) {
			return 0;
		}

		int count = 0;
		for (int i = m; i < coins.length; i++) {
			count += numberOfCoins(coins, m + 1, sum - coins[i], pattern + " : " + coins[i]);
		}

		return count;
	}

	static int minCoin(int C[], int m, int A) {

		if (A == 0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		int count = 0;
		for (int i = 0; i <= m - 1; i++) {
			if (C[i] <= A) {
				int curr_min = minCoin(C, m, A - C[i]);
				count = count + curr_min;
				if (curr_min != Integer.MAX_VALUE && curr_min + 1 < min) {
					min = curr_min + 1;
				}
			}
		}
		return count;
	}

}
