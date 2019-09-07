package solutions;

import models.Node;

import java.util.Arrays;

//https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
public class NArrayDepth {

    public static void main(String[] args) {
        NArrayDepth obj = new NArrayDepth();
        Node secondNode = new Node(2, Arrays.asList(
                new Node(5, null),
                new Node(6, null)));
        Node thirdNode = new Node(3, null);
        Node fourthNode = new Node(4, null);

        Node root = new Node(1, Arrays.asList(secondNode, thirdNode, fourthNode));

        int depth = obj.maxDepth(root);
        System.out.println(depth);
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        } else if (root.children == null) {
            return 1;
        } else {
            int childrenCount = root.children.size();

            int[] depths = new int[childrenCount];

            for (int i = 0; i < childrenCount; i++) {
                depths[i] = maxDepth(root.children.get(i));
            }

            return 1 + maximum(depths);
        }
    }

    private int maximum(int[] depths) {
        int max = 0;

        for (int depth : depths) {
            if (depth > max) {
                max = depth;
            }
        }
        return max;
    }
}
