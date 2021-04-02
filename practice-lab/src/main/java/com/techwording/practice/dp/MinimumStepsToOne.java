package com.techwording.practice.dp;

import java.util.HashMap;
import java.util.Map;

public class MinimumStepsToOne {

	/*
	 * Problem Statement: On a positive integer, you can perform any one of the following 3 steps. 1.) Subtract 1 from it. ( n = n
	 * - 1 ) , 2.) If its divisible by 2, divide by 2. ( if n % 2 == 0 , then n = n / 2 ) , 3.) If its divisible by 3, divide by
	 * 3. ( if n % 3 == 0 , then n = n / 3 ). Now the question is, given a positive integer n, find the minimum number of steps
	 * that takes n to 1
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 20;

		System.out.println(stepCount(n, 0));

	}

	static Map<Integer, Integer> stepCountCache = new HashMap<>();

	private static int stepCount(int n, int step) {

		Integer integer = stepCountCache.get(n);
		if (integer != null) {
			System.out.println("cache hit for : " + n);
			return integer;
		}
		if (n <= 1) {
			return 0;
		}
		int reduce3 = Integer.MAX_VALUE;
		int reduce2 = Integer.MAX_VALUE;
		int reduce1 = Integer.MAX_VALUE;
		if (n % 3 == 0) {
			reduce3 = 1 + stepCount(n / 3, step);
		}
		if (n % 2 == 0) {
			reduce2 = 1 + stepCount(n / 2, step);
		}

		reduce1 = 1 + stepCount(n - 1, step);

		int result = Math.min(reduce1, Math.min(reduce3, reduce2));
		stepCountCache.put(n, result);
		return result;
	}

}
