package cn.edu.fzu.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
题目描述

从上往下打印出二叉树的每个节点，同层节点从左至右打印。
*/

/*
 * 其实就是BFS嘛，用队列就行了，见代码①
 */

public class PrintFromTopToBottom {
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
    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
    	if (root == null) return new ArrayList<Integer> ();
    	ArrayList<Integer> paths = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.offer(root);
        
        while (!queue.isEmpty()) {
        	TreeNode node = queue.poll();
        	paths.add(node.val);
        	if (node.left != null) {
        		queue.offer(node.left);
        	}
        	if (node.right != null) {
        		queue.offer(node.right);
        	}
        }
        
        return paths;
    }
}
