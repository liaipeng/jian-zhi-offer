package cn.edu.fzu.algorithm;

/*
题目描述

求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
*/

/*
 * 书中的构造函数，虚函数等解法在Java中都不适用。
 * 
 * 看了讨论区的解法，感觉好神奇。
 * 
 * 解题思路：
 * 1.需利用逻辑与的短路特性实现递归终止。 
 * 2.当n==0时，(n>0)&&((sum+=Sum_Solution(n-1))>0)只执行前面的判断，为false，然后直接返回0；
 * 3.当n>0时，执行sum+=Sum_Solution(n-1)，实现递归计算Sum_Solution(n)。
 * 
 * 其实就是利用短路特性的递归解法。
 * 正常的递归解法应该是
 * 
 *      int sum = n;
        if (n == 0) {
            return 0;
        }
        else {
            return sum + Sum_Solution(n-1);
        }
        
 * 但是题目规定了不能使用if判断，所以不能使用正常的递归解法。
 * 所以，利用短路特性这个小trick，变相实现if的功能。
 * 
 * 好巧妙的解法，赞一个。
 * 
 * 具体见代码①
 */

public class SumOfOneToN {
    public int sum_Solution(int n) {
        int sum = n; // sum一开始只有n。
        // 这个flag没有任何意义，目的只是让=右边的式子能正常编译。
        // (sum+=sum_Solution(n-1))>0是否大于0，等于0，小于0也都没有意义。目的只是执行sum+=sum_Solution(n-1)。
        // 当n == 0的时候，不会执行后半部分。
        // 否则，执行后半部分，递归。
        @SuppressWarnings("unused")
		boolean flag = (n>0)&&((sum+=sum_Solution(n-1))>0); 
        return sum;
    }
}
