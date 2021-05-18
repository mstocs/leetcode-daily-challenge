package problems.bit.xor;

/**
 * 2021年05月18日23:50:25
 *
 * 给你一个整数数组 arr 。
 *
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 *
 * a 和 b 定义如下：
 *
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 *
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXor {
    //思路：利用异或计算性质
    class Solution {
        public int countTriplets(int[] arr) {
            int n = arr.length;
            int[] s = new int[n+1];
            for(int i = 0; i < n; ++i) {
                s[i + 1] = s[i] ^ arr[i];
            }
            int ans = 0;
            for(int i = 0; i < n; ++i) {

                for(int k = i + 1; k < n; ++k) {
                    //核心推导式
                    if(s[i] == s[k+1]) {
                        ans += k-i;
                    }
                }

            }
            return ans;
        }
    }
}
