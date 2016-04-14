package cn.edu.fzu.algorithm;

/*
题目描述

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个非递减序列的一个旋转，输出旋转数组的最小元素。
例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
*/

public class MinNumberInRotateArray {
	// 顺序查找
    private int minNumberInOrderArray(int[] array, int start, int end) {
        int minNum = array[start];
        for (int i = start + 1; i <= end; i++) {
            if (array[i] < minNum) {
                minNum = array[i];
            }
        }
        return minNum;
    }
     
    public int minNumberInRotateArray(int [] array) {
        if (array == null || array.length == 0) return 0;
         
        int low = 0, high = array.length-1, mid = 0;
         
        // 如果是没有旋转的数组，会直接跳过这个循环
        while (array[low] >= array[high]) {
            // high和low相邻时，high指向的即为最小数
            if (high - low == 1) {
                mid = high;
                break;
            }
            mid = high - (high - low) / 2;
             
            // 如果low mid high指向的数相等，那么此时没有办法判断mid所指的数是在前半还是后半，只能顺序查找
            if (array[low] == array[high] && array[low] == array[mid]) {
                return minNumberInOrderArray(array, low, high);
            }
            // 如果mid指向的数比low指向的数大，那么mid此时位于前半子数组中，最小数在mid右边
            if (array[mid] > array[low]) {
                low = mid;
                // 否则，最小数在mid左边
            } else {
                high = mid;
            }
        }
        return array[mid];
    }
}
