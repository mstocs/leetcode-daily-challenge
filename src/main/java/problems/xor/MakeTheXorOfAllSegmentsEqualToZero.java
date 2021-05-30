package problems.xor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 2021年05月30日19:41:43
 *
 * 给你一个整数数组 nums​​​ 和一个整数 k​​​​​ 。区间 [left, right]（left <= right）的 异或结果 是对下标位于 left 和 right（包括 left 和 right ）之间所有元素进行 XOR 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。
 *
 * 返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/make-the-xor-of-all-segments-equal-to-zero
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MakeTheXorOfAllSegmentsEqualToZero {
    //思路：动态规划，首先理解题意，其实是找使首个子数组亦或结果为0的最小修改次数，具体可看图解
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int max = 1024;
        //代表使前i列亦或结果为j的最小修改次数
        int[][]f = new int[k][max];
        //前k列的最小修改次数
        int[] g= new int[k];
        for(int i = 0; i < k; i++) {
            Arrays.fill(f[i], 0x3f3f3f3f);
            g[i] = 0x3f3f3f3f;
        }

        //cnt的目的是记录行数（不是每一列都是k行）
        for(int i = 0,  cnt = 0; i < k; i++, cnt = 0) {
            Map<Integer, Integer> map = new HashMap<>();
            //将每一列各数字出现的次数存入map中
            for(int j = i; j < n; j+=k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                cnt++;
            }
            //第一列，无状态转移方程，直接赋值（亦或结果为自身），从亦或结果0枚举到1024
            if(i == 0) {
                for(int xor = 0; xor < max; xor++) {
                    f[0][xor] = Math.min(f[0][xor], cnt - map.getOrDefault(xor, 0));
                    g[0] = Math.min(g[0], f[0][xor]);
                }
            } else {
                for(int xor = 0; xor < max; xor++) {
                    //新的一列最大修改次数等于前一列的最小修改次数加新一列的长度
                    f[i][xor] = g[i-1] + cnt;
                    //如果有重复元素使得亦或结果为xor，则减去对应次数，即map.get(cur)
                    for(int cur : map.keySet()) {
                        f[i][xor] = Math.min(f[i][xor], f[i -1][xor ^ cur] + cnt - map.get(cur));
                    }
                    g[i] = Math.min(g[i], f[i][xor]);
                }
            }
        }
        return f[k-1][0];
    }

    public static void main(String[] args) {
        System.out.println(0x3f3f3f3f);
    }
}
