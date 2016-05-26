package cn.edu.fzu.algorithm;
/*
题目描述

求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数。
*/

/*
 * 解法一：累加1到n中每个整数1出现的次数。
 * 		每次通过对10求余判断整数的个位数字是不是1。
 * 		如果这个数字大于10，除以10之后再判断各位数字是不是1。
 * 		但是这个方法时间复杂度是O(nlogn)当n非常大的时候，需要很大的计算量。
 * 
 * 
 * 解法二：从数字规律着手明显提高时间效率的解法。
 * 		我们用一个稍微大一点的数字21345作为例子来分析。
 * 
 * 		我们把从1到21345的所有数字分成两段即1-1345和1346-21345（分段好处在于便于进行递归运算，因为1345为21345去掉最高位的结果）。
 * 
 *      先来看1346-21345中1出现的次数。1的出现分为两种情况：
 *      	情况一：1出现在最高位（万位）。从1到21345的数字中，1出现在 10000-19999这10000个数字的万位中，一共出现了10000（10^4）次；
 *      
 *      	情况二：1出现在除了最高位之外的其他位中。例子中 1346-21345，这20000个数字中后面四位中1出现的次数是2000次
 *      	（2*10^3，其中2是第一位的数值，10^3是因为数字的后四位数字 其中一位为1，其余的三位数字可以在0到9这10个数字任意选择，
 *      	由排列组合可以得出总次数是2*10^3）。
 *      	（渔童注：这一段分析有点小问题，这20000个数字中后面四位中1出现的次数应该是8000次，2*4*10^3，
 *      	其中2是第一位的数值，4是后面四位的位数长度，10^3是因为数字的后四位数字 其中一位为1，
 *      	其余的三位数字可以在0到9这10个数字任意选择，由排列组合可以得出总次数是2*4*10^3，下面的代码实现，原作者写的是正确的）
 *      
 *      至于从1到1345的所有数字中1出现的次数，我们就可以用递归地求得了。这也是我们为什么要把1-21345分为1-1345和1346-21345两段的原因。
 *      因为把21345的最高位去掉就得到1345，便于我们采用递归的思路。
 *      
 *      分析到这里还有一种特殊情况需要注意：前面我们举例子是最高位是一个比1大的数字，此时最高位1出现的次数10^4（对五位数而言）。
 *      但如果最高位是1 呢？比如输入12345，从10000到12345这些数字中，1在万位出现的次数就不是10^4次，而是2346次了，
 *      也就是除去最高位数字之后剩下的 数字再加上1。
 * 
 */


public class NumberOf1Between1AndN {
	public int numberOf1Between1AndN_Solution(int n) {
	    if (n <= 0) return 0;
	    char[] number = String.valueOf(n).toCharArray();
	    return numberOf1(number, 0);
    }
	private int numberOf1(char[] number, int start) {
		if (start == number.length) return 0;
		
		int first = number[start] - '0'; // 取最高位
		int len = number.length - start; // 剩余数字的长度
		
		if (len == 1 && first == 0) return 0; // 如果长度为1，即只有个位数
		if (len == 1 && first > 0) return 1;
		
		// 求1在最高位出现的次数
		int numFirstDigit = 0;
		// 如果number是"21345"，numFirstDigit是数字10000~19999的第一个位中的数目(10^4)。
		if (first > 1) {
			numFirstDigit = powerBase10(len - 1);
			// 如果number是"12345"，numFirstDigit是数字10000~12345的第一个位中的数目(2345+1)。
		} else if (first == 1) {
			numFirstDigit = atoi(number, start+1) + 1;
		}
		
		// numOtherDigits是1346~21345除了第一位之外的数位中的数目。
		int numOtherDigits = first * (len-1) * powerBase10(len-2);
		// numRecursive是1~1345中的数目
		int numRecursive = numberOf1(number, start+1); // 递归，去掉最高位。
		
		return numFirstDigit + numOtherDigits + numRecursive; 
	}
	// 10的n次方
	private int powerBase10(int n) {
		int result = 1;
		for (int i = 0; i < n; i++) {
			result *= 10;
		}
		
		return result;
	}
	// 将字符数组转为int
	private int atoi(char[] number, int start) {
		int result = 0;
		while (start < number.length) {
			result *= 10;
			result += (number[start] - '0');
			start ++;
		}
		return result;
	}
	
	public static void main(String[] args) {
		int x = new NumberOf1Between1AndN().numberOf1Between1AndN_Solution(10);
		System.out.println(x);
	}
}
