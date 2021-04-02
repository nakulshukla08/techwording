package com.techwording.practice.dp;

public class BinomialCoeeficient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Binomial coefficient nCr

		int n = 5;
		int r = 2;

		int partialFactorial = getPartialFactorial(n, 2);

		System.out.println(partialFactorial);

	}

	private static int getPartialFactorial(int n, int k) {

		int result = n;

		for (int i = 1; i < k; i++) {
			result = result * (n - 1);
			n--;
		}

		return result;

	}

}
