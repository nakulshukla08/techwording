package com.techwording.practice.twod.array;

public class SumOfDiagonals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * 00 01 02 10 11 12 20 21 22
		 */
		int[][] arr = { { 2, 3, 8 }, { 4, 5, 9 }, { 6, 7, 10 } };

		SumOfDiagonals obj = new SumOfDiagonals();

		System.out.println(obj.sumOfDiag(arr));

	}

	public int sumOfDiag(int[][] arr) {

		int sum = 0;
		int i = 0;
		while (i < arr.length) {
			sum = sum + arr[i][i];
			i++;
		}

		i = 0;
		int j = arr.length - 1;

		while (i < arr.length) {
			sum = sum + arr[i][j];
			i++;
			j--;
		}

		return sum;
	}

}
