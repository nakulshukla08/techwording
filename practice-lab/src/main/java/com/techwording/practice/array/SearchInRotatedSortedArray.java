package com.techwording.practice.array;

public class SearchInRotatedSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = { 3, 5, 1 };

		int searchFor = 3;

		SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();

		int pivot = solution.findPivot(arr, 0, arr.length - 1);

		System.out.println("pivot : " + pivot);
		int key = -1;

		if (pivot == -1) {
			key = solution.binarySearch(arr, searchFor, 0, arr.length - 1);
		}
		else {
			if (arr[pivot] == searchFor) {
				key = pivot;
			}
			else if (arr[pivot] > searchFor && arr[0] < searchFor) {
				key = solution.binarySearch(arr, searchFor, pivot + 1, arr.length - 1);
			}
			else {
				key = solution.binarySearch(arr, searchFor, 0, pivot);
			}
		}
		System.out.println("key : " + key);

	}

	private int binarySearch(int[] arr, int searchFor, int start, int end) {

		if (start > end) {
			return -1;
		}

		if (start == end && searchFor == arr[start]) {
			return start;
		}

		int mid = (start + end) / 2;

		if (searchFor == arr[mid]) {
			return mid;
		}

		if (searchFor > arr[mid]) {
			return binarySearch(arr, searchFor, mid + 1, end);
		}
		else {
			return binarySearch(arr, searchFor, start, mid - 1);
		}

	}

	private int findPivot(int[] arr, int start, int end) {

		int pivot = -1;

		if (start >= end) {
			return pivot;
		}

		int mid = (start + end) / 2;

		if (mid + 1 < arr.length && arr[mid] > arr[mid + 1]) {
			return mid;
		}
		else if (mid - 1 >= 0 && arr[mid] < arr[mid - 1]) {
			return mid - 1;
		}

		if (arr[mid] > arr[end]) {
			return findPivot(arr, mid, end);

		}
		if (arr[start] > arr[mid]) {
			return findPivot(arr, start, mid);
		}
		return pivot;

	}

}
