package solutions.medium;

import models.TreeNode;
import solutions.util.ArrayUtils;
import solutions.util.BinaryTreeUtils;

import java.util.*;

//449 https://leetcode.com/problems/serialize-and-deserialize-bst/
// 297 https://leetcode.com/problems/serialize-and-deserialize-binary-tree
public class SerialiseDeserialiseBinaryTree {
    public static void main(String[] args) {
        SerialiseDeserialiseBinaryTree obj = new SerialiseDeserialiseBinaryTree();
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(10);
        root.right = new TreeNode(30);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(15);

        root.right.left = new TreeNode(25);
        root.right.right = new TreeNode(40);

        BinaryTreeUtils.inorderTraversal(root);

        String serialised = obj.serialize(root);
        System.out.println();
        System.out.println("Serialised: " + serialised);
        TreeNode root1 = obj.deserialize(serialised);
        BinaryTreeUtils.inorderTraversal(root1);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "X";

        String leftSerialised = serialize(root.left);
        String rightSerialised = serialize(root.right);

        return root.val + "," + leftSerialised + "," + rightSerialised;
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals("X")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);

        return root;
    }
}
