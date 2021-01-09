import java.util.*;

//1168 https://leetcode.com/problems/optimize-water-distribution-in-a-village/
public class OptimiseWaterDistribution {
    public static void main(String[] args) {
        OptimiseWaterDistribution obj = new OptimiseWaterDistribution();
    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        // this is the new size of the graph which will both the actual edges and
        // the virtual edges between the nodes and the well
        int newSize = wells.length + pipes.length;

        int[][] graph = new int[newSize][3];

        int i;
        for (i = 0; i < wells.length; i++) {
            graph[i] = new int[] { 0 i + 1, wells[i] };
        }

        for (int j = 0; j < pipes.length; j++) {
            graph[i++] = pipes[i];
        }

        // Sort based on weight. Kruskal algo's step
        Arrays.sort(graph, (arr1, arr2) -> arr1[2] - arr2[2]);

        UnionFind uf = new UnionFind(n + 1);

        for (int[] edge : graph) {
            uf.union(edge[0], edge[1], edge[2]);
        }

        return uf.res;
    }

    class UnionFind {
        int size;
        int[] rank;
        int[] parents;
        int numComponents;
        public int res;

        public UnionFind(int n) {
            size = n;
            rank = new int[n];
            parents = new int[n];
            numComponents = n;
            res = 0;

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int p) {
            if (parents[p] == p) {
                return p;
            }

            return parents[p] = find(parents[p]);
        }

        public void union(int x, int y, int cost) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX == parentY)
                return;
            res += cost;

            if (rank[parentX] < rank[parentY]) {
                parents[parentX] = parentY;
                rank[parentY] += rank[parentX];
            } else {
                parents[parentY] = parentX;
                rank[parentX] += rank[parentY];
            }
            numComponents--;
        }

        public int getNumComponents() {
            return numComponents;
        }
    }
}