package cn.edu.fzu.algorithm;

/*
题目描述

请实现两个函数，分别用来序列化和反序列化二叉树
*/

/*
 * 我们可以根据前序遍历的顺序来序列化二叉树，因为前序遍历是从根节点开始的。
 * 当在遍历二叉树碰到null时，序列化成一个特殊的字符#
 * 结点之间用,隔开。
 * 例如
 * 			 1
 * 		   /   \
 * 		  2	    3
 *       /     / \
 *      4     5   6
 *  可以序列化成"1,2,4,#,#,#,3,5,#,#6,#,#"
 *  见代码中的serialize方法
 *  
 *  接着是反序列化。
 *  第一个读出的数字是1。
 *  由于前序遍历是从根节点开始的，这是根节点的值。
 *  接下来读出的数字是数字2，根据前序遍历的规则，这是根节点的左子节点的值。
 *  同样地，接下来的数字4是值为2的结点的左子节点。
 *  接着读出两个#，表明值为4的结点的左右子节点均为null，因此它是一个叶节点。
 *  接下来回到值为2的结点，重建它的右子节点。
 *  由于下一个字符是#，表明值为2的结点的右子节点为null。
 *  以此类推，
 *  具体见deserialize方法
 *     
 */
public class SerializeBinaryTree {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }
	}
    /*
     * 序列化
     */
    String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder("");
        if (root == null) {
            sb.append("#,"); // null结点，加入#
        } else {
            sb.append(root.val + ",");
            sb.append(serialize(root.left)); // 递归序列化左子树
            sb.append(serialize(root.right)); // 递归序列化右子树
        }
        return sb.toString();
  	}
    
    /*
     * 反序列化
     */
    private int index = -1;
    
    TreeNode deserialize(String str) {
        String[] values = str.split(",");
        return deserialize(values);
  	}
    TreeNode deserialize(String[] values) {
    	index ++; // index++必须放在前面，否则递归的时候会死循环
        if (index > values.length) return null;
        TreeNode node = null;
        if (!"#".equals(values[index])) {
            node = new TreeNode(Integer.parseInt(values[index]));
            node.left = deserialize(values); // 递归序列化左子树
            node.right = deserialize(values); // 递归序列化右子树
        }
        
        return node;
  	}
    
}
