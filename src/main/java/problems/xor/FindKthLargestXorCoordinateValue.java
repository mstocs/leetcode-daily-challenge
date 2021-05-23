package problems.xor;

import java.util.PriorityQueue;

/**
 * 2021年05月19日23:27:25
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 *
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 *
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindKthLargestXorCoordinateValue {

    public static int kthLargestValue(int[][] matrix, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int col = matrix.length;
        int row = matrix[0].length;

        int[][] rec = new int[col+1][row+1];
        for(int i = 1; i <= col; ++i) {
            for(int j = 1; j <= row; ++j) {
//                if(j>0) {
//                    coordinateValue[i+1][j] = coordinateValue[i][j] ^ coordinateValue[i+1][j-1] ^ matrix[i+1][j];
//                } else {
//                    coordinateValue[i+1][j] = coordinateValue[i][j]  ^ matrix[i+1][j];
//                }
                //核心方程，需要考虑rec[i-1][j-1]被使用了两次的情况，所以需要再异或一次
                rec[i][j] = rec[i-1][j] ^ rec[i][j-1] ^ rec[i-1][j-1] ^ matrix[i-1][j-1];

                priorityQueue.add(rec[i][j]);
            }
        }
        Integer result = null;
        for(int i = 0; i < k; i ++) {

            result = priorityQueue.poll();

        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {5,2};
        int[] b = {1,6};
        int[][] x = {{5,2},{1,6}};
        int k = 4;
        kthLargestValue(x, k);
    }
}
