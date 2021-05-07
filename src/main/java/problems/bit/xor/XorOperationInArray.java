package problems.bit.xor;

/**
 * 2021年05月07日12:58:53
 *
 * 给你两个整数，n 和 start 。
 *
 * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
 *
 * 请返回 nums 中所有元素按位异或（XOR）后得到的结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xor-operation-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class XorOperationInArray {

    /**
     * 思路：数组元素之间相差2
     * @param n
     * @param start
     * @return
     */
    public int xorOperation(int n, int start) {
        int result = start;
        for(int i = 1; i < n; i++) {
            start += 2;
            result = result ^ start;
        }
        return result;
    }
}
