package cn.edu.fzu.algorithm;

/*
题目描述

在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是重复的数字2或者3。
*/
/*
 * 解法一
 * 	解决这个问题的一个简单的方法是先把输入的数组排序，然后在排好序的数组中找出重复的数字。
 * 	缺点是时间复杂度高，需要O(nlogn)。
 * 
 * 解法二
 * 	还可以用哈希表。从头到尾按顺序扫描数组的每个数，每扫描到一个数字的时候，都可以用O(1)的时间来判断哈希表里是否已经包含了该数字。
 * 	如果哈希表中还没有该数字，加入该数字；如果已经存在，则找到重复元素。
 * 	缺点是需要O(n)的额外空间。
 * 	具体见代码①。
 * 
 * 解法三
 * 	不需要额外空间的解法。
 * 	从头到尾一次扫描这个数组中的每个数字。当扫描到下标为i的数字时，首先比较这个数字（用m表示）是不是等于i：
 * 		如果是，接着扫描下一个数字。
 * 		如果不是，再拿它和第m个数字进行比较。如果和第m个数字相等，就找到了一个重复的数字；否则，交换两个数字。直到找到重复的数字。
 * 
 * 	具体见代码②
 * 	尽管代码中有两重循环，单每个数字最多只要交换两次就能找到属于它自己的位置，因此总的时间复杂度是O(n)。
 * 	另外，所有的操作都是在数组上进行，空间复杂度为O(1)。
 * 
 */


public class DuplicateNumber {
	/*
	 * 代码①
	 */
	public boolean duplicate(int[] numbers, int length, int[] duplication) {
    	if (numbers == null || numbers.length == 0) return false;
        boolean[] helper = new boolean[numbers.length]; // 记录数字是否出现过，默认为false
        
        for (int num : numbers) {
            if (helper[num]) { // 若数字已经出现过，就是重复数字。
                duplication[0] = num;
                return true;
            }
            helper[num] = true;
        }
        return false;
    }
	
	/*
	 * 代码②
	 */
	 public boolean duplicate_2(int[] numbers, int length, int[] duplication) {
	    	if (numbers == null || numbers.length == 0) return false;
	    	
	        for (int i = 0; i < numbers.length; i++) {
	            if (numbers[i] == i) continue; // 下标为i的数字刚好等于i，继续遍历下一个数。
	            if (numbers[i] == numbers[numbers[i]]) { // 否则，拿它和第m个数字进行比较，如果相等，就找到了一个重复数字。
	                duplication[0] = numbers[i];
	                return true;
	            }
	            // 否则，交换两个数字
	            int temp = numbers[i];
	            numbers[i] = numbers[temp];
	            numbers[temp] = temp;
	        }
	        
	        return false;
	 }
}
