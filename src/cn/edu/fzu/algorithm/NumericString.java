package cn.edu.fzu.algorithm;

/*
题目描述

请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
*/
/*
 * 1.在数值之前可能有一个表示正负的'-'和'+'。
 * 2.接下来是若干个0到9的数位表示数值的整数部分。
 * 3.如果是数值是一个小数，那么在小数点'.'后面可能会有若干个0到9的数位表示数值的小数部分。
 * 4.如果数值用科学计数法表示，接下来是一个'e'或者'E'，紧跟着一个整数（可能有正负号）表示指数。
 * 
 * 具体见代码①
 */

public class NumericString {
	private int i = 0; // 字符串的下标，全局变量
    
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) return false;
        
        // 第一位可以是符号
        if (str[i] == '-' || str[i] == '+') {
            i++;
        }
        if (i == str.length) return false; // 此时，若字符串已经到结尾了，false
        boolean numeric = true;
        scanDigits(str); // 扫描整数部分
        // 如果此时字符串还没结束，则可能是小数、科学技术法，或者不符合规范
        if (i != str.length) {
        	// 处理小数
            if (str[i] == '.') {
                i++;
                scanDigits(str); // 扫描小数部分
                // 如果此时字符串还没结束，只能是科学计数法，否则不符合规范
                if (i != str.length && (str[i] == 'E' || str[i] == 'e')) {
                    numeric = isExponential(str); // 处理科学计数法
                }
            // 处理整数
            } else if (str[i] == 'E' || str[i] == 'e') {
                numeric = isExponential(str); // 处理科学计数法
            } else {
                numeric = false; // 否则，不符合规范
            }
        } 
        return numeric && (i == str.length);
    }
    // 扫描数位
    private void scanDigits(char[] str) {
        while (i < str.length && Character.isDigit(str[i])) {
            i++;
        }
    }
    // 匹配科学技术法表示的数值的结尾部分
    private boolean isExponential(char[] str) {
        i++;
        if (i == str.length) return false;
        if (str[i] == '+' || str[i] == '-') {
            i++;
        }
        if (i == str.length) return false;
        scanDigits(str);
        return i == str.length;
    }
}
