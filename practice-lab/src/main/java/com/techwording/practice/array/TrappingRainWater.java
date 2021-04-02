package com.techwording.practice.array;

public class TrappingRainWater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int arr[] = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		/*
		 * 0 1 0 1 + 2 + 1 1
		 *
		 */

		System.out.println(trappedWaterVolume(arr));

	}

	private static int trappedWaterVolume(int[] arr) {

		int index = 0;
		int lastTower = 0;
		int sum = 0;
		for (int i = index; i < arr.length; i++) {
			int current = arr[i];
			if (current < lastTower) {
				sum = sum + lastTower - current;
				System.out.println("Adding : " + (lastTower - current));
			}
			else {
				lastTower = current;

			}
		}

		return sum;

	}

}
