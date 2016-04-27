package cn.edu.fzu.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
题目描述

输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点或者NULL）。
请实现一个函数，复制此复杂链表。
*/

/*
 * 相比复制普通的链表，本题的难点在于对random的复制。
 * 		第一步，复制原始链表的每个结点，然后用next连接起来。
 * 		第二步，设置每个结点的random指针。
 * 			假设原始链表中的某个结点N的random指向结点S，由于S可能在N的前面也可能在后面，所以要定位S必须从原始链表的头结点开始沿着next走s步。
 * 			那么，在复制链表上结点N’的random S'离复制链表的头结点的距离也是沿着next指针走s步。
 * 			但是，由于定位每个结点的random都要走O(n)步才能找到，所以时间复杂度为O(n²)。
 * 
 * 优化1：
 * 		上述方法主要时间花费在定位random上面。
 * 		所以，可以用一个Map存储新链表与原始链表的结点配对信息<N, N'>。
 * 		这样一来，假设原始链表中N结点的random指向S，新链表只需取得S的对应结点S'即可，O(1)时间即可完成定位。
 * 		也就是用O(n)的空间换时间。代码也直观易于理解。
 * 		见代码①
 * 
 * 优化2：
 * 		优化1需要消耗额外的空间。
 * 		此方法在不用辅助空间的情况下实现O(n)的时间复杂度。
 * 		第一步，仍然复制每个结点N，假设为N'。不同的是，把N'插入到N的后面。
 * 		第二步，设置复制结点的random。因为N'位于N的后面，所以如果N的random指向S，那么N'的random指向S的下一个结点S'。
 * 		第三部，拆分奇数偶数位置为两条链表。偶数位置的就是复制出来的链表。
 * 		见代码②
 */

public class CloneRandomListNode {
	public static class RandomListNode {
		int label;
		RandomListNode next = null;
		RandomListNode random = null;

		RandomListNode(int label) {
			this.label = label;
		}
	}

	/*
	 * 代码①
	 */
	public RandomListNode clone(RandomListNode head) {
		if (head == null) return null;
		RandomListNode cur = head;
		RandomListNode newListHead = null;
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		
		// 复制结点
		for (int i = 0; cur != null; i++) {
			RandomListNode clone = new RandomListNode(cur.label);
			map.put(cur, clone); // 维护一个哈希表，存储原链表与新链表节点的对应关系。
			if (i == 0) newListHead = clone; // 记录新链表的头结点。
			cur = cur.next;
		}
		
		// 连接结点
		for (Entry<RandomListNode, RandomListNode> entry : map.entrySet()) {
			RandomListNode oNode = entry.getKey(); // 原链表节点
			RandomListNode nNode = entry.getValue(); // 新链表节点
			nNode.next = map.get(oNode.next); // 连接next
			nNode.random = map.get(oNode.random); // 连接random
		}
		
		return newListHead;
	}
	
	/*
	 * 代码②
	 */
	public RandomListNode clone_2(RandomListNode head) {
		if (head == null) return null;
		RandomListNode cur = head;
		
		// 复制结点，并插入到原结点后面。
		while (cur != null) {
			RandomListNode clone = new RandomListNode(cur.label);
			clone.next = cur.next;
			cur.next = clone;
			cur = clone.next;
		}
		
		// 设置新结点的random
		cur = head;
		RandomListNode newCur = null;
		while (cur != null) {
			newCur = cur.next; 
			if (cur.random != null) { // 注意判断random是否为空，否则会空指针
				newCur.random = cur.random.next; // 新结点的random为原始结点random的后一个结点
			}
			cur = newCur.next;
		}
		
		// 将长链表拆成两条
		cur = head;
		RandomListNode fakeNewHead = new RandomListNode(-1); // 假表头的应用
		fakeNewHead.next = head;
		RandomListNode newHead = head.next;
		
		while (cur != null) {
			fakeNewHead.next = cur.next;
			fakeNewHead = fakeNewHead.next;
			cur.next = fakeNewHead.next;
			cur = cur.next;
		}
		
		return newHead;
	}
}
