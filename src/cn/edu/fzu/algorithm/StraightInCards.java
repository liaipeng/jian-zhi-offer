package cn.edu.fzu.algorithm;

import java.util.Arrays;

/*
题目：
从扑克牌中随机抽取5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
2~10为数字本身，A为1，J为11，Q为12，K为13，而大王小王可以看成任意数字。（为了方便，大王小王都用0表示）
*/

/*
 * 最直观的方法是把数组进行排序，然后判断数组是不是连续的。
 * 由于0可以当做任何数，所以0可以用于填补数组中的空缺。
 * 
 * 所以我们需要做3件事情：
 * 1. 把数组排序。
 * 2. 统计0的个数。
 * 3. 统计排序后数组中相邻数字之间的空缺数。
 * 
 * 如果0的个数大于或等于空缺数，就可以组成一个顺子。
 * 
 * 如果出现两个非0数字相同，那么肯定不可能是一个顺子。
 * 
 * 具体见代码①。
 * 
 */

public class StraightInCards {
	/*
	 * 代码①
	 */
    public boolean isContinuous(int[] numbers) {
		if (numbers == null || numbers.length != 5) return false;
        
		// 注：因为扑克牌总共只有0~13的数，所以可以用长度为14的哈希表来排序，复杂度为O(n)
		// 这边用快排，复杂度O(nlogn)，但是，因为n为5，所以差别不大。
        Arrays.sort(numbers);
        
        int numOfZero = 0, numOfGap = 0, index = 0;
        
        // 统计数组中0的个数
        for (; index < numbers.length && numbers[index] == 0; index++) {
            numOfZero ++;
        }
        
        // 统计数组中gap的数目
        for (; index < numbers.length-1; index++) {
            if (numbers[index+1] == numbers[index]) return false; // 如果相等，不可能是顺子
            numOfGap += numbers[index+1] - numbers[index] - 1; // 计算ga
        }
        
        return numOfZero >= numOfGap; // 若0的个数不少于gap的数量，就能组成顺子
    }
}
