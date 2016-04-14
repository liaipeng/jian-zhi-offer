package cn.edu.fzu.algorithm;

/*
题目描述

我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
*/

/*
 * 	假设是一个2*8的矩形，覆盖方法记为f(8)。
 * 		假如第一个小矩形是竖着放的，那么剩下右边的2*7的矩形，覆盖方法为f(7)
 * 		假如第一个小矩形是横着放的，那么这个小矩形下方的位置肯定也要放一个小矩形，所以剩下右边2*6的矩形，覆盖方法为f(6)
 * 	所以f(8) = f(7) + f(6)
 * 
 * 		f(7) = f(6) + f(5)
 * 		...
 * 		f(3) = f(2) + f(1)
 * 		f(2) = 2
 * 		f(1) = 1
 * 	
 * 	所以，其实这个问题本质上也就是一个斐波那契数列。
 * 
 */

public class RectCover {
	/*
	 * 代码①
	 */
    public int rectCover(int target) {
		if (target < 2) return 1;
		if (target < 3) return 2;
		int first = 1, second = 2, ways = 0;
		for (int i = 3; i <= target; i++) {
			ways = first + second;
			first = second;
			second = ways;
		}
		return ways;
    }
}
