package problems.dynamic.programming;

/**
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 *
 * 题目数据保证答案符合 32 位整数范围。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 示例 2：
 *
 * 输入：nums = [9], target = 3
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSum {

    /**
     * 思路：动态规划，目标值从0开始计算到target
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[]nums, int target) {
        //初始化dp数组，长度为target+1（多存储0）
        int[] dp = new int[target + 1];
        //边界条件，目标值是0的话有一种解法，即不填充元素
        dp[0] = 1;
        //dp开始，从1开始计算到target
        for(int i = 1; i <= target; i++) {
            //遍历数组中的元素
            for (int num : nums) {
                //只有数据小于等于目标值的时候，才有可能出现新的结果
                if(num <= i) {
                    //因为num存在而给结果带来的增量，就等于target等于i-num的结果数量
                    dp[i] += dp[i-num];
                }
            }
        }
        return dp[target];
    }
}
