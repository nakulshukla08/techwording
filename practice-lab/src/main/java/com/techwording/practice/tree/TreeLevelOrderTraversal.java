package com.techwording.practice.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeLevelOrderTraversal {

	public static void main(String[] args) {

		CreateTreeFromSortedArray util = new CreateTreeFromSortedArray();
		Integer[] arr = new Integer[] { 3, 7, 9, 15, 20 };
		TreeNode root = util.execute(arr, 0, arr.length - 1);
		TreeLevelOrderTraversal obj = new TreeLevelOrderTraversal();
		List<List<Integer>> levelOrder = obj.levelOrder(root);
		System.out.println(levelOrder);
	}

	/*
	 * public api for level order traversal
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {

		List<List<Integer>> list = new ArrayList<List<Integer>>();
		return level(root, list);
	}

	private List<List<Integer>> level(TreeNode root, List<List<Integer>> list) {

		if (root == null) {
			return Collections.emptyList();
		}

		Queue<TreeNode> queue = new LinkedList<>();

		queue.add(root);

		while (!queue.isEmpty()) {

			int size = queue.size();
			List<Integer> subList = new ArrayList<>();
			while (size > 0) {
				TreeNode node = queue.remove();
				if (node == null) {
					continue;
				}
				subList.add(node.val);
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}

				size--;
			}

			list.add(subList);
		}

		return list;

	}

}
