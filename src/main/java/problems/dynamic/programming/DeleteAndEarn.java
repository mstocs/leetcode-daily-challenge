package problems.dynamic.programming;

/**
 * 2021年05月05日10:19:23
 *
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-and-earn
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DeleteAndEarn {

    /**
     * 预处理后使用动态规划
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        //找出数组的最大值
        int maxVal = 0;
        for(int i :nums) {
            maxVal = Math.max(maxVal, i);
        }

        //dp下标代表要选择的数字，值代表选择该数字带来的收益，使用下标可以处理+1和-1的情况
        int[] dp = new int[maxVal + 1];

        //dp初始化
        for(int i : nums) {
            dp[i] += i;
        }

        return dp(dp);
    }

    //每次遍历时做出二选一： a.选择dp(i-1)，不能累加本次值； b.选择dp(i-2)+currVal，累加本次值
    //上述二者选择比较大的值，即为当前位置能偷到的最大值
    private int dp(int[] nums) {
        //初始化第一个和第二个元素
        int first = nums[0];
        //第二个的最优值为1和2的最大值
        int second = Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            //临时存储
            int temp = second;
            //本次的选择，将会作为下一次计算的second
            second = Math.max(temp, first + nums[i]);
            //本次的second和变成下一次计算的first
            first = temp;
        }
        //循环结束的second即为最后位置的最优解
        return second;
    }

}
