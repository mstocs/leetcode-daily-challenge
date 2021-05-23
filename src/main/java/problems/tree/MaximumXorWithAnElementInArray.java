package problems.tree;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2021年05月24日00:57:31
 *
 * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
 *
 * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
 *
 * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximumXorWithAnElementInArray {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        //数组排序，从小到大
        Arrays.sort(nums);
        int numQ = queries.length;
        //保存查询id，按顺序返回
        int[][] newQueries = new int[numQ][3];
        for(int i = 0; i < numQ; i++) {
            newQueries[i][0] = queries[i][0];
            newQueries[i][1] = queries[i][1];
            newQueries[i][2] = i;
        }
        //按照mi的顺序排序
        Arrays.sort(newQueries, new Comparator<int[]>() {
            @Override
            public int compare(int[] query1, int[] query2) {
                return query1[1] - query2[1];
            }
        });

        //保存结果
        int[] ans = new int[numQ];
        Trie trie = new Trie();
        int idx = 0;
        int n = nums.length;
        //遍历查询数组，计算结果
        for(int[] query : newQueries) {
            int x = query[0];
            int m = query[1];
            int qid = query[2];
            //将小于m的元素添加到前缀树
            while(idx < n && nums[idx] <= m) {
                trie.insert(nums[idx]);
                idx++;
            }
            if(idx == 0) {
                ans[qid] = -1;
            } else {
                //从前缀树获取可以异或得到的最大值
                ans[qid] = trie.getMaxXor(x);
            }
        }
        return ans;
    }
}

class Trie {
    //这里可以根据最大值10的9次方来定义长度，最长不超过2^30次方，所以长度30
    static final int L = 30;

    //子树代表低一个bit位是否有0或者1的情况存在
    Trie[] children = new Trie[2];

    public void insert(int val) {
        Trie node = this;
        //从L-1开始，即从最高位开始逐步取出值
        for(int i = L - 1; i >= 0; --i) {
            //当前bit位的只
            int bit = (val >> i) & 1;
            //递归的添加
            if(node.children[bit] == null) {
                node.children[bit] = new Trie();
            }
            node = node.children[bit];
        }
    }

    public int getMaxXor(int val) {
        int ans = 0;
        Trie node = this;
        for(int i = L - 1; i >=0; --i) {
            int bit = (val>>i) & 1;
            //如果可以取到1
            if(node.children[bit^1]!=null) {
                //结果的该位置填充1
                ans |= 1 << i;
                //bit设置为该位的选择
                bit ^= 1;
            }
            node = node.children[bit];
        }
        return ans;
    }
}
