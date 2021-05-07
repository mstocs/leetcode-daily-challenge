package problems.bit.xor;

/**
 * 2021年05月07日13:00:41
 * 未知 整数数组 arr 由 n 个非负整数组成。
 *
 * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
 *
 * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
 *
 * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-xored-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DecodeXoredArray {
    /**
     * 思路： 一个数亦或两次任一相同的数，还是得到当前数
     *      a xor b xor b = a
     *      假设 a xor b = c
     *      c xor a = a xor b xor a = b xor a xor a = b
     *
     *  所以，原数组的第二个元素等于 原数组第一个元素 xor 加密数组的第一个元素
     *       原数组的第三个元素等于 原数组第二个元素 xor 加密数组的第二个元素
     *       依次计算，即可计算出原数组
     */

    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] result = new int[n+1];
        result[0] = first;
        for(int i = 0; i < encoded.length; i++) {
            result[i+1] = encoded[i] ^ result[i];
        }
        return result;
    }
}
