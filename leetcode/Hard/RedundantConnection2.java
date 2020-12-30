import input.TwoDArrayReader;

import java.util.Arrays;

// 685 https://leetcode.com/problems/redundant-connection-ii/
public class RedundantConnection2 {

    public static void main(String[] args) {
        int[][] edges = new TwoDArrayReader().get2DArray();
        int[] redundantDirectedConnection = new RedundantConnection2().findRedundantDirectedConnection(edges);
        System.out.println(Arrays.toString(redundantDirectedConnection));
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;

        int[] candidate1 = new int[]{-1, -1};
        int[] candidate2 = new int[]{-1, -1};

        // Find a node which has 2 parents. Because that is the node which must have the redundant
        // edge. We are only checking for 2 parents, because there is only 1 redundant connection
        int[] parents = new int[n + 1];

        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];

            if (parents[dest] == 0) {
                parents[dest] = src;
            } else {
                // a parent for this node has already been seen, so this is the second parent for the dest node
                // set the current edge as the second candidate. We are setting this edge as candidate2,
                // because we need to return the edge which occurs later in the input. This is a way to
                // mark the later appearing edge
                candidate2 = new int[]{src, dest};

                // the previous parent becomes candidate1
                candidate1 = new int[]{parents[dest], dest};

                // set the current edge(this appears later in the input list)
                // we are basically setting the candidate2 as invalid
                edge[1] = 0;
            }
        }

        // perform a union-find now
        // if we can successfully perform a union operation with all nodes, then candidate2 is the
        // redundant node, simply return it
        for (int i = 0; i <= n; i++) {
            parents[i] = i; // re-using the same parent array for union find
        }

        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];

            if (dest == 0) {
                // we marked this invalid, wont consider this
                continue;
            }

            int parentSrc = find(parents, src);
            int parentDest = find(parents, dest);

            if (parentSrc == dest) {
                // there is a cycle
                if (candidate1[0] == -1) {
                    // this condition will become true for cases which does not have 2 parent nodes.
                    // in that case, we  just need to check for those edges whichh are causing a cycle
                    // as soon as we find a cycle, that is a redundant edge.
                    // since we are moving along the edges from left to right and there is only one redundant edge, the current
                    // edge is the redundant edge. Directly return it
                    return edge;
                } else {
                    // candidate1 exist,
                    // If cycle exist and removing candidate2 is still causing the cycle, the candidate2 is not the redundant edge, candidate1 itself
                    // is the redundant edge
                    return candidate1;
                }
            }

            // union step
            parents[parentDest] = parentSrc;
        }

        // if there is no cycle, then candidate2 is the right choice
        return candidate2;
    }

    private int find(int[] parents, int id) {
        if (parents[id] == id) {
            return id;
        }
        return parents[id] = find(parents, parents[id]);
    }
}
