package cn.edu.fzu.algorithm;

/*
题目描述

在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。 
输入描述:
array： 待查找的二维数组
target：查找的数字


输出描述:
查找到返回true，查找不到返回false

* 首先选取数组中右上角的数字，
* 	如果该数字等于要查找的数字，查找过程结束；					
* 	如果该数字大于要查找的数字，剔除这个数字所在的列；
* 	如果该数字小于要查找的数字，剔除这个数字所在的行。
* 
* 也就是说，如果要查找的数字不在数组的右上角，则每一次都在数组的查找范围内剔除一行或者一列
* 不断缩小查找的范围，直到找到要查找的数字，或者范围为空。
*
*
*/

public class FindNumberIn2DArray {
	public boolean find(int [][] array,int target) {
        int row = array.length, col = array[0].length;
        if (row < 1 || col < 1) return false;
        if (target < array[0][0] || target > array[row-1][col-1]) return false;
         
        int rowIndex = 0, colIndex = col - 1;
        while (rowIndex < row && colIndex >= 0) {
            // 抛弃最右列
            if (target < array[rowIndex][colIndex]) {
                colIndex -= 1;
            // 抛弃最上行
            } else if (target > array[rowIndex][colIndex]){
                rowIndex += 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
