package cn.edu.fzu.algorithm;

/*
题目描述

输入一个链表，输出该链表中倒数第k个结点。
*/

/*
 * 这题是出现在“代码的鲁棒性”一节。要考虑代码的容错性。
 * 
 * 比如，题目的第k个结点，我们得向面试官确认，是从0开始还是从1开始计数的。（本题是从1开始计数的）
 * 还有，如果链表没有k个结点，应该返回什么。
 * 
 * 而题目本身没什么难的，用快慢指针，快指针比慢指针快k个结点，
 * 那么当快指针为null的时候，慢指针刚好指向倒数第k个结点（如果是从0开始计数的，那就是快指针指向尾结点的时候）
 * 
 * 见代码①
 */

public class FindKthNodeToTail {
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
    public ListNode findKthToTail(ListNode head,int k) {
		if (head == null || k <= 0) return null; // 鲁棒性判断点1：head为空。  鲁棒性判断点2：k如果是负数或者为0，也返回空
        
        ListNode fast = head, slow = head;
        
        // 快指针先走k步
        for (int i = 0; i < k; i++) {
            if (fast == null) return null; // 鲁棒性判断点3：如果链表都没有k长，那显然不可能有倒数第k个结点
            fast = fast.next;
        }
        
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        return slow;
    }
}
