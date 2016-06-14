package cn.edu.fzu.algorithm;

import java.util.ArrayList;

/*
题目描述

小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck! 
输出描述:
输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
*/

/*
 * FindNumbersWithSum升级版。解法也类似。
 * 
 * 初始start为1，end为2（因为题目说了是正数序列）
 * 判断start ~ end的和是否为sum，如果是的话，此为一个正确的解。然后，start往后移动一格，继续判断。
 * 如果和小于sum，end往后移动一格。
 * 如果大于sum，start往后移动一格。
 * 
 * 直到start已经到了(1+sum)/2，循环结束。
 * 因为如果start已经到了(1+sum)/2，那么start到end的总和肯定超过sum。
 * 
 * 为什么是(1+sum)/2，而不是sum/2？
 * 因为
 * 		若sum为奇数：(1+sum)/2 + (1+sum)/2 + 1 = sum + 2 > sum
 * 	   	若sum为偶数：(1+sum)/2 + (1+sum)/2 + 1 = sum + 1 > sum
 * 
 */

public class FindContinuousSequence {
    public ArrayList<ArrayList<Integer> > findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (sum < 3) return result;
        
        int start = 1, end = 2, curSum = start + end;
        int mid = (1 + sum) >> 1;
        while (start < mid) { 
        	// 如果找到一个符合的序列，加入到result中
        	if (curSum == sum) {
        		addList(result, start, end);
            	curSum -= start; // 去掉小数
            	start ++; // 小数+1
        	}
        	// 如果curSum比目标sum小，end往后移动，更新curSum
        	while (curSum < sum && start < mid) {
        		end ++;
        		curSum += end;
        	}
        	// 如果curSum比目标sum大，更新curSum，start往后移动
        	while (curSum > sum && start < mid) {
        		curSum -= start;
        		start ++;
        	}
        }
        
        return result;
    }
    private void addList(ArrayList<ArrayList<Integer>> result, int start, int end) {
    	ArrayList<Integer> seq = new ArrayList<>();
    	for (int num = start; num <= end; num++) {
    		seq.add(num);
    	}
    	
    	result.add(seq);
    }
}
