package cn.edu.fzu.algorithm;

import java.util.Stack;

/*
题目描述

给定一颗二叉搜索树，请找出其中的第k大的结点。例如， 5 / \ 3 7 /\ /\ 2 4 6 8 中，按结点数值大小顺序第三个结点的值为4。
*/
/*
 * 如果按照中序遍历的顺序遍历一颗二叉搜索树，遍历序列的数值是递增排序的。
 * 因此，只需要用中序遍历算法遍历一棵二叉搜索树，很容易找出它的第k大结点。
 * 
 * 见代码①
 * 
 * 除此之外，还可以使用二分搜索。
 * 见代码②
 * 
 */

public class KthBiggestNodeInBST {
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
	TreeNode kthNode(TreeNode root, int k) {
        if (root == null || k < 1) return null;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()){
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            curNode = stack.pop();
            if (k == 1) return curNode;
            k--;
            curNode = curNode.right;
        }
        return null; // 循环内肯定能找到，这边无所谓了
    }
	
	/*
     * 代码③
     * 
     */
    // 返回以root为根的树的节点数量
    private int countNode (TreeNode root) {
        if (root == null) return 0;
        return 1 + countNode(root.left) + countNode(root.right);
    }
    public TreeNode kthNode_2(TreeNode root, int k) {
    	if (root == null || k < 1) return null;
        int leftNode = countNode(root.left); // 计算左子树节点数
        // 如果k小于等于左子树节点数，那么第k小的数在左子树中，以root.left为根递归找左子树中第k大的数
        if (k <= leftNode) {
            return kthNode_2(root.left, k);
            // 如果k大于左子树个数+1，那么第k小的数在右子树中，以root.right为根递归找右子树中第 k - 1 - leftNode大的数
        } else if (k > leftNode + 1) {
            return kthNode_2(root.right, k - 1 - leftNode);
        }
        // 否则，当前结点即为第k大的节点
        return root;
    }
}
