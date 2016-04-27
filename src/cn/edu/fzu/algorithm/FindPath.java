package cn.edu.fzu.algorithm;

import java.util.ArrayList;

/*
题目描述

输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
*/

/*
 * 这边的路径，必须是从根节点开始，叶节点结束的路径。
 * 然后，树的题目，就用递归吧
 * 
 * 见代码①
 */

public class FindPath {
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
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root,int target) {
        if (root == null) return new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        
        find(root, paths, new ArrayList<Integer>(), target);
        
        return paths;
    }
    private void find(TreeNode root, ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> path, int remain){
    	// remain是target沿路下来减去经过结点的val剩下的值，如果此时remain刚好等于遇到的结点的大小，且该结点是叶节点，找到一条符合的路径。
    	if (remain == root.val && (root.left == null && root.right == null)) {
    		path.add(root.val); // 添加此叶节点
    		paths.add(new ArrayList<>(path)); // 得到一条符合的路径
    		path.remove(path.size()-1); // 移除此结点，回到上一层继续遍历。
    		return;
    	} else {
    		path.add(root.val); // 将当前结点加入路径
    		if (root.left != null) find(root.left, paths, path, remain - root.val); // 若左子树不为空，遍历左子树。
    		if (root.right != null) find(root.right, paths, path, remain - root.val); // 遍历右子树
    		path.remove(path.size()-1); // 移除此结点，回到上一层继续遍历。
    	}
    }
}
