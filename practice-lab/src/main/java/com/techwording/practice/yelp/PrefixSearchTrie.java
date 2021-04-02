package com.techwording.practice.yelp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrefixSearchTrie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Trie trie = createExampleTrie();

		System.out.println(trie);

		List<String> search = trie.search("Pro");

		System.out.println(search);

	}

	private static Trie createExampleTrie() {

		Trie trie = new Trie();

		trie.insert("Programming");
		trie.insert("Process");
		/*
		 * trie.insert("is"); trie.insert("a"); trie.insert("way"); trie.insert("of"); trie.insert("life"); trie.insert("ball");
		 * trie.insert("bald");
		 */

		return trie;
	}

}

class TrieNode {

	Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	List<String> words;
	Character c;

	public Character getC() {

		return c;
	}

	public void setC(Character c) {

		this.c = c;
	}

	boolean isEnd;

	public Map<Character, TrieNode> getChildren() {

		return children;
	}

	public void setChildren(Map<Character, TrieNode> children) {

		this.children = children;
	}

	public List<String> getWords() {

		return words;
	}

	public void setWords(List<String> words) {

		this.words = words;
	}

	public boolean isEnd() {

		return isEnd;
	}

	public void setEnd(boolean isEnd) {

		this.isEnd = isEnd;
	}

}

class Trie {

	private TrieNode root = new TrieNode();

	public void insert(String word) {

		TrieNode current = root;

		for (char l : word.toCharArray()) {

			Map<Character, TrieNode> children = current.getChildren();

			TrieNode trieNode = null;
			if (children.containsKey(l)) {
				trieNode = children.get(l);

			}
			else {
				trieNode = new TrieNode();
				trieNode.setC(l);
				children.put(l, trieNode);
			}

			List<String> words = trieNode.getWords();
			if (words == null) {
				words = new ArrayList<>();
			}
			words.add(word);
			trieNode.setWords(words);
			current = trieNode;
			/*
			 * current = children .computeIfAbsent(l, c -> { TrieNode node = new TrieNode(); node.setC(l); List<String> words =
			 * node.getWords(); if (words == null) { words = new ArrayList<>(); } words.add(word); node.setWords(words); return
			 * node; });
			 */
		}
		current.setEnd(true);
	}

	public List<String> search(String prefix) {

		char[] charArray = prefix.toCharArray();

		Map<Character, TrieNode> children = root.getChildren();
		List<String> words = null;
		for (Character c : charArray) {
			TrieNode trieNode = children.get(c);
			if (trieNode != null) {
				children = trieNode.getChildren();
				words = trieNode.getWords();
				System.out.println("Word for : " + c + " : " + words);
			}
		}

		return words;

	}
}
