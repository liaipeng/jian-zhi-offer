package cn.edu.fzu.algorithm;

import java.util.ArrayList;
import java.util.Collections;

/*
 题目描述

 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 结果请按字母顺序输出。 

 输入描述:
 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 */

/*
 * LeetCode中遇到过相同的题目
 * 代码①就是在LeetCode中的解法。
 * 
 * 如果说代码①是插入法，那么书上的做法则是交换法：
 * 	我们求整个字符串的排列，可以看成两步：
 * 		首先求所有可能出现在第一个位置的字符，即把第一个字符和后面所有的字符交换。
 * 		第二步固定第一个字符，求后面所有字符的排列。
 * 		依次类推。
 * 
 * 
 */

public class SortString {
	/*
	 * 代码①
	 */
	public ArrayList<String> permutation(String str) {
		if (str == null || str.length() == 0) return new ArrayList<String>();
		ArrayList<String> seq = new ArrayList<>();
		char[] letters = str.toCharArray();
		
		collect(letters, 0, new StringBuilder(""), seq);
		
		Collections.sort(seq); // 按字母序排列
		return seq;
	}
	private void collect(char[] letters, int index, StringBuilder sb, ArrayList<String> seq) {
		if (sb.length() == letters.length) { // 当sb收集完letters数组中的所有字母后，产生一个排列结果。
			String tempStr = sb.toString();
			if (!seq.contains(tempStr)) { // 判断排列结果是否已存在
				seq.add(sb.toString());				
			}
			return;
		} else {
			// 从右到左往sb里插字母
			for (int i = sb.length(); i >= 0; i--) {
				StringBuilder newSb = new StringBuilder(sb);
				newSb.insert(i, letters[index]); // 插入完这个字母，递归去取下一个字母来循环插入
				collect(letters, index+1, newSb, seq);
			}
		}
	}
	
	
	/*
	 * 代码②
	 * 相比代码①，时间和空间上都得到了优化
	 */
	public ArrayList<String> permutation_2(String str) {
		if (str == null || str.length() == 0) return new ArrayList<String>();
		ArrayList<String> seq = new ArrayList<>();
		char[] letters = str.toCharArray();
		
		collect_2(letters, 0, seq);
		
		Collections.sort(seq); // 按字母序排列
		return seq;
	}
	private void collect_2(char[] letters, int start, ArrayList<String> seq) {
		if (start == letters.length) { // 与代码①基本相似
            String tempStr = new String(letters);
            if (!seq.contains(tempStr)) {
  				seq.add(new String(letters));              
            }
			return;
		} else {
			// 依次将所有字符与start交换
			for (int i = start; i < letters.length; i++) {
				// 交换字符
				char temp = letters[i];
				letters[i] = letters[start];
				letters[start] = temp;
				
				collect_2(letters, start+1, seq); // 固定第一个字符，递归交换后面的字符
				
				// 交换回去
				temp = letters[i];
				letters[i] = letters[start];
				letters[start] = temp;
			}
		}
	}
	
	
	public static void main(String[] args) {
		ArrayList<String> res = new SortString().permutation_2("abc");
		System.out.println(res);
	}
}
