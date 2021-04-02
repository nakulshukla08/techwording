package com.techwording.practice.twod.array;

import java.util.Arrays;

public class Transpose {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] arr = { { 2, 3 }, { 4, 5 }, { 6, 7 } };
		int breadth = arr[0].length;

		int[][] result = new int[breadth][arr.length];

		for (int j = 0; j < breadth; j++) {

			int i = 0;
			for (int[] element : arr) {

				result[j][i] = element[j];
				System.out.println(element[j]);

				i++;
			}
		}

		System.out.println(Arrays.toString(result));

	}

}
