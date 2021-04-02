package com.techwording.practice.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MergeLastKElements {

	/*
	 * File1: ……..a,b,c,d,1,3,5,7 k = 4 , n + k File2: ……..x,y,z,2,4,6,8 N=4 O/P should be 2n: i.e 1,2,3,4,5,6,7,8
	 *
	 *
	 *
	 * 1 2 3 4 5 6 7 8
	 *
	 * n = 4 ;
	 *
	 * slow + 1
	 *
	 * fast + 4
	 *
	 *
	 */

	public static void main(String[] args) {

		String[] arr1 = { "A", "B", "C", "D", "1", "3", "5", "7", "9", "10" };
		// String[] arr1 = { "7" };
		List<String> list = new LinkedList<>(Arrays.asList(arr1));

		// String[] arr2 = { "X", "Y", "Z", "2", "4", "6", "8" };
		String[] arr2 = { "8" };
		List<String> list2 = new LinkedList<>(Arrays.asList(arr2));

		List<String> mergeLastKElementsOfList = mergeLastKElementsOfList(list, list2, 4);

		if (!isEmpty(mergeLastKElementsOfList)) {
			mergeLastKElementsOfList.stream()
				.forEach(element -> System.out.println(element));
		}
		else {
			System.out.println("Empty");
		}

		List<String> result = getLastKElementsModified(list, list2, 4);

		if (!isEmpty(result)) {
			result.stream()
				.forEach(element -> System.out.println(element));
		}
		else {
			System.out.println("Empty");
		}

	}

	private static Boolean isEmpty(List<String> list) {

		return list == null || list.isEmpty();
	}

	private static List<String> mergeLastKElementsOfList(List<String> list1, List<String> list2, int k) {

		if (isEmpty(list1) || isEmpty(list2)) {
			return Collections.emptyList();
		}
		ListIterator<String> itr1 = getLastKElements(list1, k);

		ListIterator<String> itr2 = getLastKElements(list2, k);

		List<String> result = new LinkedList<>();
		int counter = 1;

		while (itr1.hasNext() || itr2.hasNext()) {
			String element = "";
			if (counter % 2 == 0 && itr2.hasNext()) {
				element = itr2.next();
			}
			else if (itr1.hasNext()) {
				element = itr1.next();
			}
			result.add(element);

			counter++;
		}

		return result;

	}

	private static List<String> getLastKElementsModified(List<String> list1, List<String> list2, int k) {

		int targetCount = 2 * k;

		int count = 0;

		ListIterator<String> listIteratorPrevious1 = list1.listIterator();
		ListIterator<String> listIteratorMain1 = list1.listIterator();

		ListIterator<String> listIteratorPrevious2 = list2.listIterator();
		ListIterator<String> listIteratorMain2 = list2.listIterator();

		// 2 3 4 5
		// 6 7

		int counter1 = 0;
		while (listIteratorPrevious1.hasNext() && counter1 <= 2 * k) {
			counter1++;
			listIteratorPrevious1.next();
		}

		while (listIteratorPrevious1.hasNext()) {
			listIteratorPrevious1.next();
			listIteratorMain1.next();
		}

		if (counter1 < k) {
			k = k + k - counter1;
		}

		int counter2 = 0;
		while (listIteratorPrevious2.hasNext() && counter2 <= k) {
			counter2++;
			listIteratorPrevious2.next();
		}

		while (listIteratorPrevious2.hasNext()) {
			listIteratorPrevious2.next();
			listIteratorMain2.next();
		}

		if (counter2 < k) {
			k = counter2;
		}

		// listIterator2.next();

		List<String> result = new ArrayList<String>();
		/*
		 * while ((listIterator1.hasNext() || listIterator2.hasNext()) && count <= targetCount) {
		 *
		 * System.out.println("count :" + count);
		 *
		 * String element = ""; int list1Counter = 0;
		 *
		 * if (count % 2 == 0 && listIterator2.hasNext()) { element = listIterator2.next(); } else if (listIterator1.hasNext()) {
		 * element = listIterator1.next(); }
		 *
		 * result.add(element);
		 *
		 * count++; }
		 */

		return result;

	}

	private static ListIterator<String> getLastKElements(List<String> list, int k) {

		ListIterator<String> listIterator1 = list.listIterator();

		int counter = 0;
		while (listIterator1.hasNext() && counter <= k) {
			counter++;
			listIterator1.next();
		}

		ListIterator<String> listIterator2 = list.listIterator();

		while (listIterator1.hasNext()) {
			listIterator1.next();
			listIterator2.next();
		}

		listIterator2.next();

		return listIterator2;

	}

}
