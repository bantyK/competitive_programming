import java.util.*;

//117 https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
public class ConnectNextNode {
    public static void main(String[] args) {
        ConnectNextNode obj = new ConnectNextNode();
    }

    public Node connect(Node root) {
        if(root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int n = queue.size();
            List<Node> nodes = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                Node curr = queue.poll();

                nodes.add(curr);

                if(curr.left != null) {
                    queue.offer(curr.left);
                }

                if(curr.right != null) {
                    queue.offer(curr.right);
                }
            }

            for(int i = 0; i < nodes.size() - 1; i++) {
                nodes.get(i).next = nodes.get(i + 1);
            }
        }
        return root;
    }
}
