package lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * assembled from multiple lessions
 * modified with generics
 * 
 */
public class TreeNode<T> {
    public T val;
    public TreeNode<T> left, right;

    public TreeNode(T val) {
        this.val = val;
        this.left = this.right = null;
    }

    public List<T> inorderTraversal(TreeNode<T> root) {
        if (root == null) {
            return null;
        }

        List<T> valueList = new ArrayList<>();
        
        Stack<TreeNode<T>> stack = new Stack<>();
        while(root!=null) {
            stack.push(root);
            root = root.left;
        }

        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.peek();
            valueList.add(node.val);

            //
        }


        return valueList;
    }

    public static <T> TreeNode<T> arrayToTree(T[] array, int rootIndex) {
        TreeNode<T> root = null;
        if (array != null
                && array.length != 0
                && rootIndex >= 0
                && rootIndex < array.length) {
            T val = array[rootIndex];
            if (val != null) {
                root = new TreeNode<>(val);
                root.left = TreeNode.arrayToTree(array, rootIndex * 2 + 1);
                root.right = TreeNode.arrayToTree(array, rootIndex * 2 + 2);
            }
        }
        return root;
    }
    
}
