package cn.edu.fzu.algorithm;

/*
题目描述

请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。

*
*	问题本身不难，但是如果从头往尾遍历进行替换，因为要一直移动字符，所以要O(n²)的复杂度。
*	而如果从后面往前面替换，所有字符只需要移动一次，O(n)的复杂度，见代码①
*
*/

public class ReplaceSpace {
	/*
	 * 代码①
	 */
	 public String replaceSpace(StringBuffer str) {
	        if (str == null || str.length() < 1) return str.toString();
	        int count = 0;
	        char[] letters = str.toString().toCharArray();
	         
	        // 计算空格数量
	        for (int i = 0; i < letters.length; i++) {
	            if (letters[i] == ' ') {
	                count ++;
	            }
	        }
	        // 创建新字符数组
	        char[] newLetters = new char[letters.length + (count * 2)];
	        int newIndex = newLetters.length - 1, oldIndex = letters.length - 1;
	         
	         
	        while (oldIndex >= 0) {
	            if (letters[oldIndex] == ' ') {
	                newLetters[newIndex--] = '0';
	                newLetters[newIndex--] = '2';
	                newLetters[newIndex--] = '%';
	                oldIndex --;
	            } else {
	                newLetters[newIndex--] = letters[oldIndex--];
	            }
	        }
	             
	        return String.valueOf(newLetters);
	    }
}
