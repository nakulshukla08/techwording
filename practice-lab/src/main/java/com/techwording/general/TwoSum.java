package com.techwording.general;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TwoSum {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		int[] nums = { 3, 2, 4 };
		int target = 6;
		System.out.println(Arrays.toString(twoSum1(nums, target)));

	}

	public static int[] twoSum(int[] nums, int target) {

		// Arrays.sort(nums);
		int[] result = new int[2];
		boolean found = false;
		for (int i = 0; i < nums.length; i++) {

			if (found) {
				break;
			}
			if (nums[i] < target) {
				for (int j = nums.length - 1; j >= 0; j--) {
					if (nums[j] + nums[i] < target) {
						break;
					}
					else if (nums[j] + nums[i] == target) {
						result[0] = i;
						result[1] = j;
						found = true;
						break;
					}
				}
			}

		}
		return result;
	}

	public static int[] twoSum1(int[] nums, int target) {

		// Arrays.sort(nums);
		int[] result = new int[2];

		Map<Integer, Integer> intMap = new LinkedHashMap<>();
		int i = 0;
		for (Integer num : nums) {
			intMap.put(num, i);
			i++;
		}

		i = 0;
		for (Integer num : nums) {
			int diff = target - num;
			if (diff > 0 && intMap.get(diff) != null && i != intMap.get(diff)) {

				result[0] = intMap.get(diff);
				result[1] = i;

			}

			i++;
		}
		return result;
	}
}
