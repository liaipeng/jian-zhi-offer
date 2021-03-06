package cn.edu.fzu.algorithm;

/*
 * 面试题：树中两个结点的最低公共祖先。
 *
 * 一、二叉搜索树
 * 		因为二叉搜索树是排序过的，位于左子树的结点都比父节点小，位于右子树的结点都比父节点大。
 * 		所以，只需要从树的根节点开始和两个输入的结点进行比较：
 * 			如果当前结点的值比两个结点的值都大，那么最低公共祖先肯定在当前结点的左子树中。
 * 			如果当前结点的值比两个结点的值都小，那么最低公共祖先肯定在当前结点的右子树中。
 * 			如果一个大一个小，那么当前结点就是最低公共祖先。
 * 
 * 二、普通的树，且树中有指向父结点的指针
 * 		那么问题就可以转换成求两个链表的第一个公共结点。
 * 		从树的每个结点开始，沿着指向父节点的指针，一直到根节点，串成了一条链表。
 * 		那么两个结点位于两条链表上，他们的最低公共祖先刚好就是两个链表的第一个公共结点。
 * 
 * 三、普通的树，没有指向父节点的指针
 * 		1. 允许使用辅助内存
 * 			用前序遍历的方法来得到从根节点分别到两个结点的路径。
 * 			用两个链表分别保存从根节点到输入的两个结点的路径。
 * 			然后把问题转换成两个链表的最后公共结点。
 * 			
 * 			例如，从根到H的路径：
 * 				(1)遍历到A，把A存放到路径中去，路径中只有一个结点A;
 * 				(2)遍历到B，把B存放到路径中去，路径为A -> B;
 * 				(3)由于B没有子节点了，因此此路径不可能到H，把B从路径中删除，路径变成A;
 * 				(4)遍历到C，把C存放到路径中，路径为A -> C;
 * 				(5)遍历H，到达目标结点，A -> C -> H就是从根节点开始到达H必须经过的路径。
 * 
 * 
 * 		2. 不允许使用辅助内存
 * 			从根节点开始遍历一棵树，每遍历到一个结点时，判断两个输入结点是不是在它的子树中。
 * 			如果在子树中，则分别遍历它的所有子节点，并判断两个输入结点是不是在它们的子树中。
 * 			这样从上到下一只找到第一个节点，它自己的子树中同时包含两个输入的结点，而它的子节点却没有，
 * 			那么该结点就是最低的公共祖先。
 * 			但是缺点是要对同一个结点遍历很多次，效率低下。
 * 
 */


public class LowestCommonAncestors {
}
