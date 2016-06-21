package cn.edu.fzu.algorithm;

/*
题目描述

给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
*/
/*
 * 不能使用除法。
 * 
 * 最直观的做法是用连乘n-1个数字得到B[i]，但是这个方法O(n²)的时间复杂度。
 * 
 * 不妨定义
 * C[i] = A[0]*A[1]*...*A[i-1]
 * D[i] = A[i+1]*...*A[n-2]*A[n-1]
 * 
 * C[i]可以自上而下的顺序计算而来，即C[i] = C[i-1] * A[i-1]
 * D[i]可以自下而上的顺序计算出来，即D[i] = D[i+1] * A[i+1]
 * 
 * 具体见代码①
 */

public class MutipleArray {
	public int[] multiply(int[] A) {
		if (A == null || A.length == 0) return null;
        
        int[] C = new int[A.length];
        
        C[0] = 1;
        for (int i = 1; i < A.length; i++) {
            C[i] = C[i-1] * A[i-1];
        }
        
        int temp = 1; // 由于在这一次遍历中就可以把结果算出来，所以不用D来记录过程，可以节省空间。
        for (int i = A.length-2; i >= 0; i--) {
            temp *= A[i+1];
            C[i] *= temp;
        }
        
        return C;
    }
}
