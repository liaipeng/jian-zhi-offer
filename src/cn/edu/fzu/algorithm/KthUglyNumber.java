package cn.edu.fzu.algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*
题目描述

把只包含因子2、3和5的数称作丑数（Ugly Number）。
例如6、8都是丑数，但14不是，因为它包含因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
*/

/*
 * 如果是按照顺序判断每个整数是不是丑数，虽然算法非常直观
 * 但是算法效率很低，最大的问题是每个整数都需要计算，即使他不是丑数。
 * 
 * 解法一：
 * 创建数组保存已经找到的丑数，用空间换时间。
 * 根据丑数的定义，丑数应该是另一个丑数乘以2、3或者5的结果。
 * 因此，我们可以创建一个数组，里面的数字是排好序的丑数，每一个丑数都是前面的丑数乘以2、3或者5得到的。
 * 
 * 这种思路的关键在于怎样确保数组里面的丑数是排好序的。
 * 假设数组中已经有若干个丑数排好序，并且把已有最大的丑数记做M。
 * 接下来分析如何生成下一个丑数。
 * 该丑数肯定是前面某一个丑数乘以2、3或者5的结果。
 * 因为已有的丑数是按顺序存放在数组中的，
 * 所以对乘以2而言，肯定存在某一个丑数T2，排在它之前的每一个丑数乘以2得到的结果都小于已有最大丑数，在它之后的乘以2的结果都会太大。
 * 我们只需要记下这个丑数的位置，同时每次生成新的丑数的时候，去更新这个T2。
 * T3和T5同理。
 * 
 * 具体见代码①
 * 
 * 
 * 解法二：
 * 思想跟解法一是一样的，只不过是用队列实现。但是理解起来还没解法一好理解。
 * 1）初始化array和队列：Q2 Q3 Q5
 * 2）将1插入array
 * 3）分别将1*2、1*3 、1*5插入Q2 Q3 Q5
 * 4)令x为Q2 Q3 Q5中的最小值，将x添加至array尾部
 * 5）若x存在于：
 *  Q2：将 x * 2、x * 3、x*5 分别放入Q2 Q3 Q5，从Q2中移除x
 *  Q3：将 x * 3、x*5 分别放入Q3 Q5，从Q3中移除x
 *  Q5：将 x * 5放入Q5，从Q5中移除x
 * 6）重复步骤4~6，知道找到第k个元素
 * 
 * 见代码②
 * 
 * 解法三：
 * 用1个优先队列（小根堆）实现代替代码②的3个队列。
 * 代码简洁了非常多，但是因为插入新的元素要不断调整堆，而且还要判断重复的元素，效率比代码②要低。
 * 
 * 见代码③
 * 
 */

public class KthUglyNumber {
	/*
	 * 代码①
	 */
    public int getUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        int[] uglyNums = new int[index];  // 存放丑数
        uglyNums[0] = 1; // 第一个丑数为1
        int nextUglyIndex = 1; // 要生成的下一个丑数的下标
        
        int index2 = 0, index3 = 0, index5 = 0; // T2、T3和T5在uglyNums中的下标，初始都为0。
        
        // 不断生成新的丑数，直到下标到达指定index
        while (nextUglyIndex < index) {
        	// 下一个丑数是T2*2、T3*3和T5*5中的最小值
        	uglyNums[nextUglyIndex] = Math.min(Math.min(uglyNums[index2]*2, uglyNums[index3]*3), uglyNums[index5]*5);
        	
        	// 如果此时的T2已经不大于数组中的最大丑数了，更新T2的下标，T3和T5同理。
        	// 其实就是每次都找到比最大丑数M大，同时距离最近的T2，T3和T5
        	while (uglyNums[index2] * 2 <= uglyNums[nextUglyIndex]) {
        		index2++;
        	}
         	while (uglyNums[index3] * 3 <= uglyNums[nextUglyIndex]) {
         		index3++;
        	}
         	while (uglyNums[index5] * 5 <= uglyNums[nextUglyIndex]) {
         		index5++;
        	}
        	
        	nextUglyIndex ++;
        }
        
        return uglyNums[nextUglyIndex-1];
    }
    
    /*
     * 代码②
     */
    public int getUglyNumber_Solution_2(int index) {
        if (index <= 0) return 0;
        
        int min = 0;
        // 三个队列分别存放*2, *3和*5的uglyNumber
        Queue<Integer> q2 = new LinkedList<>(); 
        Queue<Integer> q3 = new LinkedList<>();
        Queue<Integer> q5 = new LinkedList<>();
        q2.offer(1); // 初始化
        
        for (int i = 0; i < index; i++) {
        	// 取三个队列队首的uglyNumber，如果队列为空，用最大值代替。
        	int val2 = (q2.isEmpty()) ? Integer.MAX_VALUE : q2.peek();
        	int val3 = (q3.isEmpty()) ? Integer.MAX_VALUE : q3.peek();
        	int val5 = (q5.isEmpty()) ? Integer.MAX_VALUE : q5.peek();
        	
        	min = Math.min(Math.min(val2, val3), val5); // 取三个uglyNumber中的最小值
        	
        	// 如果选择的是val2
        	if (min == val2) {
        		q2.poll(); // 摘掉q2的队首
        		// 在q2和q3中添加下一个的uglyNumber
        		q2.offer(min * 2); 
        		q3.offer(min * 3);
        		// 如果选择的是val3
        	} else if (min == val3) {
        		q3.poll();
        		q3.offer(min * 3);
        	} else {
        		q5.poll();
        	}
        	q5.offer(min * 5);
        }
        
        return min;
    }
    
    /*
     * 代码③
     */
    public int getUglyNumber_Solution_3(int index) {
        if (index <= 0) return 0;
        
        int min = 0, t2, t3, t5;
        
        PriorityQueue<Integer> q = new PriorityQueue<>(); // 优先队列
        Set<Integer> set = new HashSet<Integer>(); // 判断队列中是否已经存在过该元素
        q.offer(1);
        
        
        for (int i = 0; i < index; i++) {
        	min = q.poll();
        	t2 = min * 2;
        	if (t2 > 0 && set.add(t2)) q.offer(t2); // t2 > 0是防止溢出的情况
        	t3 = min * 3;
        	if (t3 > 0 && set.add(t3)) q.offer(t3);
        	t5 = min * 5;
        	if (t5 > 0 && set.add(t5)) q.offer(t5);
        	
        	System.out.println(q);
        }
        
        return min;
    }
    
    public static void main(String[] args) {
		int val1 = new KthUglyNumber().getUglyNumber_Solution_3(1500);
		System.out.println(val1);
		
		int val2 = new KthUglyNumber().getUglyNumber_Solution_2(1500);
		System.out.println(val2);
	}
}
