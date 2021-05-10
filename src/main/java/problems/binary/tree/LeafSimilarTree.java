package problems.binary.tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 2021年05月10日13:09:40
 *
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 *
 *
 *
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 *
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 *
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/leaf-similar-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeafSimilarTree {
    List<Integer> temp;

    /**
     * 思路，广度优先搜索，获得两个root的叶子节点，然后比较
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaf1 = getLeaf(root1);
        List<Integer> leaf2 = getLeaf(root2);
        if(leaf1.size()==leaf2.size()) {
            for(int i = 0; i < leaf2.size(); i++) {
                if(leaf2.get(i) != leaf1.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private List<Integer> getLeaf(TreeNode root) {
        temp = new ArrayList();
        inOrder(root);
        return temp;
    }

    private void inOrder(TreeNode root) {
        //空节点返回
        if(root == null) {
            return;
        }
        //叶子节点，记录节点的值
        if(root.left==null && root.right ==null) {
            temp.add(root.val);
        }
        //搜索左子树
        inOrder(root.left);
        //搜索右子树
        inOrder(root.right);
    }
}
