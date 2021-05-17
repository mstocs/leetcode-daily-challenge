package problems.bit.xor;

import java.util.HashSet;
import java.util.Set;

/**
 * 2021年05月16日23:24:07
 *
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 *
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxXorResultInArray {
    //整形共有32个bit位，第一位代表符号，角标从0开始，则最大位置为30
    static final int HIGH_BIT= 30;

    //思路：从最大位开始，依次判断该bit位是否可以取1
    public int findMaximumXOR(int[] nums) {
        int x =0;
        //从最大位置向下遍历
        for(int i = HIGH_BIT; i >=0; i--) {
            //记录数组中每一个元素的前几个bit位的所有可能性
            Set<Integer> seen = new HashSet<Integer>();
            for(int num : nums) {
                seen.add(num >> i);
            }

            //默认在新一轮遍历的位置填充1
            int xNext = (x <<1) + 1;
            //found为true，该位置填1，found为false填0
            boolean found = false;

            for(int num : nums) {
                //用填充1的结果反推，set里面有，代表数组存在某个数字可以使当前遍历bit位填充1
                if(seen.contains(xNext ^ (num >> i))) {
                    found = true;
                    break;
                }
            }

            //found为true，当前bit位填充1，为false，填充0，需要减去1
            if(found) {
                x = xNext;
            } else {
                x = xNext - 1;
            }
        }
        return x;
    }
}
