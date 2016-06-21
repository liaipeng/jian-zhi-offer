package cn.edu.fzu.algorithm;

/*
题目描述

地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
但是不能进入行坐标和列坐标的数位之和大于k的格子。 
例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
*/

/*
 * 和前面的题目类似，这个方格也可以看出一个m*n的矩阵。同样在这个矩阵中，除边界上的格子之外其他格子都有四个相邻的格子。
 * 机器人从坐标(0,0)开始移动。当它准备进入坐标为(i,j)的格子时，通过检查坐标的数位和来判断机器人是否能够进入。
 * 如果机器人能够进入坐标为(i,j)的格子，我们接着再判断它能否进入四个相邻的格子(i,j-1)、(i-1,j),(i,j+1)和(i+1,j)。
 * 因此，我们可以用如下的代码来实现回朔法
 */
public class MovingRangeOfRobot {
	public int movingCount(int threshold, int rows, int cols) {
        int flag[][] = new int[rows][cols]; //记录是否已经走过
        return movingCountCore(0, 0, rows, cols, flag, threshold);
    }
 
    private int movingCountCore(int i, int j, int rows, int cols, int[][] flag, int threshold) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || numSum(i) + numSum(j)  > threshold || flag[i][j] == 1) return 0;    
        flag[i][j] = 1;
        return movingCountCore(i - 1, j, rows, cols, flag, threshold)
            + movingCountCore(i + 1, j, rows, cols, flag, threshold)
            + movingCountCore(i, j - 1, rows, cols, flag, threshold)
            + movingCountCore(i, j + 1, rows, cols, flag, threshold)
            + 1;
    }
    private int numSum(int i) {
        int sum = 0;
        do{
            sum += i%10;
        }while((i = i/10) > 0);
        return sum;
    }
}
