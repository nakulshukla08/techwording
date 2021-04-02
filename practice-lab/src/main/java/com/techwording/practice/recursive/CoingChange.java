package com.techwording.practice.recursive;

import java.util.HashMap;
import java.util.Map;

public class CoingChange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = { 1, 2, 3 };
		int k = 4;

		// System.out.println(waysForCoinChange(arr, k));

		// System.out.println(count(arr, k));

		// System.out.println("NCount : " + NCount);

		System.out.println(waysForCoinChangeDistinct(arr, k, arr.length - 1));

		System.out.println("kCount : " + kCount);

	}

	private static Map<String, Integer> map = new HashMap<>();

	private static int kCount = 0;

	private static int NCount = 0;

	private static int waysForCoinChangeDistinct(int[] arr, int k, int n) {

		kCount++;
		if (n < 0 || k < 0) {
			return 0;
		}
		if (k == 0) {
			return 1;
		}
		int x = 0;
		int y = 0;
		String xKey = k + "|" + n;
		if (map.get(xKey) == null) {
			x = waysForCoinChangeDistinct(arr, k - arr[n], n);

			y = waysForCoinChangeDistinct(arr, k, n - 1);

			map.put(xKey, x + y);

			return x + y;
		}
		else {
			return map.get(xKey);
		}

	}

	private static int waysForCoinChange(int[] arr, int k) {

		System.out.println("invocation for k = " + k);
		kCount++;
		int index = 0;

		if (k == 0) {

			return 1;
		}
		if (index >= arr.length || k < 0) {
			return 0;
		}
		int sum = 0;
		for (int i = index; i < arr.length; i++) {
			if (map.get(k - arr[i]) == null) {
				int ways = waysForCoinChange(arr, k - arr[i]);
				// map.put(k, ways);
				sum = sum + ways;
			}
			else {
				sum = sum + map.get(k - arr[i]);
			}
		}
		return sum;
	}

	public static int count(int[] S, int N) {

		System.out.println("invocation for N = " + N);

		NCount++;

		// if the total is 0, return 1
		if (N == 0) {
			return 1;
		}

		// return 0 if total becomes negative
		if (N < 0) {
			return 0;
		}

		// initialize the total number of ways to 0
		int result = 0;

		// do for each coin
		for (int element : S) {
			// recur to see if total can be reached by including
			// current coin `S[i]`
			result += count(S, N - element);
		}

		// return the total number of ways
		return result;
	}

}
