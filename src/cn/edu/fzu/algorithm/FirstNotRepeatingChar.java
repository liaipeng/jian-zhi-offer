package cn.edu.fzu.algorithm;

/*
题目描述

在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符的位置。若为空串，返回-1。位置索引从0开始
*/

/*
 * 字符(char)是一个长度为8的数据类型，因此总共有256种可能。
 * 于是创建一个长度为256的数组，每个字母根据其ASCII码值作为数组的下标对应数组的一个数字，
 * 数组中存储的是每个字符出现的次数。
 * 
 */

public class FirstNotRepeatingChar {
    public int firstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) return -1;
        int[] appears = new int[256]; // 存放字符出现的次数，默认为0
        
        char[] letters = str.toCharArray();
        // 第一遍遍历：统计字符出现的次数
        for (char c : letters) {
            appears[c]++; 
        }
        
        // 第二遍遍历：找出只出现一次的字符
        for (int i = 0; i < letters.length; i++) {
            if (appears[letters[i]] == 1) return i; 
        }
        
        return -1;
    }
}
