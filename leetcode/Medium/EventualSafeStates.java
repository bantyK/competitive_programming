import java.util.*;

//802 https://leetcode.com/problems/find-eventual-safe-states/
public class EventualSafeStates {
    public static void main(String[] args) {
        EventualSafeStates obj = new EventualSafeStates();
        //[[1,2],[2,3],[5],[0],[5],[],[]]
        System.out.println(obj.eventualSafeNodes(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}}));
        System.out.println(obj.eventualSafeNodes(new int[][]{{1, 2}, {2}, {}}));
    }

    /**
     * 0: Node is not processed yet
     * 1: Node is processed before and results in a cycle
     * 2: Node is processed before and does results in a cycle
     */

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (dfs(graph, i, colors)) {
                res.add(i);
            }
        }

        return res;
    }

    private boolean dfs(int[][] graph, int vertex, int[] colors) {
        if (colors[vertex] > 0) {
            // if this node is already marked that it result into a cycle or not, then no need to check more, directly return accordingly
            return colors[vertex] == 2;
        }

        colors[vertex] = 1;

        for (int neigh : graph[vertex]) {
            if (colors[neigh] == 2) {
                // this node does not result into a cycle, so no need to check this node
                continue; // do not return, we need to check all paths
            }
            if (colors[neigh] == 1) {
                // this node results into a cycle, no need to proceed ahead,
                return false;
            }

            boolean isCyclic = dfs(graph, neigh, colors);
            if (!isCyclic) {
                return false;
            }
        }

        colors[vertex] = 2; // mark this node to indicate that this node never result into a cycle
        return true;
    }

}
