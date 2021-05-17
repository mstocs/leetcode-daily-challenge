package problems.binary.tree;

import common.TreeNode;

/**
 * 2021年05月17日23:59:19
 *
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 *
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 *
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 *
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cousins-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CousinsInBinaryTree {
    int x;
    int y;
    int xDepth;
    int yDepth;
    TreeNode yParent;
    TreeNode xParent;
    boolean xFound;
    boolean yFound;

    //深度优先搜索
    public boolean isCousins(TreeNode root, int x, int y) {
        this.x=x;
        this.y=y;
        dfs(root, 0, null);
        return xParent !=  yParent && xDepth == yDepth;
    }

    private void dfs(TreeNode root, int depth, TreeNode parent) {
        if(root == null) {
            return;
        }

        if(x == root.val) {
            xDepth = depth;
            xFound = true;
            xParent = parent;
        } else if (y == root.val) {
            yDepth = depth;
            yFound = true;
            yParent = parent;
        }

        if(xFound && yFound) {
            return;
        }

        dfs(root.left, depth + 1, root);
        dfs(root.right, depth+1, root);
    }


}
