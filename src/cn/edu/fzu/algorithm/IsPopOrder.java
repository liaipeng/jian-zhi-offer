package cn.edu.fzu.algorithm;

import java.util.Stack;

/*
 题目描述

 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 假设压入栈的所有数字均不相等。
 例如序列1,2,3,4,5是某栈的压入顺序，序列 4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 */

/*
 * 这题也是在“举例让抽象问题具体化”章节。
 * 可以列出表格帮助理解，例如题目的例子：压入序列为1,2,3,4,5，弹出序列是4,5,3,2,1
 * 
 * 		步骤		操作		栈			弹出数字序列
 * 		1		压入1	1
 * 		2		压入2	1,2
 * 		3		压入3	1,2,3
 * 		4		压入4	1,2,3,4
 * 		5		弹出		1,2,3		4
 * 		6		压出5	1,2,3,5		4
 * 		7		弹出		1,2,3		4,5
 * 		8		弹出		1,2			4,5,3
 * 		9		弹出		1			4,5,3,2
 * 		10		弹出					4,5,3,2,1
 * 
 * 也就是说，当栈顶没有弹出序列要求的数字时，压入一个数字。
 * 			有的话，则弹出该数字。看弹出序列中的下一个数字，依次类推。
 * 当弹出序列中的某一个数字，在栈顶不存在，且此时压入序列已经为空时，说明该弹出序列不是该压栈序列的弹出序列。
 */

public class IsPopOrder {
	public static boolean isPopOrder(int[] pushA, int[] popA) {
		if (pushA == null || popA == null || pushA.length == 0
				|| popA.length == 0 || pushA.length != popA.length)
			return false;
		int pushIndex = 0, popIndex = 0; // 压入序列和弹出序列的下标
		Stack<Integer> stack = new Stack<>();
		
		stack.push(pushA[pushIndex]); // 先压入第一个数字，这样在循环内就不用判断stack是否为空了
		
		while (popIndex < popA.length) {
			// 如果栈顶数字不是弹出序列的当前数字，压入下一个数字，直到栈顶数字等于弹出序列的数字。若直到压栈序列为空，返回false
			while (stack.peek() != popA[popIndex]) { 
				if (pushIndex == pushA.length) return false;
				stack.push(pushA[pushIndex++]);
			}
			
			stack.pop(); // 弹出匹配的数字
			popIndex++; // 取弹出序列的下一个数字进行处理
		}
		return true; // 循环运行完美返回false，则表示该序列是合法的弹出顺序。
	}

	public static void main(String[] args) {
		int[] pushA = { 1, 2, 3, 4, 5 };
		int[] popA = { 4,3,5,1,2 };
		boolean isOrder = isPopOrder(pushA, popA);
		System.out.println(isOrder);
	}
}
