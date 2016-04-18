package cn.edu.fzu.algorithm;

import java.util.ArrayList;

/*
 题目描述

 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 例如，如果输入如下矩阵：
 1 	2 	3 	4 
 5 	6 	7 	8 
 9 	10 	11 	12 
 13 	14 	15 	16 
 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */

/*
 * 这题还是有点复杂。
 * 考察的是用图形帮助理解题目，找出其中规律，有了思路后再着手编码，否则会越编越乱。
 * 
 * 这是书上的讲解：
 * 
 * 	当我们遇到一个复杂问题的时候，可以用图形来帮助我们思考。
 * 	由于是以外圈到内圈的顺序依次打印，可以把矩阵想象成若干个圈。
 * 	我们可以用一个循环来打印矩阵，每一次打印矩阵中的一个圈。
 * 	
 * 	接下来分析循环结束的条件：
 * 		假设这个矩阵的行数是rows，列数是cols。打印第一圈的左上角坐标是(0,0)，第二圈的是(1,1)。
 * 		可以看到，左上角的坐标行标和列标总是相同的。
 * 			对于5*5的矩阵而言，最后一圈只有一个数字，坐标为(2,2)。我们发现5>2*2。
 * 			对于6*6的矩阵而言，最后一圈有4个数字，其左上坐标仍然为(2,2)。我们发现6>2*2依然成立。
 * 		于是我们可以得出，让循环继续的条件是cols > startX * 2 并且 rows > startY * 2。
 * 		也就是说，不满足此条件是，打印结束。
 * 
 * 	接着我们来分析如何打印一圈的功能，即如何实现printMatrixInCircle。
 * 		打印一圈可以分为4个步骤：左到右，上到下，右到左，下到上。
 * 		不过，值得注意的是，最后一圈有可能退化成只有一行、只有一列，甚至只有一个数字。
 * 		此时打印就不在需要4步，否则将可能出现数组越界。
 * 
 * 		因此，我们要仔细分析打印每一步的前提条件。
 * 			我们发现，第一步总是需要的，因为一圈至少有一行。
 * 			如果只有一行，那么第二步就不用了。也就是说，需要第二步的前提是，终止行号大于起始行号。
 * 			第三步的前提是圈内至少有两行两列，也就是说除了终止行号大于起始行号，还要终止列号大于起始行号。
 * 			第四步的前提是至少有三行两列，也就是说除了终止列号大于起始列号，还要终止行号大于起始行号至少2。
 * 			
 * 	具体实现见代码①
 * 
 */

public class PrintMatrix {
	/*
	 * 代码①
	 */
	public ArrayList<Integer> printMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return new ArrayList<Integer>();
		ArrayList<Integer> al = new ArrayList<>();

		int start = 0; // 每圈的起始坐标
		int rows = matrix.length, cols = matrix[0].length;

		// 逐圈打印
		while (rows > start * 2 && cols > start * 2) {
			printMatrixInCircle(matrix, start++, rows, cols, al);
		}

		return al;
	}
	// 打印一圈
	private void printMatrixInCircle(int[][] matrix, int start, int rows,
			int cols, ArrayList<Integer> al) {
		int endX = cols - 1 - start, endY = rows - 1 - start; // 终止坐标

		// step1. print left to right
		for (int i = start; i <= endX; i++) {
			al.add(matrix[start][i]);
		}

		if (start < endY) { // 有两列才进入第二步
			// step2. print up to down
			for (int i = start + 1; i <= endY; i++) {
				al.add(matrix[i][endX]);
			}
		}

		if (start < endY && start < endX) { // 有两行两列才进入第三步
			// step3. print right to left
			for (int i = endX - 1; i >= start; i--) {
				al.add(matrix[endY][i]);
			}
		}

		if (start < endY - 1 && start < endX) { // 有三行两列才进入第四步
			// step4. print down to up
			for (int i = endY - 1; i >= start + 1; i--) {
				al.add(matrix[i][start]);
			}
		}
	}
}
