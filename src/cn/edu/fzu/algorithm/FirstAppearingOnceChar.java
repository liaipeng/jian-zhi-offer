package cn.edu.fzu.algorithm;

import java.util.Arrays;

/*
题目描述

请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。 
输出描述:
如果当前字符流没有存在出现一次的字符，返回#字符。
*/
/*
 * 用哈希的思想
 * 
 * 具体见代码
 */

public class FirstAppearingOnceChar {
	private int[] occurrence = new int[256]; // 哈希表，存放字符的出现情况
    private int index = 0; // 字符的出现顺序
    
    public FirstAppearingOnceChar() {
        Arrays.fill(occurrence, -1); // 初始化为-1，表示没有出现过
    }
    
    //Insert one char from stringstream
    public void insert(char ch) {
        if (occurrence[ch] == -1) {
            occurrence[ch] = index; // 若该字符没有出现过，把对应的值改为其出现顺序
        } else if (occurrence[ch] >= 0){
            occurrence[ch] = -2; // 若该字符已经出现过了，将其值改为-2，表示出现过多次
        }
        index ++;
    }
  	//return the first appearence once char in current stringstream
    /*
     * 当我们需要找出到目前为止从字符流里读出的所有字符中第一个不重复的字符时，只需要扫描整个数组，并从中找出最小的大于等于0的值对应的字符即可。
     */
    public char firstAppearingOnce() {
        char ch = '#'; // 如果没有只出现一次的字符的话，返回#
        int minIndex = Integer.MAX_VALUE;
    	for (int i = 0; i < 256; i++) {
            if (occurrence[i] >= 0 && occurrence[i] < minIndex) {
                ch = (char)i;
                minIndex = occurrence[i];
            }
        }
        return ch;
    }
}
