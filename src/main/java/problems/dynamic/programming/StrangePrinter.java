package problems.dynamic.programming;

/**
 * 2021年05月24日23:02:54
 *
 * 有台奇怪的打印机有以下两个特殊要求：
 *
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/strange-printer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StrangePrinter {
    public int strangePrinter(String s) {
        int n = s.length();
        //dp[i][j]代表从i(包含)到j(包含)的最优解
        int[][]dp = new int[n][n];
        //i从高到低遍历，是为了转态转移方程用的数据已经有值
        for(int i = n -1; i >= 0; i--) {
            //一个字符，最少需要一次打印
            dp[i][i] = 1;
            for(int j = i + 1;j < n; j++) {
                //头尾相同的情况，和没有尾的情况相同
                if(s.charAt(j) == s.charAt(i)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    //头尾不同的情况，需要遍历所有可能，求最小值
                    int min = Integer.MAX_VALUE;
                    for(int k = i; k < j; k++) {
                        //这里无需考虑k是否和k+1相同，如果相同的话dp[i][k]+dp[k+1][j]应该是比最优解大的，遍历完取min即可
                        min = Math.min(min, dp[i][k]+dp[k+1][j]);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][n-1];
    }
}
