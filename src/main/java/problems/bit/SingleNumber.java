package problems.bit;

/**
 * 2021年05月01日17:19:45
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 */
public class SingleNumber {

    /**
     * 将目标元素的每一个二进制位一一确定
     * 确定方式：所有原素的二进制位记起来，和3取模
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        //int类型有32位二进制,i代表二进制从右到左第几位，0代表第一位
        for (int i = 0; i < 32; ++i) {
            int total = 0;
            for (int num : nums) {
                //先将数字右移i个位数，然后和1取交集，可以取到对应位数的二进制，是1或者0，累加所有数字该位数的值
                total += ((num >> i) & 1);
            }
            //取模只有两种情况，1或者0，1的话需要在该位置填充1，0不需要特殊处理（默认填充0）
            if (total % 3 != 0) {
                //左移后与已记录结果取并
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
