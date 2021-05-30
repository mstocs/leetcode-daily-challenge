package problems.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 2021年05月30日20:56:36
 * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 *
 * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 *
 * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOfSubmatricesThatSumToTarget {
    //思路，对行使用双指针，累加每一行对应列的所有元素，降级成为子数组串中目标和的问题
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int res = 0;
        //对行数使用双指针遍历
        for(int i = 0; i < row; i++) {
            int[] sum = new int[col];
            for(int j = i; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    //新的每一行j，累加这一行的对应位置的只
                    sum[k] += matrix[j][k];
                }
                res += subArraySum(sum, target);
            }
        }
        return res;
    }

    //前缀和加哈希表，如当前前缀和减去目标值存在于前缀和map中，说明存在一个或多个子数组满足条件
    private int subArraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,1);
        int count = 0;
        int pre = 0;
        for(int x : nums) {
            pre += x;
            if(map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
