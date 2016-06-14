package cn.edu.fzu.algorithm;

/*
题目描述

牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。
后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
*/

/*
 * 这一题有很多解法。
 * 
 * 两次翻转字符串应该是最经典的解法。
 * 
 * 具体见代码①
 */

public class ReverseSentence {
    private void reverseStr(char[] letters, int low, int high) {
        while (low < high) {
            char temp = letters[low];
            letters[low] = letters[high];
            letters[high] = temp;
            
            low++;
            high--;
        }
    }
    /*
     * 代码①
     */
    public String reverseSentence(String str) {
        if (str == null || str.length() == 0) return str;
        char[] letters = str.toCharArray();
        
        // 第一遍，翻转整个句子
        reverseStr(letters, 0, letters.length-1);
        
        // 第二遍，翻转每个单词
        for (int i = 0, start = 0; i < letters.length; i++) {
            if (letters[i] == ' ') {
                reverseStr(letters, start, i-1);
                start = i+1;
            } else if (i == letters.length-1) {
                reverseStr(letters, start, i);
            }
        }
        
        return String.valueOf(letters);
    }
}
