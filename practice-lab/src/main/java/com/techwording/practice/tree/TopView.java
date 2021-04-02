package com.techwording.practice.tree;

import java.util.HashMap;
import java.util.Map;

public class TopView {

	public static void main(String[] args) {

		CreateTreeFromSortedArray util = new CreateTreeFromSortedArray();
		Integer[] arr = new Integer[] { 3, 7, 9, 15, 20 };
		TreeNode root = util.execute(arr, 0, arr.length - 1);
		topView(root);
		System.out.println(nodeInfoMap);

	}

	static Map<Integer, NodeInfo> nodeInfoMap = new HashMap<>();

	private static void topView(TreeNode root) {
		inOrder(root, 0, 0);
	}

	private static void inOrder(TreeNode node, int horizontalDistance, int level) {
		if (node == null) {
			return;
		}
		if (node.left != null) {
			NodeInfo nodeInfo = nodeInfoMap.get(horizontalDistance + 1);
			if (nodeInfo != null && nodeInfo.getLevel() > (level + 1)) {
				nodeInfoMap.put(horizontalDistance + 1, new NodeInfo(node.left, level + 1));
			}

			inOrder(node.left, horizontalDistance + 1, level + 1);
		}

		nodeInfoMap.put(horizontalDistance, new NodeInfo(node, level));
		if (node.right != null) {
			NodeInfo nodeInfo =  nodeInfoMap.get(horizontalDistance - 1);
			if (nodeInfo != null &&  nodeInfo.getLevel() > (level + 1)) {
				nodeInfoMap.put(horizontalDistance - 1, new NodeInfo(node.right, level + 1));
			}
			inOrder(node.right, horizontalDistance - 1, level + 1);
		}
	}

	static class NodeInfo {
		TreeNode node;

		public NodeInfo(TreeNode node, int level) {
			super();
			this.node = node;
			this.level = level;
		}

		int level;

		public TreeNode getNode() {
			return node;
		}

		public void setNode(TreeNode node) {
			this.node = node;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

	}

}
