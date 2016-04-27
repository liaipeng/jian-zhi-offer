package cn.edu.fzu.algorithm;

/*
题目：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。假设输入的数组的任意两个数字都互不相同。
*/

/*
 * 这种题目举例子帮助分析：
 * 比如数组[5,7,6,9,11,10,8]。
 * 		因为是后续遍历，所以最后一个数组8是根节点。那么根据BST的性质，[5,7,6]比8小是其左子树，[9,11,10]比8大是其右子树。
 * 		然后对左右子树递归执行上述过程，直到数组为空，返回true。
 * 若是数组[7,4,6,5]：
 * 		根节点是5，由于7比5大，所以没有左子树，[7,4,6]是其右子树。
 * 		但是，该子树中，4比5小，不符合BST的性质，返回false。
 * 
 * 见代码①
 */

public class VerifySquenceOfBST {
	/*
	 * 代码①
	 */
	public boolean verifySquenceOfBST(int[] sequence) {
		if (sequence == null || sequence.length == 0) return false;
		return verifySubTree(sequence, 0, sequence.length-1);
	}
	private boolean verifySubTree(int[] subSeq, int start, int end) {
		if (start >= end) return true;
		int root = subSeq[end];
		int leftEnd = start;
		// 划分左子树
		for (int i = start; i < end-1; i++) {
			if (subSeq[i] > root) break; // 大于root，此处开始是右子树
			leftEnd = i;
		}
		
		// 剩下的就是右子树，判断右子树是否符合规范
		for (int i = leftEnd+1; i < end-1; i++) {
			if (subSeq[i] < root) return false; // 右子树中有小于root的数字，不符合规范。
		}
		
		return verifySubTree(subSeq, start, leftEnd) && verifySubTree(subSeq, leftEnd+1, end-1);
	}
}
