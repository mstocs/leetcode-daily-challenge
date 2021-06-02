package problems.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 2021年06月02日23:16:07
 * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 *
 * 子数组大小 至少为 2 ，且
 * 子数组元素总和为 k 的倍数。
 * 如果存在，返回 true ；否则，返回 false 。
 *
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ContinuousSubArraySum {
    /**
     * 思路：前缀和+哈希表+同余定理
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        //题目要求子数组最少两个元素，长度不及两个元素，必定false
        if(n<2) {
            return false;
        }
        //key：前缀和的余数 value:第一次出现的前缀
        Map<Integer, Integer> map = new HashMap<>();
        //保证数组前两个元素即满足条件的情况
        map.put(0, -1);
        //前缀和的余数
        int remainder = 0;
        for(int i = 0; i < n; i++) {
            //注意这里只需要使用前一个前缀和的余数即可，使用了同余定理
            remainder = (remainder + nums[i]) % k;
            //map中已存在相同余数，需要判断子数组长度大于等于2，才可返回true
            if(map.containsKey(remainder)) {
                int preIndex = map.get(remainder);
                if(i - preIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        //遍历完数组，返回false
        return false;
    }
}
