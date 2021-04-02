package com.techwording.general;

import java.util.Arrays;

public class MedianMergedSortedArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MedianMergedSortedArrays solution = new MedianMergedSortedArrays();

		int[] nums1 = { 1 };
		int[] nums2 = { 1 };

		solution.findMedianSortedArrays(nums1, nums2);

	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		int resultLength = nums1.length + nums2.length;
		int[] result = new int[resultLength];
		int nums1Counter = 0;
		int nums2Counter = 0;

		int lastUpdate = -1;
		for (int i = 0; i < resultLength; i++) {

			if (nums1Counter >= nums1.length || nums2Counter >= nums2.length) {
				break;
			}

			if (nums1[nums1Counter] >= nums2[nums2Counter]) {
				result[i] = nums2[nums2Counter];
				nums2Counter++;
			}
			else {
				result[i] = nums1[nums1Counter];
				nums1Counter++;
			}

			lastUpdate = i;
		}

		if (nums1Counter < nums1.length) {

			if (lastUpdate == -1) {
				lastUpdate = 0;
			}
			for (int j = lastUpdate + 1; j < resultLength; j++) {

				if (nums1Counter < nums1.length) {
					result[j] = nums1[nums1Counter];
					nums1Counter++;
				}
				else {
					break;
				}
			}
		}

		if (nums2Counter < nums2.length) {

			if (lastUpdate == -1) {
				lastUpdate = 0;
			}
			for (int j = lastUpdate + 1; j < resultLength; j++) {
				if (nums2Counter < nums2.length) {
					result[j] = nums2[nums2Counter];
					nums2Counter++;
				}
				else {
					break;
				}
			}
		}

		System.out.println(Arrays.toString(result));

		int med = result.length / 2 + result.length % 2;

		double median = result[med - 1];

		if (result.length % 2 == 0) {
			median = (median + result[med]) / 2;
		}

		System.out.println(median);
		return median;

	}

}
