package com.techwording.practice.tree;

import java.util.ArrayList;
import java.util.List;

public class FindNodesAtGivenDistance {

	public static void main(String[] args) {

		int x = 1234567 / 1000000;
		System.out.println(x);
		int y = 1234567 % 1000000;
		System.out.println(y);
		// TODO Auto-generated method stub

		/*
		 * 1
		 *
		 * 2 3 4 5 6 7
		 *
		 *
		 * HashMap <Node, Node> childParentMap =
		 */

		Node root = new Node(4);

		Node one = new Node(5);

		Node two = new Node(6);

		Node three = new Node(7);

		Node four = new Node(8);

		one.setLeft(three);
		one.setRight(four);

		root.setLeft(one);
		root.setRight(two);

		Node source = new Node(5);

		List<Node> nodesAtGivenDistanceUtil = getNodesAtGivenDistance(root, source, 2);

		System.out.println(nodesAtGivenDistanceUtil);
	}

	private static List<Node> getNodesAtGivenDistance(Node root, Node source, int n) {

		NodeWrapper populateWrapper = populateWrapper(root, null);

		NodeWrapper sourceWrapper = findWrapper(populateWrapper, source);

		return getNodesAtGivenDistanceUtil(sourceWrapper, n);

	}

	private static NodeWrapper findWrapper(NodeWrapper populateWrapper, Node source) {

		if (populateWrapper == null) {
			return null;
		}

		if (populateWrapper.getData() == source.getData()) {
			return populateWrapper;
		}

		NodeWrapper left = findWrapper(populateWrapper.getLeft(), source);

		if (left == null) {
			return findWrapper(populateWrapper.getRight(), source);
		}

		return left;
	}

	private static List<Node> getNodesAtGivenDistanceUtil(NodeWrapper node, int n) {

		List<Node> result = new ArrayList<Node>();

		if (node == null || node.isVisited()) {
			return result;
		}

		if (n == 0) {
			result.add(new Node(node.getData()));
			return result;
		}

		node.setVisited(true);

		List<Node> parentResult = getNodesAtGivenDistanceUtil(node.getParent(), n - 1);

		List<Node> leftChildResult = getNodesAtGivenDistanceUtil(node.getLeft(), n - 1);

		List<Node> rightChildResult = getNodesAtGivenDistanceUtil(node.getRight(), n - 1);

		result.addAll(parentResult);
		result.addAll(leftChildResult);
		result.addAll(rightChildResult);
		return result;
	}

	private static List<Node> getNodesAtGivenDistanceUtil(Node node, int n) {

		List<Node> result = new ArrayList<Node>();

		if (node == null) {
			return result;
		}

		if (n == 0) {
			result.add(node);
			return result;
		}

		List<Node> leftResult = getNodesAtGivenDistanceUtil(node.getLeft(), n);

		List<Node> rightResult = getNodesAtGivenDistanceUtil(node.getRight(), n);

		result.addAll(rightResult);
		result.addAll(leftResult);

		return result;
	}

	private static NodeWrapper populateWrapper(Node root, Node parent) {

		NodeWrapper newRoot = null;
		if (root != null) {
			newRoot = new NodeWrapper(root.getData());

			if (parent != null) {
				NodeWrapper parentWrapper = new NodeWrapper(parent.getData());
				newRoot.setParent(parentWrapper);
			}

			if (root.getLeft() != null) {
				newRoot.setLeft(populateWrapper(root.getLeft(), root));
			}

			if (root.getRight() != null) {
				newRoot.setRight(populateWrapper(root.getRight(), root));
			}
		}

		return newRoot;
	}

}

class NodeWrapper {

	int data;

	private NodeWrapper left;

	private NodeWrapper right;

	private NodeWrapper parent;

	private boolean visited;

	public NodeWrapper(int data) {

		super();
		this.data = data;
	}

	public int getData() {

		return data;
	}

	public void setData(int data) {

		this.data = data;
	}

	public boolean isVisited() {

		return visited;
	}

	public void setVisited(boolean visited) {

		this.visited = visited;
	}

	public NodeWrapper getLeft() {

		return left;
	}

	public void setLeft(NodeWrapper left) {

		this.left = left;
	}

	public NodeWrapper getRight() {

		return right;
	}

	public void setRight(NodeWrapper right) {

		this.right = right;
	}

	public NodeWrapper getParent() {

		return parent;
	}

	public void setParent(NodeWrapper parent) {

		this.parent = parent;
	}

}

/*
 * class NodeWrapper {
 *
 * Node current;
 *
 * Node parent;
 *
 * boolean visited;
 *
 * }
 */

class Node {

	private int data;

	public Node(int data) {

		super();
		this.data = data;
	}

	private Node left;

	private Node right;

	public int getData() {

		return data;
	}

	public void setData(int data) {

		this.data = data;
	}

	public Node getLeft() {

		return left;
	}

	public void setLeft(Node left) {

		this.left = left;
	}

	public Node getRight() {

		return right;
	}

	public void setRight(Node right) {

		this.right = right;
	}

	@Override
	public String toString() {

		return "Node [data=" + data + "]";
	}

}
