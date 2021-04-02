package com.techwording.practice.yelp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] arr = { "cat", "dog", "tac", "god", "act" };

		printGroupedAnagrams(arr);

	}

	private static void printGroupedAnagrams(String[] arr) {

		Map<String, List<String>> map = new LinkedHashMap<>();

		for (String str : arr) {
			char[] charArray = str.toCharArray();
			Arrays.sort(charArray);
			String s = String.valueOf(charArray);

			List<String> list = map.get(s);
			if (list != null) {
				list.add(str);
			}
			else {
				list = new ArrayList<String>();
				list.add(str);
				map.put(s, list);
			}
		}

		for (List<String> list : map.values()) {
			System.out.println(list);
		}
	}

}
