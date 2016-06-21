package cn.edu.fzu.algorithm;

/*
题目描述

将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
*/

/*
 * 和LeetCode中的String to Integer(atoi)一样的题目。
 * 唯一的不同是类似(123a33)这样的输入，LeetCode要求返回123，这题则是返回0。
 * 
 * 具体见代码①
 */

public class StrToInt {
	/*
	 * 代码①
	 */
    public int strToInt(String str) {
        if (str == null || str.length() == 0) return 0;
        char[] digits = str.toCharArray();
        int sign = 0, i = 0, num = 0;
        
        // 处理开头无意义的空格
        while (i < digits.length && digits[i] == ' ') {
        	i++;
        }
        
        // 处理符号
        if (digits[i] == '+') {
        	sign = 1;
        	i++;
        } else if (digits[i] == '-') {
        	sign = -1;
        	i++;
        } else {
        	sign = 1;
        }
        
        for (; i < digits.length; i++) {
        	if (!Character.isDigit(digits[i])) return 0; // 若碰到非数字，返回0
        	
        	// 判断是否溢出
        	if (num > Integer.MAX_VALUE/10 || num == Integer.MAX_VALUE && digits[i] > Integer.MAX_VALUE%10) {
        		return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        	}
        	num = num * 10 + digits[i] - '0';
        }
        
        return num * sign;
    }
}
