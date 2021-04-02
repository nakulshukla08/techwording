package com.techwording.practice.glovo;

public class Glovo {

	public int findNumberOfOccurances(int[] input, int x) {

		int start = 0;
		int end = input.length;

		int resultIndex = binarySearch(input, start, end, x);

		return findOccurenceUtil(resultIndex, x, input);
	}

	private int findOccurenceUtil(int resultIndex, int x, int[] input) {

		int counter = 0;

		for (int i = resultIndex; i < input.length; i++) {
			if (input[i] != x) {
				break;
			}
			counter++;
		}

		for (int i = resultIndex - 1; i >= 0; i--) {
			if (input[i] != x) {
				break;
			}
			counter++;
		}

		return counter;

	}

	private int binarySearch(int[] input, int start, int end, int x) {

		int mid = (start + end) / 2;

		if (mid >= input.length || mid < 0) {
			return 0;
		}

		if (x == input[mid]) {
			return mid;
		}
		int answer = 0;
		if (x > input[mid]) {
			answer = binarySearch(input, mid + 1, end, x);
		}
		else {
			answer = binarySearch(input, start, mid - 1, x);
		}

		return answer;

	}

	// @Test
	public void testShouldAddTwoNumbers() {

		// you can write to stdout for debugging purposes, e.g.
		System.out.println("This is a debug message");
		int[] input = { 2, 2, 2, 2, 2, 2, 2 };
		int x = 2;

		int result = findNumberOfOccurances(input, x);

		System.out.println(result);
		// Assert.assertEquals(7, result);
	}
}
