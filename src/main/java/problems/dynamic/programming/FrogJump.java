package problems.dynamic.programming;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.util.Arrays;

/**
 * 2021年04月29日12:48:51
 *
 * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
 *
 * 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
 *
 * 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
 *
 * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/frog-jump
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FrogJump {

    //代表从第i块石头，前一步跳了dis个距离时，能否过河，可以为true，否则为false
    private Boolean[][] rec;

    public boolean canCross(int[] stones) {
        int n = stones.length;
        rec = new Boolean[n][n];
        return dfs(stones, 0, 0);
    }

    //记忆化搜索+动态规划
    private boolean dfs(int[] stones, int i, int lastDis) {
        //如果i为最后一个石头，则代表成功，必为true
        if (i == stones.length - 1) {
            return true;
        }
        //
        if(rec[i][lastDis] != null) {
            return rec[i][lastDis];
        }

        //遍历下一步的3种可能
        for(int curDis = lastDis - 1; curDis <= lastDis + 1; curDis++) {
            //大于0为必要条件，否则将不会前进
            if(curDis > 0) {
                //二分查找下一步可以跳到的位置是否存在
                int j = Arrays.binarySearch(stones, i + 1, stones.length, curDis + stones[i]);
                //如果存在并且从下一个位置开始，前一步跳了curDis时，最终可以到达
                //这里也是动态规划的入口
                if(j >= 0 && dfs(stones, j, curDis)) {
                    //则记录当前位置，前一步跳lastDis的时候，结果为true
                    return rec[i][lastDis] = true;
                }
            }
        }
        //遍历完结果都不为true的话，结果为false
        return rec[i][lastDis]=false;
    }
}
