package cn.edu.fzu.algorithm;

/*
题目描述

统计一个数字在排序数组中出现的次数。
*/

/*
 * 如果用顺序查找，要用O(n)的时间。
 * 
 * 既然是排好序的数组，应该想到用二分查找。
 * 用二分查找分别找出第一个k出现的位置，和最后一个k出现的位置，即可得出k共出现了几次。
 * 
 * 见代码①。
 */

public class AppearTimesInSortedArray {
	// 找第一个K的下标
	private int getFirstK(int[] array, int k, int low, int high) {
		if (low > high) {
			return -1; // 没有找到k，返回-1
		}
		
		int mid = low + ((high - low) >> 1);
		
		if (array[mid] == k) { // 如果中间的数刚好为k
			// 判断其前一个数字是否为k，若不是，则mid指向的就是第一个k。或者mid是0，也会是第一个k
			if ((mid > 0 && array[mid-1] != k) || mid == 0) {
				return mid;
			} else { // 否则，第一个k在左半边数组
				high = mid - 1;
			}
		} else if (array[mid] > k) { // 如果大于k，k在数组左半边
			high = mid - 1;
		} else { // 否则，在右半边
			low = mid + 1;
		}
		
		return getFirstK(array, k, low ,high);
	}
	// 找最后一个K的下标
	private int getLastK(int[] array, int k, int low, int high) {
		if (low > high) {
			return -1; // 没有找到k，返回-1
		}
		
		int mid = low + ((high - low) >> 1);
		
		if (array[mid] == k) { // 如果中间的数刚好为k
			// 判断其后一个数字是否为k，若不是，则mid指向的就是最后一个k。
			// 或者mid是array.length-1，也是最后一个k
			if ((mid < array.length-1 && array[mid+1] != k) || mid == array.length-1) { 
				return mid;
			} else { // 否则，最后一个k在右半边数组
				low = mid + 1;
			}
		} else if (array[mid] < k) { // 如果小于k，k在数组右半边
			low = mid + 1;
		} else { // 如果小于k，k在数组左半边
			high = mid - 1; 
		}
		
		return getLastK(array, k, low ,high);
	}
	
    public int getNumberOfK(int[] array , int k) {
    	if (array == null || array.length == 0) return 0;
    	
    	int first = getFirstK(array, k, 0, array.length-1);
    	int last = getLastK(array, k, 0, array.length-1);
    	
    	return (first != -1) ? last - first + 1 : 0; // 如果first不为-1，那么k的数量就是last-first+1。否则，返回0
    }
}
