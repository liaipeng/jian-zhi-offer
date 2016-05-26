package cn.edu.fzu.algorithm;

/*
题目描述

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
*/

/*
 * 由于要求转换之后的链表是排好序的，所以可以终须遍历树种的每一个结点
 * 因为对BST中序遍历，其实就是按照从小到大的顺序遍历二叉树的每一个结点。
 * 
 * 按照中序遍历的顺序，当我们遍历转换到根节点的时候，它的左子树已经转换成了一个排好序的链表了。
 * 并且，处在链表中的最后一个结点是当前值最大的结点。
 * 此时，将左子树中的最大结点与根节点连接起来，值最大的结点就变成根节点。
 * 然后，我们去遍历转换右子树，并把根节点和右子树中最小的结点连接起来。
 * 
 * 核心思路就是：中序遍历BST，遍历过程把结点串起来
 * 
 * 具体见代码①
 */

public class ConvertBSTToLinkedList {
	public static class TreeNode {
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
	public TreeNode convert(TreeNode root) {
        if (root == null) return null;
        
        TreeNode lastNodeInList = null; // 指向转换好的链表的最后一个结点，也就是暂时的最大值
        lastNodeInList = convertNode(root, lastNodeInList); // 调用convertNode方法进行转换，返回链表的最后一个结点
        
        // 最后一个结点往回找，找到头结点
        while(lastNodeInList.left != null) {
        	lastNodeInList = lastNodeInList.left;
        }
         
        return lastNodeInList; // 返回链表的头结点
    }
	private TreeNode convertNode(TreeNode root, TreeNode lastNodeInList) {
		// 如果树的左子树不为空，以左子树为根进行递归。（中序遍历，先遍历左子树）
		if (root.left != null) {
			// 此时的左子树已经转换完毕，lastNodeInList已经指向该部分链表的最后一个结点
			lastNodeInList = convertNode(root.left, lastNodeInList); 
		}
		// （中序遍历，遍历根节点）
		root.left = lastNodeInList; // 根节点指向lastNodeInList，也就是左子树中的最大值
		if (lastNodeInList != null) {
			lastNodeInList.right = root; // 将根节点接到链表上
		}
		lastNodeInList = root; // 此时链表中最大的结点已经变成根节点，所以lastNodeInList移动到根节点
		
		// 中序遍历，遍历右子树）
		if (root.right != null) {
			lastNodeInList = convertNode(root.right, lastNodeInList);
		}
		
		return lastNodeInList;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		TreeNode n1 = new TreeNode(6);
		TreeNode n2 = new TreeNode(14);
		TreeNode n3 = new TreeNode(4);
		TreeNode n4 = new TreeNode(8);
		TreeNode n5 = new TreeNode(12);
		TreeNode n6 = new TreeNode(16);
		
		root.left = n1;
		root.right = n2;
		
		n1.left = n3;
		n1.right = n4;
		
		n2.left = n5;
		n2.right = n6;
		
		
		TreeNode r = new ConvertBSTToLinkedList().convert(root);
		System.out.println(r);
	}
}
