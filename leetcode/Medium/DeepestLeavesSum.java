import java.util.*;

//1302 https://leetcode.com/problems/deepest-leaves-sum/
public class DeepestLeavesSum {
    public static void main(String[] args) {
        DeepestLeavesSum obj = new DeepestLeavesSum();
    }

    public int deepestLeavesSum(Solution.TreeNode root) {
        if (root == null) return 0;

        Queue<Solution.TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> temp;
        int sum = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            temp = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                Solution.TreeNode curr = queue.poll();

                temp.add(curr.val);

                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }

            if (queue.isEmpty())
                sum = temp.stream().mapToInt(Integer::intValue).sum();
        }

        return sum;
    }
}
