package com.techwording.general;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FirstMissingPositiveInteger {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		FirstMissingPositiveInteger solution = new FirstMissingPositiveInteger();

		int[] nums = { 1, 2, 0 };
		System.out.println(solution.firstMissingPositive(nums));

	}

	public int firstMissingPositive(int[] nums) {

		if (nums == null || nums.length == 0) {
			return 1;
		}

		Arrays.sort(nums);
		int result = 0;
		Map<Integer, Boolean> checkMap = new HashMap<>();
		int min = nums[0];
		for (int n : nums) {
			if (n < min) {
				min = n;
			}
			checkMap.put(n, true);
		}

		if (min >= 2) {

			return 1;

		}
		else {
			min = 0;
			boolean exit = false;
			while (!exit) {
				if (checkMap.get(min + 1) == null && min + 1 > 0) {
					result = min + 1;
					exit = true;
				}
				min++;
			}

			return result;
		}

	}
}
