import input.Node;

import java.util.HashSet;
import java.util.*;

//1650 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
public class LowestCommonAncestor3 {

    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Integer> parentNodesForP = new HashSet<>();

        Node node = p;
        while (node != null) {
            parentNodesForP.add(node.val);
            node = node.parent;
        }

        node = q;
        while (node != null) {
            if (parentNodesForP.contains(node.val)) {
                return node;
            }
            node = node.parent;
        }
        return null; // will not execute
    }

    /// No space required
    public Node lowestCommonAncestorConstantSpace(Node p, Node q) {
        int pHeight = height(p);
        int qHeight = height(q);

        while (pHeight > qHeight) {
            --pHeight;
            p = p.parent;
        }

        while (qHeight > pHeight) {
            --qHeight;
            q = q.parent;
        }

        while (p != q) {
            p = p.parent;
            q = q.parent;
        }

        return p;
    }

    private int height(Node node) {
        int height = 0;
        while (node != null) {
            node = node.parent;
            height++;
        }
        return height;
    }
}