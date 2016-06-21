package cn.edu.fzu.algorithm;

import java.util.ArrayList;
import java.util.List;

/*
题目：

每年六一儿童节,NowCoder都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
HF作为NowCoder的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
然后,他随机指定一个数m,让编号为0的小朋友开始报数。
每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,
从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到NowCoder名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
请你试着想下,哪个小朋友会得到这份礼品呢？

也就是:
0,1,...,n-1这n个数字排成一个圆圈，从数字0开始从这个圆圈里删除第m个数字。然后从被删掉的数字的下一个数字开始，继续删除第m个数字。
直到圈中剩下最后一个数字。
求出这个圆圈里剩下的最后一个数字。
*/

/*
 * 一、经典的解法，用环形链表模拟圆圈
 * 		创建一个总共n个结点的ArrayList模拟圆圈，然后每次在这个链表中删除第m个结点。
 * 		由于ArrayList本身不是一个环形结构，因此每当扫描到链表末尾的时候，记得把指针移回表头。
 * 
 * 		见代码①		
 * 		
 * 		也可以直接用数组进行模拟，但是效率比较低，见代码②
 * 
 * 二、用数学公式推导，具体见书本。
 * 
 */

public class LastRemainingInCircle {
	/*
	 * 代码①
	 */
	public int lastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1) return -1;
        List<Integer> numbers = new ArrayList<>(); // 模拟圆圈
        
        // 将数字存入圆圈
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }
        
        int index = 0; // 链表的指针
        while (numbers.size() > 1) {
            index = (index + m - 1) % numbers.size(); // 第m个，实际上是向前走m-1步，通过%numbers.size()来模拟圆圈。
            numbers.remove(index); // 每次删除第m个元素，直到size == 1，跳出循环
        }
        return numbers.get(0); // 返回剩下的元素
    }
	
	/*
	 * 代码②
	 */
	public int lastRemaining_Solution_2(int n, int m) {
        if (n < 1 || m < 1) return -1;
        
        int[] numbers = new int[n]; // 长度为n的数组，不需要初始化。
        
        int index = -1, step = 0, count = n;
        while (count > 0) { // 注意，这里循环结束的条件是所有的元素都被删除
            index++;          //指向上一个被删除对象的下一个元素。
            if (index >= n) index = 0; // 模拟圆圈
          	if (numbers[index] == -1) continue; // 跳过被标识为删除的元素
            step++; // 步数+1
            if (step == m) { // 当步数为m时
                numbers[index] = -1; // 删除该元素
                step = 0; // 步数置为0
                count--; // 元素个数-1 
            }
        }
        return index; // 返回最后被删除的那个元素。
    }
}
