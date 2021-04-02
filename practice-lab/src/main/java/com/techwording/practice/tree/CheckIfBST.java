package com.techwording.practice.tree;

public class CheckIfBST {

	public static void main(String[] args) {

		CreateTreeFromSortedArray util = new CreateTreeFromSortedArray();
		Integer[] arr = new Integer[] { 10, 5, 15, null, null, 6, 20 };
		TreeNode root = util.createTreeNode(arr, 1);

		System.out.println(isValidBST(root));

	}

	public static boolean isValidBST(TreeNode root) {

		if (root == null) {
			return true;
		}

		boolean leftCheck = false;

		if (root.left != null) {
			if (root.left.val < root.val) {
				leftCheck = true;
			}
		}
		else {
			leftCheck = true;
		}

		boolean rightCheck = false;

		if (root.right != null) {
			if (root.right.val > root.val) {
				rightCheck = true;
			}
		}
		else {
			rightCheck = true;
		}

		return leftCheck && rightCheck && isValidBST(root.left) && isValidBST(root.right);

	}

	static boolean isBSTUtil(TreeNode root, TreeNode prev) {

		// traverse the tree in inorder fashion and
		// keep track of prev node
		if (root != null) {
			if (!isBSTUtil(root.left, root)) {
				return false;
			}

			// Allows only distinct valued nodes
			if (root.val <= prev.val) {
				return false;
			}

			// Initialize prev to current
			prev.val = root.val;

			return isBSTUtil(root.right, root);
		}

		return true;

	}

}
