package cn.edu.fzu.algorithm;

/*
题目描述

一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
*/

/*
 * 青蛙跳台阶升级版
 * 
 * 可以发现：
      f(1) = 1;
      f(2) = 2;
      f(3) = f(1) + f(2) + 1;
      f(4) = f(1) + f(2) + f(3) + 1;
      ...
 *    
 *     按照这列出的情况，可以写出代码①
 *     但是代码①显然是效率很低的，因为重复计算了很多很多结点
 *     
 *     
 * Discuss：
 * 
 *     其实经过归纳可以发现
 *     f(0) = 1;
 *     f(1) = f(0);
 *     f(2) = f(0) + f(1);
 *     f(n) = f(0) + f(1) + f(2) + f(3) + ... + f(n-2) + f(n-1)
 *     f(n-1) = f(0) + f(1) + f(2) + f(3) + ... + f((n-1)-1) = f(0) + f(1) + f(2) + f(3) + ... + f(n-2)
 *     
 *     所以， f(n) = 2 * f(n-1)
 *     代码可以简化成代码②
 * 
 *     
 *     终极解法：
 *     每个台阶都有跳与不跳两种情况（除了最后一个台阶），最后一个台阶必须跳。所以共用2^(n-1)中情况
 *     见代码③
 * 
 */

public class JumpFloorII {
	/*
	 * 代码①
	 */
    public int jumpFloorII(int target) {
        if (target < 2) return 1;
        int result = 0;
         
        for (int i = 1; i < target; i++) {
            result += jumpFloorII(i);
        }
        return result + 1;
    }
    
    /*
     * 代码②
     */
    public int jumpFloorII_2(int target) {
        return (target < 2) ? 1 : 2 * jumpFloorII_2(target - 1);
    }
    
    /*
     * 代码③
     */
    public int jumpFloorII_3(int target) {
        return (int)Math.pow(2, target-1);
    }
    
}
