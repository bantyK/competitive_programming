import java.util.*;

//1135 https://leetcode.com/problems/connecting-cities-with-minimum-cost/
public class ConnectCitiesInMinCost {
    public static void main(String[] args) {
        ConnectCitiesInMinCost obj = new ConnectCitiesInMinCost();
        System.out.println(obj.minimumCost(3, new int[][] { { 1, 2, 5 }, { 1, 3, 6 }, { 2, 3, 1 } }));
        System.out.println(obj.minimumCost(4, new int[][] { { 1, 2, 3 }, { 3, 4, 4 } }));
    }

    public int minimumCost(int N, int[][] connections) {
        // we have to find a MST.
        // If it is not possible to find MST, then return -1

        // Sort the array based on cost
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        DisjoinSet uf = new DisjoinSet(N);

        for (int[] connection : connections) {
            uf.union(connection[0] - 1, connection[1] - 1, connection[2]);
        }

        return uf.numConnections == 1 ? uf.totalCost : -1;
    }

    class DisjoinSet {
        public int numConnections; // initially this will be equal to number of nodes, but at the end of all
        // the unions, this should reduce to 1
        int[] parents;
        int[] ranks;
        public int totalCost;

        public DisjoinSet(int n) {
            numConnections = n;
            parents = new int[n];
            ranks = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int node) {
            if (parents[node] == node) {
                return node;
            }
            return parents[node] = find(parents[node]);
        }

        public void union(int x, int y, int cost) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX == parentY)
                return;

            if (ranks[parentX] < ranks[parentY]) {
                parents[parentX] = parentY;
                ranks[parentY]++;
            } else {
                parents[parentY] = parentX;
                ranks[parentX]++;
            }
            numConnections--;
            totalCost += cost;
        }

    }
}