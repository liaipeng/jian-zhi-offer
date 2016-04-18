package cn.edu.fzu.algorithm;


/*
题目描述

操作给定的二叉树，将其变换为源二叉树的镜像。 
输入描述:
二叉树的镜像定义：源二叉树 
    	    8
    	   /  \
    	  6   10
    	 / \  / \
    	5  7 9 11
    	镜像二叉树
    	    8
    	   /  \
    	  10   6
    	 / \  / \
    	11 9 7  5
*/

/*
 * 这题的重点是，拿到题目之后，先在图上画出题目中的例子（加入题目没有提供图）
 * 然后就可以发现，镜像就是递归交换左右子树
 * 
 * 用递归求解，见代码①
 */

public class MirrorOfBinaryTree {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }

	}
	
	/*
	 * 代码①
	 */
    public void mirror(TreeNode root) {
    	// 如果根为空，或者只有一个根，无需交换，return
    	if (root == null) return;
    	if (root.left == null && root.right == null) return;
    	
    	// 交换左右子树
    	TreeNode tempNode = root.left;
    	root.left = root.right;
    	root.right = tempNode;
    	
    	// 以左子树或右子树为根，递归调用mirror
    	if (root.left != null) {
    		mirror(root.left);
    	}
    	if (root.right != null) {
    		mirror(root.right);
    	}
    }
}
