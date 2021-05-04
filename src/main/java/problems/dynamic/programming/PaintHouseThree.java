package problems.dynamic.programming;

import java.util.Arrays;

/**
 * 2021年05月04日11:30:32
 * 在一个小城市里，有 m 个房子排成一排，你需要给每个房子涂上 n 种颜色之一（颜色编号为 1 到 n ）。有的房子去年夏天已经涂过颜色了，所以这些房子不需要被重新涂色。
 *
 * 我们将连续相同颜色尽可能多的房子称为一个街区。（比方说 houses = [1,2,2,3,3,2,1,1] ，它包含 5 个街区  [{1}, {2,2}, {3,3}, {2}, {1,1}] 。）
 *
 * 给你一个数组 houses ，一个 m * n 的矩阵 cost 和一个整数 target ，其中：
 *
 * houses[i]：是第 i 个房子的颜色，0 表示这个房子还没有被涂色。
 * cost[i][j]：是将第 i 个房子涂成颜色 j+1 的花费。
 * 请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成 target 个街区。如果没有可用的涂色方案，请返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/paint-house-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class PaintHouseThree {
    static final int MAX = Integer.MAX_VALUE;

    //动态规划
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        //将当前未填充的位置从0改为-1，然后统一所有角标从0开始，即颜色范围是从0到n-1
        for(int i = 0; i<m; ++i) {
            --houses[i];
        }

        //dp数组，第m+1个房子，填充为n+1颜色，并且前m+1个房子被划分成target+1个街区时的最小花费
        int[][][]dp = new int[m][n][target];
        //dp过程需要求每个数组元素的最小值，所以先初始化为极大值
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], MAX);
            }
        }

        //从0开始遍历每个房子
        for(int currHouse = 0; currHouse < m; currHouse++) {
            //遍历每个房子的每种填充颜色
            for (int currColor = 0; currColor < n; currColor++) {
                //已经刷了的房子不能再刷别的颜色
                if(houses[currHouse]!=-1 && houses[currHouse] !=currColor) {
                    //上述判断成立的话，代表本位置已填充颜色，并且填充的颜色和当前循环到的颜色不同，无需做任何处理，直接跳出循环
                    continue;
                }

                //blocks也需要遍历
                for (int blocks = 0; blocks < target; ++blocks) {
                    //遍历前一房屋的颜色
                    for (int preColor = 0; preColor < n; preColor++) {
                        //前一房子的颜色与当前相同的情况
                        if(currColor == preColor) {
                            //第一间房子特殊处理，其实可以放在循环外，可以优化耗时
                            if(currHouse==0) {
                                if(blocks == 0) {
                                    dp[currHouse][currColor][blocks] = 0;
                                }
                            } else {
                                //状态转移方程，当前颜色和前一间房子颜色相同时
                                dp[currHouse][currColor][blocks] = Math.min(dp[currHouse-1][currColor][blocks], dp[currHouse][currColor][blocks]);
                            }
                        } //下述判断是为了防止数组越界
                        else if(currHouse > 0 && blocks > 0){
                            //状态转移方程，当前颜色和前一间房子颜色不同时
                            dp[currHouse][currColor][blocks] = Math.min(dp[currHouse][currColor][blocks], dp[currHouse-1][preColor][blocks-1]);
                        }
                    }
                    //这里是一个技巧，把当前多花费的钱最后再加上
                    if(dp[currHouse][currColor][blocks] != MAX && houses[currHouse] == -1) {
                        dp[currHouse][currColor][blocks] += cost[currHouse][currColor];
                    }
                }
            }
        }
        //遍历最后一个节点不同颜色的情况，寻找最小值
        int ans = MAX;
        for (int lastColor = 0; lastColor < n; lastColor++) {
            ans = Math.min(ans, dp[m-1][lastColor][target-1]);
        }
        return ans == MAX ? -1 : ans;
    }
}
