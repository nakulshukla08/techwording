package com.techwording.practice.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RotateArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Integer> arr = Arrays.asList(1, 4, 6, 9, 5);
		System.out.println(rotateLeft(2, arr));

	}

	public static List<Integer> rotateLeft(int d, List<Integer> arr) {
		// Write your code here
		if (d >= 1 || d <= arr.size()) {
			int size = arr.size();
			Integer[] newList = new Integer[size];
			for (int i = 0; i < size; i++) {
				int delta = (i - d);
				int newLocation = 0;
				if (delta < 0) {
					newLocation = delta + size;
				} else {
					newLocation = delta;
				}
				newList[newLocation] = arr.get(i);
			}
			return Arrays.asList(newList);
		} else {
			return arr;
		}
	}

}
