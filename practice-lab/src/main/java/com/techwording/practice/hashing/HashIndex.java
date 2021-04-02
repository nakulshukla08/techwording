package com.techwording.practice.hashing;

import java.util.Objects;

public class HashIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = { 12, 45, 67, 89, 98, 67 };

		for (int i : arr) {
			System.out.println(Objects.hashCode(i) % arr.length);
		}
	}

}
