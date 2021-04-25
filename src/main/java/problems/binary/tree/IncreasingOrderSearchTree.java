package problems.binary.tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 2021年04月25日12:33:05
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-order-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IncreasingOrderSearchTree {

    /**
     * 中序遍历读出结果，然后再构建目标树
     * @param root
     * @return
     */
    public TreeNode solve1(TreeNode root) {
        //用来保存中序遍历结果
        List<Integer> res = new ArrayList<Integer>();
        //中序遍历，添加中序遍历结果到list；
        inorder(root, res);

        //哨兵节点
        TreeNode dummyNode = new TreeNode(-1);
        //遍历节点
        TreeNode currNode = dummyNode;
        for(int val : res) {
            //为当前节点添加右节点
            currNode.right = new TreeNode(val);
            //将添加后的右节点设为当前节点
            currNode = currNode.right;
        }
        //返回哨兵节点的右节点
        return dummyNode.right;
    }

    /**
     * 二叉树中序遍历
     * @param treeNode
     * @param res
     */
    private void inorder(TreeNode node, List<Integer> res) {
        if(node == null) {
            return;
        }
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }

    private TreeNode resNode;

    /**
     * 中序遍历的时候改变指针
     * @param root
     * @return
     */
    public TreeNode solve2(TreeNode root) {
        //哨兵节点
        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;
        inorderAndSaveNode(root);
        return dummyNode.right;
    }

    /**
     * 中序遍历同时保存遍历结果
     * @param node
     */
    private void inorderAndSaveNode(TreeNode node) {
        if(node == null) {
            return;
        }

        inorderAndSaveNode(node.left);

        resNode.right = node;
        node.left = null;
        resNode = node;

        inorderAndSaveNode(node.right);
    }
}
