package com.techwording.general;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ZigZagString {

	/*
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display
	 * this pattern in a fixed font for better legibility)
	 *
	 * P A H N A P L S I I G Y I R And then read line by line: "PAHNAPLSIIGYIR"
	 *
	 * Write the code that will take a string and make this conversion given a number of rows:
	 */

	public static void main(String[] args) {

		convert("PAYPALISHIRING", 3);
		// TODO Auto-generated method stub

	}

	public static String convert(String s, int numRows) {

		Map<Integer, List<Character>> charMap = new HashMap<>();
		boolean terminate = false;
		char[] str = s.toCharArray();
		int lastVisitedA = 0;
		int lastVisitedB = numRows - 2;

		int counter = 0;
		while (!terminate) {
			for (int i = lastVisitedA; i < numRows; i++) {

				if (counter >= s.length()) {
					terminate = true;
					break;
				}
				checkMap(charMap, str[counter], i);
				counter++;
			}

			lastVisitedA = 0;

			for (int i = lastVisitedB; i > lastVisitedA; i--) {
				if (counter >= s.length()) {
					terminate = true;
					break;
				}
				checkMap(charMap, str[counter], i);
				counter++;
			}

		}
		StringBuilder sb = new StringBuilder();
		charMap.values()
			.stream()
			.flatMap(x -> x.stream())
			.forEach(a -> sb.append(a));

		return sb.toString();

	}

	private static void checkMap(Map<Integer, List<Character>> charMap, char str, int i) {

		List<Character> list = charMap.get(i);
		if (list != null) {
			list.add(str);
		}
		else {
			list = new LinkedList<>();
			list.add(str);
			charMap.put(i, list);
		}
	}

	private static void conversion() {

	}

}
