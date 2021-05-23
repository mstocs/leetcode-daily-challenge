package problems.xor;

/**
 * 2021年05月12日22:13:07
 *
 * 1310. 子数组异或查询
 * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
 *
 * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
 *
 * 并返回一个包含给定查询 queries 所有结果的数组。
 */
public class XorQueryOfSubArray {
    /**
     * 利用亦或的交换律和传递规律，首先计算前n个字符亦或的结果，区间亦或结果 = 第一个到左区间前一元素的亦或结果 ^ 第一个到右区间的亦或结果
     * @param arr
     * @param queries
     * @return
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        //保存前n个元素的亦或结果，添加第一位为0，所以数组长度加一
        int[] xor = new int[arr.length+1];
        for(int i = 0; i < arr.length; i++) {
            xor[i+1] = xor[i] ^ arr[i];
        }

        //结果数组
        int[] result = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
            //左值为到左值前一个元素，刚好对应加一后的下标
            int l = queries[i][0];
            //右值为到右值，需要下标加一
            int r = queries[i][1] + 1;
            //亦或结果即为区间结果
            result[i] = xor[l] ^ xor[r];
        }
        return result;
    }
}
