package com.techwording.practice.twod.array;

public class RearrangeCharacters {

	/*
	 *
	 * Given a string with repeated characters, the task is to rearrange characters in a string so that no two adjacent characters
	 * are same.
	 *
	 *
	 * Input: aaabc Output: abaca
	 *
	 * char[] result = abaca char [] remaining = aaabc aac ac a n!
	 *
	 * char previous = a b a c
	 *
	 * Input: aa Output: Not Possible
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String rearrangeString = rearrangeString("aaabc".toCharArray());

		System.out.println(rearrangeString);

		System.out.println(rearrangeString("aa".toCharArray()));

	}

	private static String rearrangeString(char[] input) {

		if (input == null || input.length == 0) {
			return "Not Possible";
		}

		// aaabc

		char previous = input[0];
		char[] result = new char[input.length];
		int resultCounter = 0;
		int matchFoundIndex = 0;
		for (int i = 0; i < input.length; i++) {

			if (i == 0 || input[i] != previous && input[i] != '-') {
				result[resultCounter] = input[i];
				previous = input[i];
				input[i] = '-';
				i = matchFoundIndex;
				resultCounter++;
			}
			else if (matchFoundIndex > 0) {
				matchFoundIndex = i;
			}

		}

		System.out.println("counter : " + resultCounter);

		if (resultCounter != input.length) {
			return "Not Possible";
		}

		return String.valueOf(result);

	}

}
