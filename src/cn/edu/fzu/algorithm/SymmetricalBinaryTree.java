package cn.edu.fzu.algorithm;

/*
 题目描述

 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
/*
 * LeetCode原题: Symmetric Tree
 */

public class SymmetricalBinaryTree {
	public class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;

		}
	}

	boolean isSymmetrical(TreeNode root) {
		return (null == root) || isSym(root.left, root.right);
	}
	private boolean isSym(TreeNode left, TreeNode right) {
		if ((null == left) && (null == right))
			return true;
		if ((null == left) || (null == right))
			return false;
		return (left.val == right.val) && isSym(left.left, right.right)
				&& isSym(left.right, right.left);
	}

}
