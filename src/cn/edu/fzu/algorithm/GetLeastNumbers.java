package cn.edu.fzu.algorithm;

import java.util.ArrayList;
import java.util.TreeSet;

/*
题目描述

输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
*/

/*
 * 解法一：
 * 		和MoreThanHalfNum一题中的代码②思路一样，基于partition函数来解决这个问题。
 * 		如果基于数组的第k个数字来调整，使得比第k个数字小的所有数字都位于数组的左边，比第k个数字大的所有数字都位于数组的右边。
 * 		这样调整之后，位于数组中左边的k个数字就是最小的k个数字。
 * 		
 * 		这样做的缺点是需要修改原数组，好处是复杂度为O(n);
 * 		具体见代码①。
 * 
 * 解法二：
 * 		此解法适用于海量数据的处理。
 * 		第一步，首先创建一个大小为k的数据容器来存储最小的k个数字。
 * 		第二步，接下来每次从输入的n个数字中读入一个数：
 * 			如果容器中已有的数字少于k个，则直接把这次读入的数放入容器之中。
 * 			如果容器中已有k个数字了，找出容器中最大值，拿待插入的数和最大值进行比较：
 * 				如果比最大值大，此数不可能是最小的k个数之一。
 * 				如果比最大值小，则用这个数替换已有的最大值。
 * 		
 * 		对于第二步中的操作，如果用一个二叉树来实现这个容器，那么就能在O(logk)时间内实现这三步操作。（用大根堆或者红黑树）
 * 		所有对于n个数，总的时间复杂度为O(nlogk)。
 * 		Java中TreeSet底层就是用红黑树实现的，所以采用TreeSet作为容器。
 * 		但是TreeSet不能有重复值，本题测试用例没有重复值。
 * 		
 * 		具体见代码②。
 * 		
 * 
 */

public class GetLeastNumbers {
	/*
	 * 代码①
	 */
	public ArrayList<Integer> getLeastNumbers_Solution(int[] input, int k) {
		ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k <= 0) return list;
        
        int start = 0, end = input.length-1;
        int index = partition(input, start, end);
        
        // 因为数组是从0开始的，所以第k小的数，也就是下标k-1的数
        while (index != k - 1) {
        	if (index > k - 1) {
        		end = index - 1;
        		index = partition(input, start, end);
        	} else {
        		start = index + 1;
        		index = partition(input, start, end);
        	}
        }
        
        // 将调整后的0到k-1，放入list中
        for (int i = 0; i < k; i++) {
        	list.add(input[i]);
        }
        return list;
    }
	private int partition(int[] nums, int low, int high) {
		int pivot = nums[low]; // 取nums[low]作为本轮划分基准。
		
		while (low < high) {
			// 从右往左寻找是否有比基准小的数
			while (low < high && nums[high] >= pivot) {
				high--;
			}
			nums[low] = nums[high]; // 将小于基准的数放到左边。
			
			// 从左往右寻找是否有比基准大的数
			while (low < high && nums[low] < pivot) {
				low++;
			}
			nums[high] = nums[low]; // 将大于基准的数放到右边。
		}
		nums[low] = pivot; // 此时low == high，用哪个都行
		return low;
	}
	
	/*
	 * 代码②
	 */
	public ArrayList<Integer> getLeastNumbers_Solution_2(int[] input, int k) {
		ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k <= 0) return list;
        
        TreeSet<Integer> set = new TreeSet<>();
        
        for (int num : input) {
        	if (set.size() < k) {
        		set.add(num);
        	} else {
        		// TreeSet默认从小到大排序，set.last()获取的就是最大值。反之，用set.first()是获取最小值。
        		if (num < set.last()) {
        			set.remove(set.last());
        			set.add(num);
        		}
        	}
        }
        
        for (Integer s : set) {
        	list.add(s);
        }
        
        return list;
    }
	
	
}
