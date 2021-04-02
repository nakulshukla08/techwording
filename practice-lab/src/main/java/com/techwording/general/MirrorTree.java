package com.techwording.general;

public class MirrorTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * 1 2 3
	 *
	 * 4 5 6 7
	 *
	 *
	 *
	 * 1
	 *
	 * 3 2
	 *
	 * 7 6 5 4
	 */

	private TreeNode mirror(TreeNode root) {

		if (root == null) {
			return null;
		}

		TreeNode left = root.getLeft();
		TreeNode temp = left;
		TreeNode right = root.getRight();
		left = right;
		right = temp;

		root.setLeft(left);
		root.setRight(right);

		mirror(left);
		mirror(right);

		return root;

	}

	class TreeNode {

		private int data;

		private TreeNode left;

		private TreeNode right;

		public int getData() {

			return data;
		}

		public void setData(int data) {

			this.data = data;
		}

		public TreeNode getLeft() {

			return left;
		}

		public void setLeft(TreeNode left) {

			this.left = left;
		}

		public TreeNode getRight() {

			return right;
		}

		public void setRight(TreeNode right) {

			this.right = right;
		}

	}

}
