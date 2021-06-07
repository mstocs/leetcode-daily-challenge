package problems.dynamic.programming;

/**
 * 2021年06月06日17:34:39
 *
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 *
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class OnesAndZeros {
    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        //前i个元素中最多有m个0和n个1的最大子集
        int dp[][][] = new int[length+1][m+1][n+1];
        for(int i = 1; i <= length; i++) {
            int[] data= getOnesAndZeors(strs[i-1]);
            //当前元素0的个数
            int zeros = data[0];
            //当前元素1的个数
            int ones = data[1];
            //从0开始遍历，遍历到m
            for(int j = 0; j <= m; j++) {
                for(int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i-1][j][k];
                    if(zeros<=j && ones<=k) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j-zeros][k-ones]+1);
                    }
                }
            }
        }
        return dp[length][m][n];
    }

    private int[] getOnesAndZeors(String s) {
        int[] res = new int[2];
        for(int i = 0; i < s.length(); i++) {
            res[s.charAt(i)-'0']++;
        }
        return res;
    }
}
