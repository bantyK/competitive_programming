import java.util.*;

// 684 https://leetcode.com/problems/redundant-connection/
public class RedundantEdge {

    public int[] findRedundantConnection(int[][] edges) {
        DisjointSet disjointSet = new DisjointSet(edges.length);

        for (int[] edge : edges) {
            if (!disjointSet.union(edge[0] - 1, edge[1] - 1)) return edge;
        }

        throw new IllegalArgumentException();
    }

    static class DisjointSet {
        private int[] parent;
        private int[] rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
        }

        private int find(int x) {
            if (parent[x] == 0) {
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return false;

            // make root of smaller rank point to larger rank
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootY] > rank[rootX]) {
                parent[rootX] = rootY;
            } else {
                parent[rootX] = rootY;
                rank[rootY] += 1;
            }
            return true;
        }

    }
}
