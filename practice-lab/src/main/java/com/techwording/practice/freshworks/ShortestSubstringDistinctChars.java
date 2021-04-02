package com.techwording.practice.freshworks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ShortestSubstringDistinctChars {

	public static void main(String[] args) {

		String inputString = "aabbccffdabcfaabbccff"; // "abbcfaabc";// "abbcffba"; // "abbcfaabc";//
		// "abbcffba";// "abbcfba";
		Character[] distinctArray = { 'a', 'b', 'c', 'f' };

		String result = shortestSubstringWithGivenDistinctArray(inputString, distinctArray);
		System.out.println(result);

	}

	private static Set<String> arrayToSet(Character[] distinctArray) {

		return Arrays.stream(distinctArray)
			.map(c -> String.valueOf(c))
			.collect(Collectors.toSet());

	}

	private static String shortestSubstringWithGivenDistinctArray(String inputString, Character[] distinctArray) {

		int distinctArraySize = distinctArray.length;
		if (inputString.length() < distinctArraySize) {
			return "";
		}
		int end = distinctArraySize;
		int start = 0;
		int min = Integer.MAX_VALUE;
		String resultString = "";
		Map<String, Boolean> map = null;
		// To avoid inclusion of any other distinct characters in input apart from the ones from the given array.
		Set<String> distinctChar = arrayToSet(distinctArray);
		while (start < end && end < inputString.length()) {
			int distinctCount = 0;
			map = new HashMap<>();
			for (int i = start; i <= end; i++) {
				String charAtI = String.valueOf(inputString.charAt(i));
				Boolean occuredPreviously = map.get(charAtI);
				if (distinctChar.contains(charAtI) && (occuredPreviously == null || !occuredPreviously)) {
					distinctCount++;
					map.put(charAtI, Boolean.TRUE);
				}
			}

			if (distinctCount == distinctArraySize) {
				String tempResultString = inputString.substring(start, end + 1);
				if (tempResultString.length() < min) {
					resultString = tempResultString;
					min = tempResultString.length();
				}
				start++;
			}
			else if (distinctCount < distinctArraySize) {
				end++;
			}
			map = null;
		}

		return resultString;

	}

}
