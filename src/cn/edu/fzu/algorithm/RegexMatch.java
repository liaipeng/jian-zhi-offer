package cn.edu.fzu.algorithm;

/*
题目描述

请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 
在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
*/

/*
 * 对于'.'的匹配。
 * 如果模式中的字符ch是'.'，那么它可以匹配字符串中任意字符。
 * 如果模式中的字符ch不是'.'，而字符串中的字符也是ch，匹配。
 * 当字符串中的字符和模式中的字符相匹配时，向后移动一个字符，然后匹配剩余的字符串和模式。
 * 如果第一个字符和模式的一个字符不匹配时，返回false。
 * 
 * 对于'*'的匹配。
 * 可以用有限状态机来考虑。
 * 例如模式ba*ab，a*为状态2。当匹配进入状态2并且字符串的字符是'a'的时候，有两个选择：进入状态3或者回到状态2。
 * 
 * 也就是有多种不同的匹配方式。
 * 一个选择是在模式上向后移动两个字符，相当于'a*'被忽略掉。
 * 如果模式中的第一个字符和字符串中的第一个字符匹配，则在字符串向后移动一个字符，而在模式上有两个选择：向后移动2个字符，或者保持模式不变。
 * 
 * 具体见代码①
 * 
 */

public class RegexMatch {
	public boolean match(char[] str, char[] pattern) {
	    if (str == null || pattern == null) {
	        return false;
	    }
	    int strIndex = 0;
	    int patternIndex = 0;
	    return matchCore(str, strIndex, pattern, patternIndex);
	}
	 
	public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
	    //str到尾，pattern到尾，匹配成功
	    if (strIndex == str.length && patternIndex == pattern.length) {
	        return true;
	    }
	    //str未到尾，pattern到尾，匹配失败
	    if (strIndex != str.length && patternIndex == pattern.length) {
	        return false;
	    }
	    //str到尾，pattern未到尾(不一定匹配失败，因为a*可以匹配0个字符)
	    if (strIndex == str.length && patternIndex != pattern.length) {
	        //只有pattern剩下的部分类似a*b*c*的形式，才匹配成功
	        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
	            return matchCore(str, strIndex, pattern, patternIndex + 2);
	        }
	        return false;
	    }
	     
	    //str未到尾，pattern未到尾
	    if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
	        if (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.') {
	            return matchCore(str, strIndex, pattern, patternIndex + 2)//*匹配0个，跳过
	                    || matchCore(str, strIndex + 1, pattern, patternIndex + 2)//*匹配1个，跳过
	                    || matchCore(str, strIndex + 1, pattern, patternIndex);//*匹配1个，再匹配str中的下一个
	        } else {
	            //直接跳过*（*匹配到0个）
	            return matchCore(str, strIndex, pattern, patternIndex + 2);
	        }
	    }
	     
	    if (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.') {
	        return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
	    }
	     
	    return false;
	}
}
