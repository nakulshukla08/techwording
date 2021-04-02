package com.techwording.practice.yelp;

import java.util.ArrayList;
import java.util.List;

public class PrefixSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] business_names = { "burger king", "McDonald's", "super duper burger's", "subway", "pizza hut" };
		String searchTerm = "bur";

		List<String> prefixSearch = prefixSearch(business_names, searchTerm);

		System.out.println(prefixSearch);
	}

	private static List<String> prefixSearch(String[] input, String searchTerm) {

		List<String> result = new ArrayList<String>();

		for (String businessName : input) {

			if (isNotEmpty(businessName)) {

				String[] businessSplitName = businessName.split(" ");

				if (businessSplitName != null && businessSplitName.length > 0) {

					for (String splitName : businessSplitName) {

						if (splitName != null && splitName.length() >= searchTerm.length()) {
							String prefix = splitName.substring(0, searchTerm.length());

							if (searchTerm.equals(prefix)) {
								result.add(businessName);
								break;
							}
							else {
								System.out.println("search term didn't match prefix : " + prefix);
							}
						}
						else {
							System.out.println("split word's length didn't meet the criteria : " + splitName);
						}
					}
				}

			}

		}

		return result;

	}

	private static List<String> prefixSearch1(String[] businessNames, String searchTerm) {

		List<String> result = new ArrayList<String>();

		for (String businessName : businessNames) {

			if (isNotEmpty(businessName)) {

				String[] businessNameSplit = businessName.split(" ");

				if (businessNameSplit != null && businessNameSplit.length > 0) {

					for (String businessWord : businessNameSplit) {

						if (businessWord != null && businessWord.length() >= searchTerm.length()) {
							String prefix = businessWord.substring(0, searchTerm.length());

							if (searchTerm.equals(prefix)) {
								result.add(businessName);
								break;
							}
						}
					}
				}
			}
		}
		return result;
	}

	private static boolean isNotEmpty(String input) {

		return input != null && input != "";

	}

}
