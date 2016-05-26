package cn.edu.fzu.algorithm;

import java.util.Arrays;
import java.util.Comparator;

/*
题目描述

输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
*/

/*
 * 首先，把数字转换成字符串，方便比较，同时也防止在后面拼接数字的时候溢出。
 * 然后，对该字符串数组进行排序。排序规则：
 * 		假设两个要比较的字符串是a和b。
 * 		那么比较的规则就是，拼接字符串，得到ab和ba，因为拼接后两个字符串长度肯定是相同的，所以只要按照字符串大小的比较规则进行比较即可。
 * 		例如，字符串"3"和"32"。
 * 		拼接后得到"332"和"323"，因为"332"比"323"大，所以排序的结果就是"32","3"。
 * 		
 * 最后，将排序后的字符串进行拼接即可。
 * 
 */

public class PrintMinNumber {
    public String printMinNumber(int[] numbers) {
    	if (numbers == null || numbers.length == 0) return "";
    	String[] numStrs = new String[numbers.length];
    	
    	// 转成字符串
    	for (int i = 0; i < numbers.length; i++) {
    		numStrs[i] = "" + numbers[i];
    	}
    	
    	// 对字符串数组进行排序
    	Arrays.sort(numStrs, new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				String tempStr1 = str1 + str2;
				String tempStr2 = str2 + str1;
				
				return tempStr1.compareTo(tempStr2);
			}
    	});
    	
    	// 将排序好的字符串拼接起来
    	StringBuilder result = new StringBuilder("");
    	for (String str : numStrs) {
    		result.append(str);
    	}
    	
    	return result.toString();
    }
    
    public static void main(String[] args) {
    	int[] numbers = {3, 32, 321};
    	String result = new PrintMinNumber().printMinNumber(numbers);
    	System.out.println(result);
	}
}
