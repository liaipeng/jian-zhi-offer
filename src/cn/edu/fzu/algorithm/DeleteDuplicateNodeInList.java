package cn.edu.fzu.algorithm;

/*
题目描述

在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
*/

public class DeleteDuplicateNodeInList {
	public class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
	public ListNode deleteDuplication(ListNode head) {
		if (head == null) return head;
        
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode preNode = newHead;
        ListNode node = head;
        
        while (node != null && node.next != null) {
        	// 如果下一个结点与这个结点值相等
            if (node.next.val == node.val) {
                int val = node.val;
                // 一直跳到不重复的那个表结点
                while (node != null && node.val == val) {
                    node = node.next;
                }
                preNode.next = node; // 指向那个不重复的结点
            } else {
                preNode = preNode.next;
                node = node.next;
            }
        }
        return newHead.next;
    }
}
