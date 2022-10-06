package ch27.lc126_max_tree;

import lintcode.TreeNode;

/**
 * Brute force DFS
 * Time complexity: O(n^2)
 * Space complexity: O(n)
 */
public class MaxTree {

    public static void main(String[] args) {
        MaxTree maxTree = new MaxTree();
        TreeNode root = maxTree.maxTree(new int[] { 2, 5, 6, 0, 3, 1 });
        System.out.println(root);
    }

    public TreeNode maxTree(int[] A) {
        return dfs(A, 0, A.length - 1); // right inclusive
    }

    private TreeNode dfs(int[] a, int left, int right) {
        if (left >= right) {
            return null;
        }

        int max = getMaxPosition(a, left, right);
        TreeNode root = new TreeNode(a[max]); // ATTN use a[max] to pass the element value
        root.left = dfs(a, left, max - 1);
        root.right = dfs(a, max + 1, right);
        return root;
    }

    private int getMaxPosition(int[] a, int left, int right) {
        int maxIndex = Integer.MIN_VALUE, maxValue = Integer.MIN_VALUE;
        for (int i = left; i < right + 1; i++) { // ATTN + 1
            if (maxValue < a[i]) {
                maxValue = a[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

}
