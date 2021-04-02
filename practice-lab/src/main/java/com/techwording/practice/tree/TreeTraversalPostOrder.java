package com.techwording.practice.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeTraversalPostOrder {

	public static void main(String[] args) {

		CreateTreeFromSortedArray util = new CreateTreeFromSortedArray();
		Integer[] arr = new Integer[]{3,7,9,15,20};
		TreeNode root = util.execute(arr, 0, arr.length-1);
		
		TreeTraversalPostOrder obj = new TreeTraversalPostOrder();
		
		System.out.println(obj.postorderTraversal(root));
		
	}
	
	public List<Integer> postorderTraversal(TreeNode root) {

		List<Integer> list = new ArrayList<>();
		traversePost(list, root);
		
		return list;
	}

	private void traversePost(List<Integer> list, TreeNode node) {

		if (node == null)
			return;

		if (node.left != null) {
			traversePost(list, node.left);
		}

		if (node.right != null) {
			traversePost(list, node.right);
		}
		list.add(node.val);
	}
}
