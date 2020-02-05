//261 https://leetcode.com/problems/graph-valid-tree/
public class ValidTree {
    public boolean validTree(int n, int[][] edges) {
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            boolean union = union(parent, edge[0], edge[1]);
            if (!union) return false;
        }

        int parent0 = parent[0];
        for (int i = 1; i < n; i++) {
            if (parent[i] != parent0) {
                return false;
            }
        }

        return true;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        else return parent[i] = find(parent, parent[i]);
    }

    private boolean union(int[] parent, int i, int j) {
        int parentI = find(parent, i);
        int parentJ = find(parent, j);
        if (parentI == parentJ) return false;
        else {
            parent[parentJ] = parentI;
            return true;
        }
    }
}
