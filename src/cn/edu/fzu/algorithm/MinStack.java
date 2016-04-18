package cn.edu.fzu.algorithm;

import java.util.Stack;

/*
题目描述：
定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min函数。在该栈中，调用min、push及pop的时间复杂度都是O(1)。
*/

/*
 * 这一题在LeetCode上做过，有好多种解法，用两个栈的、一个栈的都有，具体可以看印象笔记中LeetCode-easy中的Min Stack
 * 
 * 代码①是书本上的解法，用两个栈，我觉得比我在印象笔记中记录的3种做法都更好理解，若是可以使用两个栈，首选这种解法。
 * 
 * 本题是在“举例让抽象问题具体化”章节。
 * 就是通过一个具体的实例来演算，帮助理解题目。
 * 
 * 例如，首先往空的数据栈里压入数字3，显然3是最小值，那么也把这个最小值压入辅助栈中。
 * 接下来，压入数字4，由于4大于之前的最小值，因此仍然在辅助栈中压入3。
 * 第三步，压入数字2，此时，则在辅助栈中压入2。
 * 以此类推。
 * 可以用一个表格帮助理解。
 * 
 */

public class MinStack {
	/*
	 * 代码①
	 */
	Stack<Integer> stack = new Stack<>(); // 数据栈
	Stack<Integer> minStack = new Stack<>(); // 辅助栈
	
    public void push(int node) {
        if (stack.isEmpty()) { // 若是空栈
        	stack.push(node);
        	minStack.push(node);
        } else {
        	stack.push(node);
        	if (node < minStack.peek()) { // 判断当前压入的元素是否比最小值小
        		minStack.push(node);
        	} else {
        		minStack.push(minStack.peek());
        	}
        }
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int min() {
        return minStack.peek();
    }
}
