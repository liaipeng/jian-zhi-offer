package cn.edu.fzu.algorithm;

/*
题目描述

大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
*/

public class Fibonacci {
    // 用动态规划，复杂度O(n)，31ms
    public int fibonacci(int n) {
        int[] num = {0, 1};
        if (n < 2) return num[n];
         
        int first = 0, second = 1, result = 0;
        // 斐波那契数列是从第0项开始算的，所以是i<=n
        for (int i = 2; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
         
    }
     
    /* 传统解法，效率低，重复计算了很多结点，750ms
        public int fibonacci(int n) {
	        if (n < 1) return 0;
	        else if (n < 3) return 1;
	        return Fibonacci(n - 2) + Fibonacci(n - 1);
    	}
    */
}
