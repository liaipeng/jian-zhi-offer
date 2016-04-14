package cn.edu.fzu.algorithm;

/*
题目描述

给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
*/

/*
 * 越是简单的题目，越是要注意陷阱和边界
 * 
 * 要注意判断exponent是正还是负
 * 如果exponent是负的，那么需要返回result的倒数。
 * 特别注意，0是没有倒数！！
 * 所以，当base=0，exponent为负数的时候，这个输入是非法的，应该抛异常。在这里我直接返回0了。
 * 
 * 注：0的0次方是没有倒数的，所以返回0后者1都是可以接受的，但这要跟面试官说清楚，表明我们已经考虑到这个边界值了。
 * 
 * 见代码①
 * 
 * 
 * Discuss：
 * 
 * 虽然代码①能够通过测试，但是代码①的效率还不够好。
 * 当exponent=32的时候，for循环要做31次乘法
 * 
 * 实际上，32次方等于16次方的平方，16次方等于9次方的平方。。。以此类推
 * 
 * 				|- a的n/2次方 * a的n/2次方  (n为偶数)
 * a的n次方 = 
 * 				|- a的(n-1)/2次方 * a的(n-1)/2次方 * a (n为奇数)
 * 
 * 优化后的代码见 代码②
 */

public class Power {
	/*
	 * 代码①
	 */
	public double power(double base, int exponent) {
		double result = 1.0;
		int counts = (exponent >= 0) ? exponent : -exponent;
		if (base == 0 && exponent < 0) return 0.0;
		for (int i = 0; i < counts; i++) {
			result *= base;
		}
		return (exponent >= 0) ? result :  (1.0 / result);
	}
	
	/*
	 * 代码②
	 */
	public double power_2(double base, int exponent) {
		double result = 1.0;
		int counts = (exponent >= 0) ? exponent : -exponent;
		if (base == 0 && exponent < 0) return 0.0;
		result = powerWithUnsignedExponent(base, counts);
		return (exponent >= 0) ? result :  (1.0 / result);
	}
	private double powerWithUnsignedExponent(double base, int exponent) {
		if (exponent == 0) return 1; // 如果指数为0，返回1
		if (exponent == 1) return base; // 如果指数为1，返回base
		
		double result = powerWithUnsignedExponent(base, exponent >> 1); // 递归求exponent减半(如果是奇数就是(n-1)/2)的power
		result *= result; // a的n/2次方 * a的n/2次方
		
		// 如果exponent是奇数，还要再乘一个base
		if ((exponent & 1 )== 1) {
			result *= base; 
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		double result = new Power().power_2(2, 3);
		System.out.println(result);
	}
}
