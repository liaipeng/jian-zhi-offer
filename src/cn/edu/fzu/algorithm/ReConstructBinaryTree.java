package cn.edu.fzu.algorithm;

/*
 题目描述

 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */

public class ReConstructBinaryTree {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private TreeNode constructBinaryTree(int[] pre, int preStart, int preEnd,
			int[] in, int inStart, int inEnd) {
		// 前序遍历第一个数为当前子树的根节点
		int rootValue = pre[preStart];
		TreeNode root = new TreeNode(rootValue);

		// 如果数组中已没有元素，构建完毕
		if (preStart == preEnd) {
			return root;
		}

		// 在中序遍历序列中寻找到根
		int rootIndex = inStart;
		for (; rootIndex <= inEnd && in[rootIndex] != rootValue; rootIndex++)
			;

		// 计算左子树长度
		int leftLength = rootIndex - inStart;

		// 建立左子树
		if (leftLength != 0) {
			// 左子树的前序遍历是从preStart+1到preStart+leftLength
			root.left = constructBinaryTree(pre, preStart + 1, preStart
					+ leftLength, in, inStart, rootIndex - 1);
		}
		if (rootIndex != inEnd) {
			// 右子树的前序遍历是从preStart+leftLength+1, preEnd
			root.right = constructBinaryTree(pre, preStart + leftLength + 1,
					preEnd, in, rootIndex + 1, inEnd);
		}

		return root;

	}

	/*
	 * 代码①
	 */
	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		if (pre.length == 0 || in.length == 0 || pre.length != in.length) return null;
		return constructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}
}
