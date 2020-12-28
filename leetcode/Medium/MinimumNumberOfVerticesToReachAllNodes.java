import java.util.ArrayList;
import java.util.List;

// 1557 https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/
public class MinimumNumberOfVerticesToReachAllNodes {
    private static class UnionFind {
        int[] parents;

        public UnionFind(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int val) {
            if (parents[val] == val) {
                return val;
            }
            return parents[val] = find(parents[val]);
        }

        public void union(int x, int y) {
            int parentStart = find(x);
            int parentEnd = find(y);

            // make the start vertex parent of end vertex
            parents[parentEnd] = parentStart;
        }
    }

    // Union find
    public List<Integer> findSmallestSetOfVertices2(int n, List<List<Integer>> edges) {
        boolean[] visited = new boolean[n];
        UnionFind uf = new UnionFind(n);

        for (List<Integer> edge : edges) {
            int endVertex = edge.get(1);
            int sourceVertex = edge.get(0);
            if (!visited[endVertex]) {
                uf.union(sourceVertex, endVertex);
                visited[endVertex] = true;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (uf.parents[i] == i) {
                res.add(i);
            }

        }
        return res;
    }

    // vertices with in-degree 0 are the nodes which cannot be accessed from any other nodes,
    // so all those nodes are the answer
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] nodes = new boolean[n];

        for (List<Integer> edge : edges) {
            nodes[edge.get(1)] = true;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!nodes[i]) {
                res.add(i);
            }
        }
        return res;
    }

}
