package com.techwording.general;

public class MaximumArea {

	/*
	 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are
	 * drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis
	 * forms a container, such that the container contains the most water.
	 *
	 * Notice that you may not slant the container.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MaximumArea area = new MaximumArea();
		int[] arr = { 4, 3, 2, 1, 4 };
		System.out.println(area.maxArea(arr));

	}

	public int maxArea(int[] height) {

		int max = 0;
		for (int i = 0; i < height.length; i++) {

			for (int j = 0; j < height.length; j++) {

				if (i != j) {
					int temp = Math.min(height[i], height[j]) * (j - i);
					if (temp > max) {
						max = temp;
					}
				}
			}

		}

		return max;

	}

}
