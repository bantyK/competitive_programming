import java.util.*;

// 1483 https://leetcode.com/problems/kth-ancestor-of-a-tree-node/
class TreeAncestor {
    int[][] up;
    Map<Integer, List<Integer>> tree;

    public TreeAncestor(int n, int[] parent) {
        up = new int[n][20];
        tree = new HashMap<>();

        for (int i = 1; i < n; i++) {
            // tree.putIfAbsent(i, new ArrrayList<>());
            // tree.get(i).add(parent[i]);

            tree.putIfAbsent(parent[i], new ArrayList<>());
            tree.get(parent[i]).add(i);
        }

        binaryLift(0, -1);
    }

    private void binaryLift(int node, int parent) {
        up[node][0] = parent;

        for (int i = 1; i < 20; i++) {
            int prev_parent = up[node][i - 1];
            if (prev_parent != -1) up[node][i] = up[prev_parent][i - 1];
            else up[node][i] = -1;
        }

        for (int child : tree.getOrDefault(node, new ArrayList<>())) {
            binaryLift(child, node);
        }
    }

    public int getKthAncestor(int node, int k) {
        if (node == -1 || k == 0) {
            return node;
        }

        for (int i = 19; i >= 0; i--) {
            if (k >= (1 << i)) {
                return getKthAncestor(up[node][i], k - (1 << i));
            }
        }
        return -1; // should not execute
    }
}