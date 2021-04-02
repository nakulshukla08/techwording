package com.techwording.general;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache<K, V> {

	private Map<K, Entry<V>> entries = new ConcurrentHashMap<>();

	private int size = 2;

	private Queue<K> queue = new LinkedList<>();

	private void put(K key, V value) {
		Entry<V> entry = new Entry<>();
		entry.setValue(value);
		entry.setTimestamp(System.currentTimeMillis());
		if (queue.size() >= size) {

			K remove = queue.remove();
			entries.remove(remove);
			System.out.println("evicting : " + remove);
		}
		queue.add(key);
		entries.put(key, entry);
	}

	private Entry<V> get(K key) {
		return entries.get(key);
	}

	class Entry<V> {
		private Long timestamp;

		private V value;

		public Long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Long timestamp) {
			this.timestamp = timestamp;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LRUCache<String, String> cache = new LRUCache<>();

		cache.put("A", "Aman");
		cache.put("X", "Xavior");
		cache.put("C", "Chris");
		System.out.println(cache.get("A"));
	}
}
