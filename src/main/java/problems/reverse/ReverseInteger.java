package problems.reverse;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseInteger {
    //思路：从最低位开始依次获得数字
    public int reverse(int x) {
        //记录结果
        int rev = 0;
        //每次循环对x取10的模，循环终止条件为x=0
        while (x!=0) {
            //保证结果不越界
            if(rev < Integer.MIN_VALUE/10 || rev > Integer.MAX_VALUE/10) {
                return 0;
            }
            //记录当前遍历到的位次的数字
            int digit = x % 10;
            //x去掉最低位
            x /= 10;
            //记录结果
            rev = rev * 10 + digit;
        }
        return rev;
    }
}
