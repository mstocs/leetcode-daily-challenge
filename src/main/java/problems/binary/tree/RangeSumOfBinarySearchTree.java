package problems.binary.tree;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2021年04月27日23:26:01
 *
 * Given the root node of a binary search tree, return the sum of values of all nodes with a value in the range [low, high].
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-of-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RangeSumOfBinarySearchTree {

    //深度优先搜索
    public int deepFirstSearchSolution (TreeNode root, int lo, int hi) {
        //空节点记为0
        if(root == null) {
            return 0;
        }
        //如果当前值小于范围最小值，则需要累加的值必然在右子树（右子树总是大于当前值）
        if(root.val < lo) {
            return deepFirstSearchSolution(root.right, lo, hi);
        }
        //如果当前值大于范围最大值，则需要累加的值必然在左子树（左子树总是小于当前值）
        if(root.val > hi) {
            return  deepFirstSearchSolution(root.left, lo, hi);
        }
        //当前值加上左右子树的值
        return root.val + deepFirstSearchSolution(root.left, lo, hi) + deepFirstSearchSolution(root.right, lo, hi);
    }

    //广度优先搜索
    public int breadthFirstSearchSolution(TreeNode root, int lo, int hi) {
        //记录结果
        int sum = 0;
        //使用队列保存待遍历的节点
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //将根节点加入队列
        queue.offer(root);
        //队列不为空时一直执行
        while (!queue.isEmpty()) {
            //取出队列头的节点
            TreeNode node = queue.poll();
            //节点为空，跳出循环
            if(node == null) {
                continue;
            }
            //节点值小于范围最小值，则无需计算当前节点的左节点
            if(node.val < lo) {
                queue.offer(node.right);
            }
            //节点值大于范围最大值，则无需计算当前节点的右节点
            else if(node.val > hi) {
                queue.offer(node.left);
            }
            //非以上两种情况，累加当前节点的值到sum，并继续遍历当前节点的左右节点
            else {
                sum += node.val;
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return sum;
    }
}
