package cn.edu.fzu.algorithm;

/*
题目描述

输入两个链表，找出它们的第一个公共结点。
*/

/*
 * LeetCode中的原题
 * 
 * 解法一：
 * 	首先，分别计算出两条链表的长度。
 * 	然后，长的链表先走，直到剩余长度与短链表相等。
 * 	接着只需要同时遍历两条链表，找到相同的结点即为第一个公共结点。
 * 
 * 	具体见代码①
 * 
 * 	另外，可以用栈来实现该思想。遍历的过程把结点都放入栈中
 * 	这样一来，栈顶元素是表尾，只需要从表尾往前进行比较，找到的最后一个相等的结点即为第一个公共结点。
 * 	但是此方法需要O(m+n)的额外空间。
 * 
 * 解法二：
 * 	把两条链表进行连接，具体见LeetCode中的解法。
 * 
 */

public class FindFirstCommonNode {
	public class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
	
	/*
	 * 代码①
	 */
    public ListNode findFirstCommonNode(ListNode head1, ListNode head2) {
    	 if (head1 == null || head2 == null) return null;
    	 
    	 ListNode cur1 = head1, cur2 = head2;
    	 int len1 = getLength(head1);
    	 int len2 = getLength(head2);
    	 
    	 while (len1 > len2) {
    		 cur1 = cur1.next;
    		 len1 --;
    	 }
    	 while (len2 > len1) {
    		 cur2 = cur2.next;
    		 len2 --;
    	 }
    	 
    	 while (cur1 != null) {
    		 if (cur1.val == cur2.val) return cur1;
    		 cur1 = cur1.next;
    		 cur2 = cur2.next;
    	 }
    	 
    	 return null;
    }
    private int getLength(ListNode head) {
    	ListNode cur = head;
    	int count = 0;
    	while (cur != null) {
    		count ++;
    		cur = cur.next;
    	}
    	
    	return count;
    }
}
