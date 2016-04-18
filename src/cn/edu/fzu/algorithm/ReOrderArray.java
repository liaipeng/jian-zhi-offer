package cn.edu.fzu.algorithm;

import java.util.ArrayList;
import java.util.List;

/*
 题目描述

 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *	
 * 这题跟书里的原题其实不一样，原题并不需要相对位置不变。那就简单多了，直接双指针一首一尾遍历交换就行了。
 * 
 * 这题想到的也是用双指针法
 * 从左到右遍历数组，pre遇到偶数的时候
 * 		判断cur是否是奇数，如果是的话，从pre开始到cur-1的所有元素往后移动一格，空出来的位置插入该奇数。（不能直接交换，直接交换顺序可能会改变），pre和cur都往右一步
 * 		如果不是奇数，pre不动，cur继续往右走，直到cur遇到偶数
 * 如果pre遇到的是奇数，pre和cur往右走，直到pre遇到偶数
 *	
 * 实现如代码①
 * 但是代码①的效率显然不会是最好的，移位浪费了太多时间，应该是O(n²)的复杂度
 * 
 * 
 * 我想到的另一个做法，就是牺牲空间来换时间了。
 * 再定义一个List（数组也行，比较麻烦一点。而且数组的长度不好掌握，同样得浪费一点长度，这里我就偷懒用List了），来存储遍历过程中遇到的偶数。
 * 遇到的奇数全部往前提，最后剩下的位置填充List里的元素即可
 * 这样的话就只需要O(n)的复杂度
 * 
 * 见代码②
 * 
 */

public class ReOrderArray {
	/*
	 * 代码①
	 */
	public void reOrderArray(int[] array) {
		if (array == null || array.length <= 1)
			return;
		int pre = 0, cur = 1;
		while (cur < array.length) {
			if (array[pre] % 2 == 0) {
				if (array[cur] % 2 == 1) {
					int tempCur = cur;
					int num = array[tempCur];
					// 移位
					while (tempCur != pre) {
						array[tempCur--] = array[tempCur];
					}
					// 在空出来的位置填上原来cur指向的奇数
					array[tempCur] = num;
					pre ++;
				}
			} else {
				pre ++;
			}
			cur ++;
		}
	}
	
	public void reOrderArray_2(int[] array) {
		if (array == null || array.length <= 1)
			return;
		List<Integer> evens = new ArrayList<>();
		int pre = 0, cur = 0;
		while (cur < array.length) {
			// 如果是偶数，放到list里
			if (array[cur] % 2 == 0) {
				evens.add(array[cur]);
				cur ++;
			} else {
				array[pre++] = array[cur++]; // 奇数往前压缩
			}
		}
		// 把list里的偶数填回数组里
		for (int num : evens) {
			array[pre++] = num; 
		}
	}
}
