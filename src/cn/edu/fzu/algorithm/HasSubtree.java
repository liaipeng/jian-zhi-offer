package cn.edu.fzu.algorithm;

/*
题目描述

输入两颗二叉树A，B，判断B是不是A的子结构。
*/


/*
 * 用递归求解，代码会比迭代简洁很多
 * 
 * 见代码①
 */

public class HasSubtree {
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
    public boolean hasSubtree(TreeNode root1,TreeNode root2) {
    	if (root1 == null || root2 == null) return false; // 如果root1和root2有一棵是空的，返回false
    	// 找到树1的某一个结点，与树2的根节点val相等
    	if (root1.val == root2.val) { 
    		if (doesTreeOneHasTreeTweo(root1, root2)) return true; // 开始判断是否是子树
    		if (hasSubtree(root1.left, root2)) return true; // 如果不是，那们递归遍历树1左子树
    		if (hasSubtree(root1.right, root2)) return true; // 如果不是，那们递归遍历树1右子树
    	}
    	return false; // 没有找到
    }
    private boolean doesTreeOneHasTreeTweo(TreeNode root1, TreeNode root2) {
    	if (root2 == null) return true; // 递归到root2为null时，说明前面的都匹配了，肯定是子树
    	if (root1 == null) return false; //  如果root1已经递归到null了，而root2还不是null，返回false
    	
    	if (root1.val != root2.val) return false; // 如果两数的某一结点值不相等，肯定不是子树，返回false
    	
    	// 当前结点已经相等，递归判断左子树和右子树。
    	return doesTreeOneHasTreeTweo(root1.left, root2.left) 
    			&& doesTreeOneHasTreeTweo(root1.right, root2.right);
    }
}
