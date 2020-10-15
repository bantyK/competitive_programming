import input.TreeNode;
import java.util.*;

// 951 https://leetcode.com/problems/flip-equivalent-binary-trees/
public class FlipEquivalentTree {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2 == null;
        if (root2 == null) return false;

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue1.offer(root1);
        queue2.offer(root2);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            
            
            if(node1.val != node2.val) return false;

            if (equal(node1.left, node2.left) && equal(node1.right, node2.right)) {
                add(queue1, node1.left);
                add(queue1, node1.right);
                add(queue2, node2.left);
                add(queue2, node2.right);
            } else if (equal(node1.left, node2.right) && equal(node1.right, node2.left)) {
                add(queue1, node1.left);
                add(queue1, node1.right);
                add(queue2, node2.right);
                add(queue2, node2.left);
            } else {
                return false;
            }
        }

        return queue1.size() == 0 && queue2.size() == 0;

    }

    private void add(Queue<TreeNode> queue, TreeNode node) {
        if (node != null) queue.offer(node);
    }

    private boolean equal(TreeNode node1, TreeNode node2) {
        if (node1 == null) return node2 == null;
        if (node2 == null) return false;

        return node1.val == node2.val;
    }

}
