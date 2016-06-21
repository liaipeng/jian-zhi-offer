package cn.edu.fzu.algorithm;

/*
题目描述

给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
*/
/*
 * 		      a
 * 	   		/   \
 * 		   b     c
 * 		  / \   / \
 *       d   e f   g
 *          / \
 *         h   i
 *      
 *  例如上面这棵二叉树，中序遍历是d, b, h, e, i, a, f, c, g
 *  
 *  分析：
 *  
 *  1.  如果一个结点有右子树，那么它的下一个结点就是它的右子树中的最左子节点。
 *  	也就是说，从右子节点出发一直沿着指向左子节点的指针，我们就能找到它的下一个结点。例如b的下一个结点是h。
 *  
 *  2.	如果一个结点没有右子树
 *  	2.1	是其父节点的左子节点，那么它的下一个结点就是它的父节点。例如d，下一个结点是b。
 *  	2.2	是其父节点的右子节点，沿着指向父节点的指针next一直向上遍历，直到找到一个是它父节点的左子节点的结点。
 *  		如果这样的结点存在，那么这个结点的父节点就是要招的结点。 
 *  		例如，要找i的下一个结点，沿着next一直向上遍历，直到b，是它父节点a的左子节点。那么a就是要找的下一个结点。
 *  		再比如，要找g的下一个结点，沿着next一直向上遍历，由于一直到根节点a都没有找到是它父节点的左子节点的结点，所以g没有下一个结点。
 *  
 *  具体见代码①。
 *      
 * 
 */

public class NextNodeInBinaryTree {
	public class TreeLinkNode {
	    int val;
	    TreeLinkNode left = null;
	    TreeLinkNode right = null;
	    TreeLinkNode next = null;

	    TreeLinkNode(int val) {
	        this.val = val;
	    }
	}
	/*
	 * 代码①
	 */
	public TreeLinkNode getNext(TreeLinkNode node) {
        if (node == null) return null;
        
        TreeLinkNode next = null;
        // 情况1：有右子树
        if (node.right != null) {
            TreeLinkNode right = node.right;
            // 找其右子树的最左子节点
            while (right.left != null) {
                right = right.left;
            }
            next = right;
            // 
        } else if (node.next != null) {
            TreeLinkNode current = node;
            TreeLinkNode parent = node.next;
            // 进入这个循环的条件是，父节点不为空，且是其父节点的右子树，也就是情况2.2
            // 循环结束的条件是：1.找到是父节点的左子树的结点； 2.一直遍历到null
            while (parent != null && current == parent.right) {
                current = parent;
                parent = parent.next;
            }
            // 不进入循环，直接跳到这一步，就是情况2.1（是其父节点的左子树，或者没有父节点，此时没有下一个结点）
            next = parent;
        }
        
        return next;
    }
}
