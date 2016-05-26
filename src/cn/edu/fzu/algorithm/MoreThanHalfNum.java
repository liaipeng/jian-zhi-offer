package cn.edu.fzu.algorithm;
/*
题目描述

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
*/

/*
 * 找出数组的主元素，和LeetCode中的Major Element一样的。
 * 唯一的不同点是这一题有可能出现没有主元素的情况。
 * 
 * 所以，在方法的最后需要加上一个判断。
 * 
 * 代码①，LeetCode中的解法。
 * 
 * 解法二，书本中的解法。基于快排的思想。
 * 		首先，在数组中随机选择一个数字，然后调整顺序，比选中数字小的排到其左边，选中大的数字排到右边（也就是快排的过程）。
 * 			如果这个选中的数的下标刚好是n/2，那么这个数字就是数组的中位数。
 * 			如果下标大于n/2，那么中位数在它的左边。反之，则在右边。
 * 		得到该中位数后，在checkMoreThanHalf方法中判断一下即可。
 * 见代码②
 */

public class MoreThanHalfNum {
	/*
	 * 代码①
	 */
	public int moreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) return 0;
        int major = 0, count = 0;
        
        for (int num : array) {
            if (count == 0) {
                major = num;
                count ++;
            } else {
                if (major == num) {
                    count ++;
                } else {
                    count --;
                }
            }
        }
        
        return checkMoreThanHalf(array, major) ? major : 0;
    }
    private boolean checkMoreThanHalf(int[] array, int major) {
        int times = 0;
        for (int num : array) {
            if (num == major) {
                times++;
            }
            if (times > array.length/2) return true;
        }
        return false;
    }
    
    /*
     * 代码②
     */
    public int moreThanHalfNum_Solution_2(int[] array) {
        if (array == null || array.length == 0) return 0;
        int mid = array.length >> 1, start = 0, end = array.length-1;
        int index = partition(array, start, end);
        while (index != mid) {
        	if (index > mid) {
        		end = index - 1;
        		index = partition(array, start, end);
        	} else {
        		start = index + 1;
        		index = partition(array, start, end);
        	}
        }
        
        return checkMoreThanHalf(array, array[mid]) ? array[mid] : 0;
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
    
    public static void main(String[] args) {
		int[] array = {1,2,3,2,2,2,5,4,2};
		int major = new MoreThanHalfNum().moreThanHalfNum_Solution_2(array);
		System.out.println(major);
	}
}
