package com.techwording.general;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class LFUCache<K, V extends Element<K>> {

	private Map<K, Element<K>> cache;

	private Queue<Element<K>> queue;

	private int capacity;

	public LFUCache(int capacity) {

		super();
		this.cache = new HashMap<>();
		this.queue = new PriorityQueue<>();
		this.capacity = capacity;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LFUCache<String, Element<String>> cache = new LFUCache<>(2);

		StringWrapper one = new StringWrapper("Aman");
		StringWrapper two = new StringWrapper("Xavior");
		StringWrapper three = new StringWrapper("Chris");
		StringWrapper four = new StringWrapper("David");
		cache.put(one.getValue(), one);
		cache.put(two.getValue(), two);
		cache.get(one.getValue());
		cache.get(one.getValue());
		cache.put(three.getValue(), three);
		cache.put(four.getValue(), four);
		System.out.println(cache.get(one.getValue()));

	}

	public Element<K> get(K key) {

		Element<K> element = cache.get(key);

		if (element != null) {
			element.increment();
		}

		return element;

	}

	public void put(K key, Element<K> value) {

		if (queue.size() >= capacity) {

			Element<K> poll = queue.poll();

			System.out.println("removing : " + poll);
			Element<K> remove = cache.remove(poll.getValue());

		}

		cache.put(key, value);
		queue.add(value);

	}

}

class Element<T> implements Comparable<Element<T>> {

	private int useCounter = 0;

	private T value;

	T getValue() {

		return this.value;
	}

	public int getUseCounter() {

		return useCounter;
	}

	public void setUseCounter(int useCounter) {

		this.useCounter = useCounter;
	}

	public void increment() {

		useCounter++;
	}

	@Override
	public int compareTo(Element<T> o) {

		System.out.println("sorting : " + getValue() + " vs : " + o.getValue());
		// TODO Auto-generated method stub
		return o.getUseCounter() - useCounter;
	}

}

class StringWrapper extends Element<String> {

	private String value;

	public StringWrapper(String value) {

		super();
		this.value = value;
	}

	@Override
	public String getValue() {

		return value;
	}

	public void setValue(String value) {

		this.value = value;
	}

	@Override
	public String toString() {

		return "StringWrapper [value=" + value + "]";
	}

}
