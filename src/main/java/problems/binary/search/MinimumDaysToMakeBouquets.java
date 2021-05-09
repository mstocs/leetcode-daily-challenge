package problems.binary.search;

/**
 * 2021年05月09日23:33:01
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 *
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 *
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 *
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumDaysToMakeBouquets {

    /**
     * 思路：二分查找
     * 可以看出，天数大于某一值，就一定可以达成，而小于某一值就一定达不成
     */
    public int minDays(int[] bloomDay, int m, int k) {
        // 没有使用 bloomDay < m * k，防止超出int范围
        if(m > bloomDay.length / k) {
            return  -1;
        }
        //获取开花时间的最大值和最小值，为二分查找边界值
        int low = Integer.MAX_VALUE;
        int high = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            low = Math.min(low, bloomDay[i]);
            high = Math.max(high, bloomDay[i]);
        }
        while(low < high) {
            int days = low + (high - low) / 2;
            // 当前值可以满足条件时，缩小到左半区，不满足时，在右半区继续查找
            if (canMake(bloomDay, days, m, k)) {
                high = days;
            } else {
                low = days + 1;
            }
        }
        return low;
    }

    //在days天内，是否可以采集m束长度为k的花束
    private boolean canMake(int[] bloomDay, int days, int m, int k) {
        //已经采集的花束数量
        int bouquets = 0;
        //累积采集某一束时的数量
        int flowers = 0;
        for (int i = 0; i < bloomDay.length && bouquets < m; i++) {
            if (bloomDay[i] <= days) {
                flowers++;
                if(flowers == k) {
                    bouquets ++;
                    flowers = 0;
                }
            }
            //当前这朵还未开，为了保证相邻，重置一束花已采集数量为零
            else {
                flowers = 0;
            }
        }
        //花束大于等于目标m时，返回true
        return bouquets >= m;
    }

}
