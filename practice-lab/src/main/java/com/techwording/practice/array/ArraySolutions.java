package com.techwording.practice.array;

import java.util.HashMap;
import java.util.Map;

public class ArraySolutions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = {1,1,2};
		System.out.println(removeDuplicates(nums));

	}

	private static int removeDuplicates(int[] nums) {

		if (nums == null || nums.length <= 0) {
			return 0;
		}

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			Integer integer = map.get(nums[i]);
			if (integer != null) {
				integer++;

			}
			else {
				map.put(nums[i], 1);
			}
		}
		int[] result = new int[map.size()];
		int c = 0;
		for (Map.Entry<Integer, Integer> e : map.entrySet()) {
			result[c] = e.getKey();
			c++;
		}

		nums = result;
		String resultStr = "";
		for (int i = 0; i < nums.length; i++) {
			resultStr = resultStr+nums[i]+",";
		}
		System.out.println(resultStr);
		return map.size();
	}

}
