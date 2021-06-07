package problems.dynamic.programming;

/**
 * 2021年06月07日12:50:35
 *
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TargetSum {


    //动态规划，找出符合条件的减号枚举个数
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if(diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length;
        int neg = diff / 2;

        //处理前i个元素，减法和为neg的最大次数
        int[][] dp = new int[n+1][neg+1];
        //无实际含义，哨兵数据，为第一个元素服务
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i -1];
            for(int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i-1][j];
                if(j >= num) {
                    dp[i][j] += dp[i-1][j-num];
                }
            }
        }
        return dp[n][neg];
    }

}
