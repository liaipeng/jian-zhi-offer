package cn.edu.fzu.algorithm;

/*
题目描述

汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
*/

/*
 * 最直观的解法，见代码①
 * 
 * 更好的解法，3次旋转，见代码②
 */

public class LeftRotateString {
	/*
	 * 代码①
	 */
	public String leftRotateString(String str,int n) {
        if (str == null || str.length() == 0 || n < 0) return str;
        
        int len = str.length();
        n %= len;
        char[] letters = str.toCharArray();
        char[] lefts = new char[n]; // 存储要被左旋的部分
        
        // 将要左旋的部分存入lefts
        for (int i = 0; i < n; i++) {
            lefts[i] = letters[i];
        }
        // 剩余部分移动到左边
        for (int i = 0; i < len-n; i++) {
            letters[i] = letters[i+n];
        }
        // 剩余的位置填入lefts保存的部分
        for (int i = len - n, j = 0; i < len; i++, j++) {
            letters[i] = lefts[j];
        }
        
        return String.valueOf(letters);
    }
	
	/*
	 * 代码②
	 */
    private void reverseStr(char[] letters, int low, int high) {
        while (low < high) {
            char temp = letters[low];
            letters[low] = letters[high];
            letters[high] = temp;
            
            low++;
            high--;
        }
    }
    public String leftRotateString_2(String str,int n) {
    	if (str == null || str.length() == 0 || n < 0) return str;
        char[] letters = str.toCharArray();
        n %= letters.length; // 处理n，所以不存在数组越界
        
        reverseStr(letters, 0, letters.length-1); // 翻转前n个字符
        reverseStr(letters, 0, letters.length-n-1); // 翻转后面的部门
        reverseStr(letters, letters.length-n, letters.length-1); // 翻转整个字符串
        
        return String.valueOf(letters);
    }
}
