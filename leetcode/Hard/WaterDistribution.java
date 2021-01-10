import java.util.*;

// 1168 https://leetcode.com/problems/optimize-water-distribution-in-a-village/
public class WaterDistribution {
    public static void main(String[] args) {
        WaterDistribution obj = new WaterDistribution();

        System.out.println(obj.minCostToSupplyWater(5, new int[] { 46012, 72474, 64965, 751, 33304 },
                new int[][] { { 2, 1, 6719 }, { 3, 2, 75312 }, { 5, 3, 44918 } }));

        System.out
                .println(obj.minCostToSupplyWater(3, new int[] { 1, 2, 2 }, new int[][] { { 1, 2, 1 }, { 2, 3, 1 } }));

    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        // we will add one additional virtual node whose distance from node[i] is
        // wells[i]
        // This node will basically help us consider the wells weight also in
        // calculating the
        // overall distribution cost
        // general nodes start from 1 so we can consider node0 as the virtual node(the
        // well)

        // total number of edges will become the normal edges given by pipes and
        // one addition edge for each vertex which joins the node from the well and the
        // cost of that
        // edge will be wells[i].

        int newSize = wells.length + pipes.length;
        int[][] graph = new int[newSize][3];

        // prepare a graph considering the normal pipes as well as the well
        int graphNodeIndex = 0;
        for (int i = 0; i < wells.length; i++) {
            graph[graphNodeIndex++] = new int[] { 0, i + 1, wells[i] }; // this is an edge from the well which is node 0
                                                                        // to the actual house which is node i
        }

        // adding the normal pipes
        for (int i = 0; i < pipes.length; i++) {
            graph[graphNodeIndex++] = pipes[i];
        }

        // Once we have the graph ready, all we have to do is find visit all the
        // nodes(houses)
        // in a minimum possible cost. In other words find the MST of the graph

        // Sort the nodes according to their costs for performing the Kruskal's MST
        // algorithm
        Arrays.sort(graph, (a, b) -> a[2] - b[2]); // a[2] is the cost

        UnionFind uf = new UnionFind(n + 1); // n given nodes + 1 well
        int cost = 0;

        for (int[] edge : graph) {
            if (uf.union(edge[0], edge[1])) {
                cost += edge[2];
            }
        }
        return cost;
    }

    class UnionFind {
        int[] parents;
        int[] rank;

        public UnionFind(int n) {
            parents = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int node) {
            if (parents[node] == node) {
                return node;
            }
            return parents[node] = find(parents[node]);
        }

        public boolean union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX == parentY)
                return false;

            if (rank[parentX] > rank[parentY]) {
                parents[parentY] = parentX;
                rank[parentX] += 1;
            } else {
                parents[parentX] = parentY;
                rank[parentY] += 1;
            }
            return true;
        }
    }
}