import input.TreeNode;
import java.util.*;

// 951 https://leetcode.com/problems/flip-equivalent-binary-trees/
public class FlipEquivalentTree {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;

        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();

        q1.offer(root1);
        q2.offer(root2);

        while(!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode r1 = q1.poll();
            TreeNode r2 = q2.poll();

            if(equal(r1.left, r2.left) && equal(r1.right, r2.right)) {
                add(q1, r1.left);
                add(q1, r1.right);
                add(q2, r2.left);
                add(q2, r2.right);
            } else if(equal(r1.left, r2.right) && equal(r1.right, r2.left)) {
                add(q1, r1.left);
                add(q2, r2.right);
                add(q1, r1.right);
                add(q2, r2.left);
            } else {
                return false;
            }
        }

        return q1.size() == q2.size();

    }

    private boolean equal(TreeNode node1, TreeNode node2) {
        if(node1 == null) return node2 == null;
        if(node2 == null) return false;

        return node1.val == node2.val;
    }

    private void add(Queue<TreeNode> q, TreeNode n) {
        if(n != null) q.offer(n);
    }

}
