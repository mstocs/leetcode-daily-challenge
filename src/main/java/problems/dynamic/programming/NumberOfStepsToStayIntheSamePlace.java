package problems.dynamic.programming;

/**
 * 2021年05月14日00:02:40
 *
 * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
 *
 * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
 *
 * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
 *
 * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOfStepsToStayIntheSamePlace {
    final int MODULO = 1000000007;
    //dp[i][j]代表i次行动，最后位置在j-1的次数
    //状态转移方程： dp[i][j] = dp[i-1][j] + dp[i-1][j-1] + dp[i-1][j+1]
    //i范围到steps，j范围到min(steps, arrLen)
    public int numWays(int steps, int arrLen) {
        int longestPosition = Math.min(arrLen, steps + 1);
        //j=0为哨兵，默认为0，j=longestPosition=1的值也是0，是尾部哨兵
        int[][] dp = new int[steps+1][longestPosition+2];
        dp[0][1] = 1;
        for(int i = 1; i <= steps; i++) {
            for(int j = 1; j<=longestPosition; j++) {
                //这道题目最恶心的地方
                dp[i][j] = (dp[i-1][j] + dp[i-1][j-1] + dp[i-1][j+1]) % MODULO;
                // 上述正常写法无法通过测试用例（27，7）
                // 思路是没有问题的，非要按照下述丑陋的方式写才可以通过，影响心情
                // dp[i][j] = (((dp[i-1][j] + dp[i-1][j-1]) % MODULO) + dp[i-1][j+1]) % MODULO;
            }
        }
        return dp[steps][1] % MODULO ;
    }
}
