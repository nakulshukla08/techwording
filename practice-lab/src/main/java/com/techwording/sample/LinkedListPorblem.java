package com.techwording.sample;

import java.util.LinkedList;
import java.util.List;

public class LinkedListPorblem {

	public static void main(String[] args) {

		List<Integer> list = new LinkedList<>();

	}

	private Node createLinkedList() {

		Node root = new Node(1);
		Node node1 = new Node(2);
		root.setNext(node1);

		return root;
	}

	class Node {

		public Node(Integer val) {

			this.value = val;
		}

		private Integer value;

		private Node next;

		public Integer getValue() {

			return value;
		}

		public void setValue(Integer value) {

			this.value = value;
		}

		public Node getNext() {

			return next;
		}

		public void setNext(Node next) {

			this.next = next;
		}

	}

	class Stack {

		private Node root;

		private Node push(Node next) {

			if (root != null) {

				Node temp = root;
				next.setNext(root);
			}
			return next;

		}

		private Node pop(Node root) {

			Node poopedNode = new Node(null);
			if (this.root != null) {

				Node temp = this.root.getNext();
				poopedNode.setValue(root.getValue());
				this.root = temp;
			}
			return poopedNode;
		}
	}

}
