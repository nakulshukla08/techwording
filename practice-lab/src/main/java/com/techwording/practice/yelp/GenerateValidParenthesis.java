package com.techwording.practice.yelp;

import java.util.ArrayList;
import java.util.List;

public class GenerateValidParenthesis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "{";

		int size = 3;

		List<String> list = new ArrayList<String>();

		list = printValidParenthesis(s, size, 1, 0, list);

		System.out.println(list);

	}

	private static List<String> printValidParenthesis(String s, int size, int left, int right, List<String> list) {

		if (left == right && right == size) {
			// System.out.println(s);
			list.add(s);
			return list;
		}
		if (left <= size) {
			if (left > right) {
				printValidParenthesis(s + "}", size, left, right + 1, list);
				printValidParenthesis(s + "{", size, left + 1, right, list);
			}

			else if (left == right) {
				printValidParenthesis(s + "{", size, left + 1, right, list);
			}

		}
		return list;

	}

}
