package ch27.lc126_max_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import lintcode.TreeNode;

/**
 * Monotonic stack
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
public class MaxTreeMS {

    public static void main(String[] args) {
        MaxTreeMS maxTree = new MaxTreeMS();
        TreeNode root = maxTree.maxTree(new int[] { 4, 6, 1, 7, 3, 5, 2 });
        System.out.println(root);
    }

    private TreeNode maxTree(int[] A) {
        int n = A.length;
        Stack<Integer> descStack = new Stack<>(); // indices
        List<TreeNode> nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            TreeNode treeNode = new TreeNode(A[i]);
            nodes.add(treeNode);
            while (!descStack.isEmpty() && A[i] > A[descStack.peek()]) {
                treeNode.left = nodes.get(descStack.pop());
            }
            if (!descStack.isEmpty() && A[i] < A[descStack.peek()]) {
                nodes.get(descStack.peek()).right = treeNode;
            }
            descStack.push(i);
        }

        int indexOfRoot = 0;
        while (!descStack.isEmpty()) {
            indexOfRoot = descStack.pop();
        }

        return nodes.get(indexOfRoot);
    }
}

