package com.techwording.practice.tree;

public class MaxDepth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateTreeFromSortedArray util = new CreateTreeFromSortedArray();
		Integer[] arr = new Integer[]{3,7,9,15,20};
		TreeNode root = util.execute(arr, 0, arr.length-1);
		System.out.println(maxDepth(root));

	}

	public static int maxDepth(TreeNode root) {

		if(root == null)
		{
			return 0;
		}
		
		return Math.max(1+maxDepth(root.left), 1+maxDepth(root.right));
	}
}
