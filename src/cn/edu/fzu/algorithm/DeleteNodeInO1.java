package cn.edu.fzu.algorithm;

/*
 * 题目：给定单向链表的头指针和一个结点指针，定义一个函数在O(1)时间删除该结点。
 */

/*
 * 题目要求O(1)时间内删除一个结点。
 * 如果是普通的做法肯定是不行的，因为删除一个结点，首先得拿到该结点的前一个结点，才能把前一个结点的next指向该节点的下一个结点，从而删除该结点。
 * 这样的话就得遍历链表，需要O(n)时间
 * 
 * 所以，转换思路。
 * 变成将要删除的结点的value改为下一个结点的value，然后删除下一个结点。这样一来就不用遍历链表了。
 * 
 * 同时，这题还考察代码的完整性:
 * 		1. 要删除的结点如果是表尾，由于表尾没有下一个结点，所以此时只能遍历链表来获取前一个结点。
 * 		2. 要删除的链表只有一个结点，即是表尾又是表头，那么就把head置位null即可
 * 
 * 见代码①
 * 
 * 值得注意的是，上述代码仍然不是完美的代码。因为它是基于一个假设：要删除的结点的确在链表中。
 * 否则，肯定需要O(n)的时间来判断结点是否存在。
 * 
 * 所以，在面试的时候，完美可以和面试官讨论这个假设，这样面试官就会觉得我们考虑问题全面。
 */

public class DeleteNodeInO1 {
	static class ListNode {
		int value;
		ListNode next;
		public ListNode(int value, ListNode next) {
			this.value = value;
			this.next = next;
		}
	}
	
	/*
	 * 代码①
	 */
	public static ListNode deleteNodeInO1(ListNode head, ListNode toBeDeleted) {
		if (head == null || toBeDeleted == null) return head;
		if (toBeDeleted.next != null) { // 如果不是表尾
			ListNode next = toBeDeleted.next;
			toBeDeleted.next = toBeDeleted.next.next;
			toBeDeleted.value = next.value;
		} else if (head == toBeDeleted) { // 如果是表尾，且是表头，也就是只有一个结点的链表
			head = null;
		} else {
			ListNode node = head;
			while (node.next != toBeDeleted) { // 如果是表尾，有多个结点的链表
				node = node.next;
			}
			node.next = null;
		}
		
		return head;
	}
	
	private static void printNode(ListNode head) {
		if (head == null) System.out.println("null");
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		ListNode n4 = new ListNode(4, null);
		ListNode n3 = new ListNode(3, n4);
		ListNode n2 = new ListNode(2, n3);
		ListNode n1 = new ListNode(1, n2);
		printNode(n1);
		
		n1 = deleteNodeInO1(n1, n4);
		printNode(n1);
		
		n1 = deleteNodeInO1(n1, n3);
		printNode(n1);
		
		n1 = deleteNodeInO1(n1, n2);
		printNode(n1);
		
		n1 = deleteNodeInO1(n1, n1);
		printNode(n1);
	}
}
