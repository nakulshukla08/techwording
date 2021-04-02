package com.techwording.general;

public class LongestNonRepeatingSubstring {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		lengthOfLongestSubstring("");

	}

	public static int lengthOfLongestSubstring(String s) {

		if (s == null || s.isEmpty()) {
			return 0;
		}

		int max = 0;
		String result = null;

		for (int i = 0; i < s.length(); i++) {
			String substr = getSubstr(s, i, "");
			if (substr.length() > max) {
				max = substr.length();
				result = substr;
			}
		}

		System.out.println("Result : " + result);

		return max;

	}

	private static String getSubstr(String input, int pos, String result) {

		if (pos < input.length()) {
			if (result.contains(String.valueOf(input.charAt(pos)))) {
				return result;
			}
			else {
				result = result + String.valueOf(input.charAt(pos));
				result = getSubstr(input, pos + 1, result);
			}
		}
		return result;

	}

}
