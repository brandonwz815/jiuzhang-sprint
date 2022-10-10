package ch12_dfs.lc1360_symmeric_tree;

import lintcode.TreeNode;

/**
 * https://www.jiuzhang.com/solutions/symmetric-tree
 * Recursive DFS
 * 
 */
public class SolutionDfsRecursive {

    public static void main(String[] args) {
        SolutionDfsRecursive solution = new SolutionDfsRecursive();

        TreeNode<Integer> root = TreeNode.arrayToTree(new Integer[] { 1, 2, 2, 3, 4, 4, 3 }, 0);
        System.out.println(solution.isSymmetric(root));

        TreeNode<Integer> root2 = TreeNode.arrayToTree(new Integer[] { 1, 2, 2, null, 3, null, 3 }, 0);
        System.out.println(solution.isSymmetric(root2));
    }

    public boolean isSymmetric(TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode<Integer> leftNode, TreeNode<Integer> rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }
        if (leftNode == null || rightNode == null) {
            return false;
        }
        if (leftNode.val != rightNode.val) {
            return false;
        }
        boolean left = isSymmetric(leftNode.left, rightNode.right);
        boolean right = isSymmetric(leftNode.right, rightNode.left);
        return left && right;
    }

}
