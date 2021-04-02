package com.techwording.practice.algo.dnc;

public class CalculatePower {

	public static int power(int x, int n) {

		// base condition
		if (n == 0) {
			return 1;
		}

		// calculate subproblem recursively
		int pow = power(x, n / 2);

		if ((n & 1) == 1) { // if `y` is odd
			return x * pow * pow;
		}

		// otherwise, `y` is even
		return pow * pow;
	}

	public static void main(String[] args) {

		int x = 5;
		int n = 10;

		System.out.println("pow(" + x + "," + n + ") = " + power(x, n));
	}

}
