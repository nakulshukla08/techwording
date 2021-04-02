package com.techwording.general;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentElements {

	/*
	 * Given a non-empty array of integers, return the k most frequent elements.
	 *
	 * Example 1:
	 *
	 * Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2]
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] num = { 1, 1, 1, 2, 2, 3 };
		int k = 2;

		TopKFrequentElements solution = new TopKFrequentElements();
		solution.topKFrequent(num, k);

	}

	public int[] topKFrequent(int[] nums, int k) {

		Queue<Element> queue = new PriorityQueue<>(new ElementComparator());

		Map<Integer, Integer> map = new HashMap<>();

		for (int n : nums) {
			Integer integer = map.get(n);
			if (integer != null) {
				map.put(n, integer + 1);
			}
			else {
				map.put(n, 1);
			}
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			Element el = new Element(entry.getKey(), entry.getValue());
			queue.add(el);
		}

		queue.stream()
			.forEach(x -> System.out.println(x));

		int[] arr = new int[k];
		int i = 0;
		while (i < k) {
			Element remove = queue.remove();
			System.out.println(remove);
			arr[i] = remove.getData();
			i++;
		}

		return arr;

	}

	class Element {

		int data;
		int frequency;

		public Element(int data, int frequency) {

			super();
			this.data = data;
			this.frequency = frequency;
		}

		public int getData() {

			return data;
		}

		public void setData(int data) {

			this.data = data;
		}

		public int getFrequency() {

			return frequency;
		}

		public void setFrequency(int frequency) {

			this.frequency = frequency;
		}

		@Override
		public String toString() {

			return "Element [data=" + data + ", frequency=" + frequency + "]";
		}

	}

	class ElementComparator implements Comparator<Element> {

		@Override
		public int compare(Element o1, Element o2) {

			if (o1.getFrequency() > o2.getFrequency()) {
				return -1;
			}
			else if (o1.getFrequency() < o2.getFrequency()) {
				return 1;
			}
			return 0;
		}

	}

}
