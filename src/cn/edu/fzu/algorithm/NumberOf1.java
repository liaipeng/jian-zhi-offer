package cn.edu.fzu.algorithm;

/*
题目描述

输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
*/

public class NumberOf1 {
	// 把一个数 与上 这个数-1的结果， 会把这个数最右边的1变成0。
    // 那么，直到n=0为止，循环了几次，就有几个1
    public int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count ++;
            n = n & (n - 1);
        }
        return count;
    }
}
