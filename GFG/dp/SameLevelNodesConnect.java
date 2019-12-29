import java.util.*;

// https://practice.geeksforgeeks.org/problems/connect-nodes-at-same-level/1
public class SameLevelNodesConnect {

    static void connect(Node root) {
        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Node> levelNodes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Node curr = queue.poll();
                levelNodes.add(curr);

                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }

            for (int i = 0; i < levelNodes.size() - 1; i++) {
                levelNodes.get(i).nextRight = levelNodes.get(i + 1);
            }
        }
    }

    static class Node {
        int data;
        Node left, right, nextRight;

        Node(int x) {
            this.data = x;
            left = right = nextRight = null;
        }
    }
}
