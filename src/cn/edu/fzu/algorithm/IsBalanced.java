package cn.edu.fzu.algorithm;

/*
题目描述

输入一棵二叉树，判断该二叉树是否是平衡二叉树。
*/

/*
 * LeetCode原题，具体见代码
 */
public class IsBalanced {
	public class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;
		public TreeNode(int val) {
	        this.val = val;
	    }
	}
	/*
	 * 计算树高的同时，判断是否平衡
	 */
	private int DFSDepth(TreeNode root) {
		if (root == null) return 0;
		
		int left = DFSDepth(root.left);
		if (left == -1) return -1; //如果左子树树高为-1，说明左子树是不平衡的，直接返回-1
		
		int right = DFSDepth(root.right);
		if (right == -1) return -1; // 同上
		
		if (Math.abs(left - right) > 1) return -1;  //如果左右子树树高相差大于1，返回-1，表示不平衡 
		
		return Math.max(left, right) + 1; //否则，返回当前根的树高
	}
    public boolean isBalanced_Solution(TreeNode root) {
    	//如果root为null，那么DFSDepth结果为0，显然不等于-1，返回true
        return DFSDepth(root) != -1;
    }
}
