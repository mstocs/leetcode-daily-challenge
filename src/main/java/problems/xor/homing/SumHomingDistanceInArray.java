package problems.xor.homing;

/**
 * 2021年05月30日20:50:15
 * <p>
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * <p>
 * 给你一个整数数组 nums，请你计算并返回 nums 中任意两个数之间汉明距离的总和。
 */
public class SumHomingDistanceInArray {

    //思路，统计每一个数组中所有元素的每一个bit位出现1的个数，每一个bit位对于总汉明距离的贡献为 c * (n-c)
    public int totalHammingDistance(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < 30; ++i) {
            int c = 0;
            for (int val : nums) {
                c += (val >> i) & 1;
            }
            ans += c * (n - c);
        }
        return ans;
    }

}
