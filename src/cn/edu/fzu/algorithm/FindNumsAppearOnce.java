package cn.edu.fzu.algorithm;

/*
题目描述

一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
*/

/*
 * LeetCode原题
 * 
 */

//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果
public class FindNumsAppearOnce {
    public void findNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null || array.length < 2) return;

        // 先求出所有数的异或，结果将是n1异或n2的结果
        int exc = 0;
        for (int num : array) {
        	exc ^= num;
        }
        // n1和n2不相等，所以必然有至少某一位不为0，在这里我们找到其最高位1.
        int highestOne = Integer.highestOneBit(exc);
        
        // 之后，根据该位是否为1将数组分为两部分，将两部分分别进行异或，即可得到两个只出现一次的数
        int n1 = 0, n2 = 0;
        for (int num : array) {
        	if ((num & highestOne) != 0) {
        		n1 ^= num;
        	} else {
        		n2 ^= num;
        	}
        }
        
        num1[0] = n1;
        num2[0] = n2;
    }
}
