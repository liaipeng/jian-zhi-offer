package cn.edu.fzu.algorithm;

/*
题目描述

输入一个链表，反转链表后，返回链表新的头结点。
*/

/*
 * 这题在LeetCode上做过。
 * 
 * 而在书中，这一题的的章节是“代码的鲁棒性”，需要考虑代码的一些边界条件：
 * 		1.	输入的链表头指针是NULL
 * 		2.	输入的链表只有一个结点
 * 		3.	输入的链表有多个结点
 * 
 * 代码①是递归解法，结构很清晰易懂。
 * 
 * 代码②是迭代解法，效率会比递归的高。
 */

public class ReverseList {
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
    public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) return head; // 边界条件
        ListNode next = head.next; // 记录当前结点的下一结点。
        ListNode newHead = reverseList(next); // 翻转剩余结点链表，并返回头结点。（一直递归到表尾，此时表尾就变成表头，return newHead）
        next.next = head; // 下一结点翻转，指向当前结点。
        // 当前结点指向空。
        //（其实分为两种，一种是如果是原表头，此时是表尾，指向null。另一种，非表头，那么实际上是回到上一层的递归时，由next.next = head;来翻转）
        head.next = null;
		return newHead; // 返回头结点。
    }
    
    /*
     * 代码②
     */
    public ListNode reverseList_2(ListNode head) {
		if (head == null || head.next == null) return head; // 边界条件
		
		ListNode preNode = null; // 记录前一个结点
		ListNode nextNode = null; // 记录后一个结点
		
		while (head != null) {
			nextNode = head.next; // nextNode移动到head的下一个结点
			head.next = preNode; // head的next指向前一个结点preNode
			preNode = head; // preNode移动到head
			head = nextNode; // head移动到nextNode
		}
		
		return preNode; // 当循环结束时，head移动到尾结点后面的null，preNode就移动到了原尾结点，也就是新的头结点。
    }
}
