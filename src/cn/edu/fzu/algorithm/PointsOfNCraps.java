package cn.edu.fzu.algorithm;

/*
题目：
把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率
*/

/*
 * 骰子一共6面，每个面上都有一个点数，对应1~6之间的一个数字。
 * 所以n个骰子的点数和的最小值为n，最大值为6n。另外根据排列组合的知识，n个骰子的所有点数的排列数为6的n次方。
 * 所以，要解决这个问题，我们需要先统计出每一个点数出现的次数，
 * 然后把每一个点数出现的次数除以6的n次方，就能求出每个点数出现的概率。
 * 
 * 我们可以用两个数组来存储骰子点数的每一个总数出现的次数。
 * 在一次循环中，第一次数组中的第n个数字表示骰子和为n出现的次数。
 * 在下一次循环中，我们加上一个新的骰子，
 * 此时和为n的骰子出现的次数应该等于上一次循环中骰子点数和为n-1、n-2、n-3、n-4、n-5与n-6的次数的总和，
 * 所以我们把另一个数组的第n个数字设为前一个数组对应的第n-1、n-2、n-3、n-4、n-5与n-6之和。
 * 
 * 基于这个思路，可以写出代码①
 * 
 */
public class PointsOfNCraps {
	final static int maxValue = 6; // 骰子的最大点数
	/*
	 * 代码①
	 */
	public static void printProbability(int number) {
		if (number < 1) return;
		// 用两个数组来存储骰子点数的每一个总数出现的次数，初始值都为0
		int[][] probabilities = new int[2][maxValue * number + 1];
		
		int flag = 0; // 滚动数组标识
		
		// 初始化第一个数组，表示只有一个骰子时，每个点数出现的次数
		for (int i = 1; i <= maxValue; i++) {
			probabilities[flag][i] = 1;
		}
		
		// 循环，计算有k个骰子时，每个点数和出现的次数
		for (int k = 2; k <= number; k++) {
			// 有k个骰子时，点数和不可能小于k，所以出现次数都为0
			for (int i = 0; i < k; i++) {
				probabilities[1 - flag][i] = 0;
			}
			// 计算最小值k到最大值k*6的出现次数
			for (int i = k; i <= maxValue*k; i++) {
				probabilities[1 - flag][i] = 0; // 清空
				// f(n) = f(n-1) + f(n-2) + f(n-3) + f(n-4) + f(n-5) + f(n-6);
				// 加入判断条件j<=i，防止越界
				for (int j = 1; j <= i && j <= maxValue; j++) {
					probabilities[1 - flag][i] += probabilities[flag][i - j];
				}
			}
			flag = 1 - flag; // 滚动
		}
		
		double total = Math.pow(maxValue, number); // 6的n次方
		// 依次打印所有点数和的概率
		for (int i = number; i <= maxValue * number; i++) {
			double ratio = (double) probabilities[flag][i] / total;
			System.out.println(i + ": " + ratio);
		}
	}
	
	public static void main(String[] args) {
		printProbability(3);
	}
}
