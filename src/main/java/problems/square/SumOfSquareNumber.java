package problems.square;

/**
 * 2021年04月28日22:12:02
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 * https://leetcode-cn.com/problems/sum-of-square-numbers/
 */
public class SumOfSquareNumber {


    //双指针
    public boolean judgeSquareSum(int c) {
        //左指针为0
        long left = 0;
        //右指针从c的平方根开始
        long right = (long) Math.sqrt(c);
        //终止条件为小于等于（等于的情况也会出现）
        while (left <= right) {
            long sum = left * left + right * right;
            if(sum == c) {
                return true;
            }
            // 和大于目标，则缩小较大的值
            else if (sum > c) {
                right--;
            }
            // 和小于目标，则增加较小的值
            else {
                left ++;
            }
        }
        return false;
    }

}