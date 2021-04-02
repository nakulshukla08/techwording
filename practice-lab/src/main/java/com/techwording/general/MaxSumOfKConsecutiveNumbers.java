package com.techwording.general;

public class MaxSumOfKConsecutiveNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] A = { 1, 2, 3, 1, 2, 3, 4, 5, 6, 7, 8, 3 };

		int maxKSum = getMaxKSum(A, 4);
		System.out.println(maxKSum);

	}

	private static int getMaxKSum(int[] A, int k) {

		if (k - 2 > A.length) {
			return 0;
		}
		int sumKLess1 = 0;
		int max = Integer.MIN_VALUE;
		int sum = 0;
		int counter = 0;

		while (counter <= k - 1) {

			sum = sum + A[counter];
			/*
			 * if (counter == k - 1) { sumKLess1 = sum; }
			 */
			counter++;
		}

		for (int i = 0; i < A.length; i++) {

			if (i + k >= A.length) {
				break;
			}

			sum = sum + A[i + k] - A[i];

			if (sum > max) {
				max = sum;
			}

		}

		return max;

	}

}
