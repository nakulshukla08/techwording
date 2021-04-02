package com.techwording.practice.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeTraversalInOrder {

	public static void main(String[] args) {

		CreateTreeFromSortedArray util = new CreateTreeFromSortedArray();
		Integer[] arr = new Integer[]{3,7,9,15,20};
		TreeNode root = util.execute(arr, 0, arr.length-1);
		List<Integer> inOrderTraversal = inOrderTraversal(root);
		System.out.println(inOrderTraversal);
	}

	/*
	 * In-Order Traversal : Left, Root, Right
	 */
	public static List<Integer> inOrderTraversal(TreeNode root) {

		List<Integer> list = new ArrayList<>();
		traverseIn(list, root);
		
		return list;
	}
	
	private static void traverseIn(List<Integer> list, TreeNode node )
	{
		if(node == null)
			return;
		
		if(node.left!=null)
		{
			traverseIn(list, node.left);
		}

		list.add(node.getValue());

		if(node.right!=null)
		{
			traverseIn(list, node.right);
		}

	}
}
