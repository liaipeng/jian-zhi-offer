package cn.edu.fzu.algorithm;

import java.util.ArrayList;

/*
题目描述

输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。 
输出描述:
对应每个测试案例，输出两个数，小的先输出。
*/

/*
 * 用两个指针，low初始指向数组第一个数，high指向最后一个数。
 * 判断两个指针指向数的和是否等于sum，若相等，得到结果。
 * 若小于sum，low向后移动。
 * 若大于sum，high向前移动。
 * 循环判断直到low==high
 * 
 * 这样得到的结果肯定是乘积最小的。因为正方形面积最大。
 * 
 */

public class FindNumbersWithSum {
	public ArrayList<Integer> findNumbersWithSum(int[] array,int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        if (array == null || array.length == 0) return result;
        
        int low = 0, high = array.length - 1, tempSum = 0;
        
        while (low < high) {
            tempSum = array[low] + array[high]; 
            if (tempSum == sum) {
                result.add(array[low]);
                result.add(array[high]);
                return result;
            }
            while (tempSum < sum && low < high) {
                low ++;
                tempSum = array[low] + array[high]; 
            }
            while (tempSum > sum && low < high) {
                high --;
                tempSum = array[low] + array[high]; 
			}
        }
        
        return result;
    }
}
