package cn.edu.fzu.algorithm;

import java.util.ArrayList;
import java.util.Stack;
/*
题目描述

输入一个链表，从尾到头打印链表每个节点的值。 
输入描述:
输入为链表的表头


输出描述:
输出为需要打印的“新链表”的表头

*
*	用栈就可以了，见代码①
*
*/

public class PrintListFromTailToHead {
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
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> al = new ArrayList<>();
        if (listNode == null) return al;
        Stack<ListNode> stack  = new Stack<>();
        while (listNode != null) {
            stack.push(listNode);
            listNode = listNode.next;
        }
         
        while (!stack.isEmpty()) {
            al.add(stack.pop().val);
        }
         
        return al;
    }
}
