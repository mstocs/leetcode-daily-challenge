package problems.bit.xor;

/**
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 *
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 *
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：encoded = [3,1]
 * 输出：[1,2,3]
 * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 * 示例 2：
 *
 * 输入：encoded = [6,5,4,6]
 * 输出：[2,4,1,5,3]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-xored-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DecodeXoredPermutation {
    /**
     * 思路，首先求出第一个元素，然后解法
     * @see DecodeXoredArray
     */

    public int[] decode(int[] encoded) {

        //求出前n个正整数的亦或结果
        int allXor = 0;
        for(int i = 1; i < encoded.length ; i++) {
            allXor = allXor ^ i;
        }
        //求出除了原数组首元素之外的亦或结果
        int restXor = encoded[1];
        for(int i = 3; i < encoded.length; i+=2) {
            restXor = restXor ^ encoded[i];
        }
        //首元素等于上述两值得亦或结果
        int first = allXor ^ restXor;
        int[] result = new int[encoded.length + 1];
        result[0] = first;
        for(int i = 1; i < result.length; i++) {
            result[i] = result[i-1] ^ encoded[i-1];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] test = new int [2];
        test[0] = 3;
        test[1] = 1;
        DecodeXoredPermutation solution = new DecodeXoredPermutation();
        solution.decode(test);
    }
}
