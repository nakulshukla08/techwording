package com.techwording.practice.tree;

public class CreateTreeFromSortedArray {

	/*
	 * A function that constructs Balanced Binary Search Tree from a sorted array
	 */
	TreeNode execute(Integer arr[], int start, int end) {

		/* Base Case */
		if (start > end) {
			return null;
		}

		/* Get the middle element and make it root */
		int mid = (start + end) / 2;
		if (arr[mid] == null)
			return null;

		TreeNode node = new TreeNode(arr[mid]);

		/*
		 * Recursively construct the left subtree and make it left child of root
		 */
		node.left = execute(arr, start, mid - 1);

		/*
		 * Recursively construct the right subtree and make it right child of root
		 */
		node.right = execute(arr, mid + 1, end);

		return node;
	}

	public TreeNode createTreeNode(Integer[] input, int index) {

		if (index <= input.length) {
			Integer value = input[index - 1];
			if (value != null) {
				TreeNode t = new TreeNode(value);
				t.left = createTreeNode(input, index * 2);
				t.right = createTreeNode(input, index * 2 + 1);
				return t;
			}
		}
		return null;
	}
}
