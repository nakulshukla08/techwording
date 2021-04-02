package com.techwording.practice.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeTraversalPreOrder {

	public static void main(String[] args) {

		CreateTreeFromSortedArray util = new CreateTreeFromSortedArray();
		Integer[] arr = new Integer[] { 3, 7, 9, 15, 20 };
		TreeNode root = util.execute(arr, 0, arr.length - 1);

		TreeTraversalPreOrder obj = new TreeTraversalPreOrder();
		System.out.println(obj.preorderTraversal(root));
	}

	public List<Integer> preorderTraversal(TreeNode root) {

		List<Integer> list = new ArrayList<>();
		traversePre(list, root);

		return list;
	}

	private void traversePre(List<Integer> list, TreeNode node) {

		if (node == null)
			return;

		list.add(node.val);

		if (node.left != null) {
			traversePre(list, node.left);
		}

		if (node.right != null) {
			traversePre(list, node.right);
		}
	}
}
