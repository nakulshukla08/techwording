package com.techwording.practice.freshworks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShortestSubstringWithDistinctChar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String inputString = "abbcffba"; // "abbcfaabc";// "abbcffba";// "abbcfba";
		char[] distinctArray = { 'a', 'b', 'c', 'f' };

		String sortedChars = sortedChars(distinctArray);

		String shortestSubstring = shortestSubStr(inputString, distinctArray, sortedChars);

		System.out.println(shortestSubstring);

		// abbcffba

		// abbcfaabc

	}

	static List<String> results = new ArrayList<>();

	private static String distinctSortedChars(String input) {

		Set<Character> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();

		for (Character c : input.toCharArray()) {
			if (!set.contains(c)) {
				sb.append(c);
				set.add(c);
			}
		}
		char[] charArray = sb.toString()

			.toCharArray();
		return sortedChars(charArray);

	}

	private static String sortedChars(char[] charArray) {

		Arrays.sort(charArray);
		return String.valueOf(charArray);
	}

	private static String shortestSubStr(String input, char[] distinctArray, String expected) {

		Map<String, Boolean> charCheck = new HashMap<>();

		for (char c : distinctArray) {
			charCheck.put(String.valueOf(c), true);
		}

		shortestSubstring(charCheck, input, 0, expected);

		int min = Integer.MAX_VALUE;
		String result = "";
		for (String str : results) {
			if (str.length() < min) {
				min = str.length();
				result = str;
			}
		}

		return result;
	}

	private static void shortestSubstring(Map<String, Boolean> charCheck, String input, int n, String expected) {

		if (n == input.length()) {
			return;
		}

		char[] charArray = input.toCharArray();
		String result = "";
		for (int i = n; i < charArray.length; i++) {

			result = result + charArray[i];

			String uniqueFromResult = distinctSortedChars(result);

			if (expected.equalsIgnoreCase(uniqueFromResult)) {
				results.add(result);
			}

		}

		shortestSubstring(charCheck, input, n + 1, expected);

	}
}
