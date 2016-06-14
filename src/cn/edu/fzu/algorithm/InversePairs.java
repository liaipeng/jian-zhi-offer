package cn.edu.fzu.algorithm;

import java.util.Arrays;

/*
题目描述

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
*/

/*
 * 用归并排序的思想。
 * 
 * 先把数组递归分解为两部分，直到长度为1。
 * 然后一边合并相邻的字数组，一边统计逆序对的数目。
 * 同时，在合并的过程进行排序，以免在以后的统计过程中再重复统计。
 * 
 * 具体的，两个指针分别指向两个子数组的末尾，并每次比较两个指针指向的数字。
 * 如果第一个子数组中的数字大于第二个子数组中的数字，那么就构成逆序对。
 * 并且逆序对的数目等于第二个子数组中剩余数字的个数（因为子数组已经排好序了，前面的肯定比当前数字小）。
 * 反之，则不构成逆序对。
 * 每一次比较的时候，我们都把较大的数字从后往前复制到一个辅助数组中去，确保辅助数组中的数字是递增排序的。
 * 在把较大的数字复制到辅助数组之后，把对应的指针向前移动一位，进行下一轮的比较。
 *
 * 具体见代码。
 * 
 */

public class InversePairs {
    public int inversePairs(int[] array) {
        if (array == null || array.length == 0) return 0;
        
        int[] copy = Arrays.copyOf(array, array.length); // 拷贝数组
        
        return inversePairsCore(array, copy, 0, array.length-1);
    }
	private int inversePairsCore(int[] array, int[] copy, int start, int end) {
		// 若递归到子数组长度为1，返回。
		if (start == end) {
			copy[start] = array[end];
			return 0;
		}
		int len = (end - start) >> 1; // 将数组分为两半
		
		// 注意，这边递归调用时，copy和array数组的位置调换了
		// 每次调用的时候，原拷贝数组和数据数组互换角色
		int leftCount = inversePairsCore(copy, array, start, start+len); // 递归计算左子数组的逆序对数量
		int rightCount = inversePairsCore(copy, array, start+len+1, end); // 递归计算右子数组的逆序对数量
		
		// i初始化为前半段最后一个数字的下标
		int i = start + len;
		// j初始化为后半段最后一个数字的下标
		int j = end;
		
		int indexCopy = end; // 拷贝数组的下标
		int count = 0; // 当前数组的逆序对数量
		
		while (i >= start && j >= start+len+1) {
			// 若左子数组的数比右子数组的大，那么逆序对数量是右子数组剩余数字的个数
			if (array[i] > array[j]) {
				copy[indexCopy--] = array[i--]; // 同时，把更大的数字拷贝的copy数组中
				count += j - start - len;
			} else {
				copy[indexCopy--] = array[j--];
			}
		}
		
		// 将剩余数字全部都拷贝到copy数组中
		while (i >= start) {
			copy[indexCopy--] = array[i--];
		}
		while (j >= start+len+1) {
			copy[indexCopy--] = array[j--];
		}
		
		return leftCount + rightCount + count; // 三种情况的逆序对数量相加即为结果。
	}
	
	public static void main(String[] args) {
		int[] array = {1,2,1,2,1};
		int count = new InversePairs().inversePairs(array);
		System.out.println(count);
	}
}
