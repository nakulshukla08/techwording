package com.techwording.practice.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NodeKDistanceAway {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CreateTreeFromSortedArray util = new CreateTreeFromSortedArray();

		Integer[] arr = new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };
		TreeNode root = util.execute(arr, 0, arr.length - 1);

		NodeKDistanceAway solution = new NodeKDistanceAway();

		TreeNode target = new TreeNode(5);
		List<Integer> distanceK = solution.distanceK(root, target, 2);

		System.out.println(distanceK);
	}

	Map<TreeNode, TreeNode> parentChildMap = new HashMap<>();
	Set<TreeNode> set = new HashSet<>();

	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

		populateMap(root, null);

		TreeNode treeNode = parentChildMap.get(target);

		return getNodesAtKDistance(treeNode, K);

	}

	private List<Integer> getNodesAtKDistance(TreeNode source, int k) {

		List<Integer> result = new ArrayList<Integer>();

		if (source == null) {
			return result;
		}

		if (set.contains(source)) {
			return result;
		}

		set.add(source);

		if (k == 0) {
			result.add(source.val);
		}

		List<Integer> parentResult = getNodesAtKDistance(parentChildMap.get(source), k - 1);

		List<Integer> leftChildResult = getNodesAtKDistance(source.left, k - 1);

		List<Integer> rightChildResult = getNodesAtKDistance(source.right, k - 1);

		result.addAll(rightChildResult);
		result.addAll(leftChildResult);
		result.addAll(parentResult);

		return result;
	}

	private void populateMap(TreeNode root, TreeNode parent) {

		if (root != null) {
			parentChildMap.put(root, parent);
			populateMap(root.left, root);
			populateMap(root.right, root);
		}
	}
}
