package com.techwording.practice.tree;

import java.util.Objects;

public class TreeNode {

	TreeNode() {

	}

	TreeNode(int val) {

		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {

		this.val = val;
		this.left = left;
		this.right = right;
	}

	int val;
	TreeNode left;
	TreeNode right;

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

	public int getValue() {

		return val;
	}

	public void setValue(int value) {

		this.val = value;
	}

	@Override
	public int hashCode() {

		return Objects.hash(val);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TreeNode other = (TreeNode) obj;
		return val == other.val;
	}

}
