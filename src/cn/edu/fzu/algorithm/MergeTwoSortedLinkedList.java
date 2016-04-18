package cn.edu.fzu.algorithm;

/*
题目描述

输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
*/


/*
 * 题目还是很简单的
 * 
 * 迭代解法，代码①
 * 递归解法，代码②
 */

public class MergeTwoSortedLinkedList {
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
    public ListNode mergeTwoSortedLinkedList(ListNode list1,ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        ListNode newHead = new ListNode(-1), cur = newHead;
        
        while (list1 != null && list2 != null) {
        	if (list1.val < list2.val) {
        		cur.next = list1;
        		list1 = list1.next;
        	} else {
        		cur.next = list2;
        		list2 = list2.next;
        	}
        	cur = cur.next;
        }
        // 把剩余的链表接上
        if (list1 != null) {
        	cur.next = list1;
        } else {
        	cur.next = list2;
        }
        
        return newHead.next;
    }
    
	/*
	 * 代码②
	 */
    public ListNode mergeTwoSortedLinkedList_2(ListNode list1,ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        ListNode newHead = null;
        
        if (list1.val < list2.val) {
        	newHead = list1;
        	newHead.next = mergeTwoSortedLinkedList_2(list1.next, list2); // 递归合并剩余链表，返回头结点
        } else {
        	newHead = list2;
        	newHead.next = mergeTwoSortedLinkedList_2(list2.next, list1); // 递归合并剩余链表，返回头结点
        }
        
        return newHead;
    }
}
