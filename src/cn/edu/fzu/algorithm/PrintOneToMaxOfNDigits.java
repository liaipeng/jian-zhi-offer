package cn.edu.fzu.algorithm;

import java.util.Arrays;

/*
题目：输入数字n，按顺序打印出从1到最大的n位十进制数。
比如输入3，则打印出1、2、3 ... 999
*/

/*
 * 本题乍一看好像很简单。实际上n有可能大于int的范围，甚至long的范围。
 * 所以直接用数字肯定不行。
 * 
 * 可以想到的是，用char型数组来表示数字。这样一来就要用字符来模拟加法的工作原理。
 * 
 * 见代码①
 * 
 * 
 * 代码①的思路比较直观，但是代码比较长。
 * 
 * 换一种思路：
 * 如果在数字面前补0，会发现n位所有十进制数其实就是n个从0到9的全排列。
 * 也就是说，我们把数字的每一位都从0到9排列一遍，就得到了所有的十进制数。
 * 
 * 全排列用递归很容易表达，数字的每一位都可能是0~9中的一个数，然后设置下一位。
 * 递归结束的条件是我们已经设置了数字的最后一位。
 * 具体见代码②
 */

public class PrintOneToMaxOfNDigits {
	/*
	 * 代码①
	 */
	private boolean increase(char[] number) {
		boolean isOverflow = false; // 是否超出n位数的范围
		int takeOver = 0; // 进位
		
		// 数组的最高位代表数字的个位，以此类推，这样看起来比较顺眼
		for (int i = number.length - 1; i >= 0; i--) {
			int digit = number[i] - '0' + takeOver; // 取出该位的原数字，若有进位，加1
			if (i == number.length - 1) digit ++; // 函数每被调用一次就在个位+1
			
			// 如果某一位超过10
			if (digit >= 10) {
				// 如果此时的i是最左边，那么此时还进位就超过n位数了，所以isOverflow置位true
				if (i == 0) {
					isOverflow = true;
				}
				
				// digit减10，同时进位+1
				digit -= 10;
				takeOver = 1;
				number[i] = (char) ('0' + digit);
			} else { // 如果没有超过10，没有进位，那么计算到此为止，可以结束循环了
				number[i] = (char) ('0' + digit);
				break;
			}
		}
		return isOverflow;
	}
	private void print(char[] number) {
		// 前面的0不打印
		boolean isPrefixZero = true;
		for (int i = 0; i < number.length; i++) {
			if (isPrefixZero && number[i] != '0') isPrefixZero = false;
			if (!isPrefixZero) System.out.print(number[i]);
		}
		System.out.println();
	}
	public void printOneToMaxOfNDigits(int n) {
		if (n <= 0) return;
		char[] number = new char[n];
		Arrays.fill(number, '0'); // 默认为0
		
		// 用increase方法对number加一，increase方法返回true时表示已经超过n位数了，停止循环
		while (!increase(number)) {
			print(number); // 打印函数
		}
	}
	
	
	/*
	 * 代码②
	 * 
	 * 第一次递归到index == number.length-1时，整个数组都是'0'
	 * 然后return到上一步，开始在number[number.length-1] + 1
	 * 然后就一直++++++
	 * （因为第一次print的时候，number所有都为'0'，所以会多打印一个空行出来，我懒得处理了）
	 */
	private void printOneToMaxOfDigitsRecursively(char[] number, int index) {
		if (index == number.length - 1) {
			print(number);
			return;
		}
		
		for (int i = 0; i < 10; i++) {
			number[index + 1] = (char) (i + '0');
			printOneToMaxOfDigitsRecursively(number, index + 1);
		}
	}
	public void printOneToMaxOfNDigits_2(int n) {
		if (n <= 0) return;
		char[] number = new char[n];
		
		for (int i = 0; i < 10; i++) {
			number[0] = (char) (i + '0');
			printOneToMaxOfDigitsRecursively(number, 0);
		}
	}
	
	
	
	public static void main(String[] args) {
		new PrintOneToMaxOfNDigits().printOneToMaxOfNDigits_2(3);
	}
}
