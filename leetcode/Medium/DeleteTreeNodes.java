import java.util.*;

//1273 https://leetcode.com/problems/delete-tree-nodes/
public class DeleteTreeNodes {

    public static void main(String[] args) {
        DeleteTreeNodes obj = new DeleteTreeNodes();
        int count;
        count = obj.deleteTreeNodes(4, new int[]{-1, 0, 0, 2}, new int[]{2, 0, 0, -2});
        System.out.println(count);


    }


    // Second approach
    public int deleteTreeNodes(int nodes, int[] parent, int[] values) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nodes; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < parent.length; i++) {
            if (parent[i] >= 0) {
                map.get(parent[i]).add(i);
            }
        }

        int[] res = dfs(map, 0, values);
        return res[1];
    }


    private int[] dfs(Map<Integer, List<Integer>> graph, int currentVertex, int[] values) {
        int total = values[currentVertex];

        int countChildNodes = 1;

        for (int child : graph.get(currentVertex)) {
            int[] temp = dfs(graph, child, values);
            total += temp[0];
            countChildNodes += temp[1];
        }
        if (total == 0) {
            // do not count its child nodes if the sum is 0
            countChildNodes = 0;
        }

        return new int[]{total, countChildNodes};
    }


    public int deleteTreeNodes2(int nodes, int[] parent, int[] values) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nodes; i++) {
            map.put(i, new ArrayList<>());
        }

        boolean[] removed = new boolean[nodes];

        for (int i = 0; i < parent.length; i++) {
            if (parent[i] >= 0) {
                map.get(parent[i]).add(i);
            }
        }

        dfs2(map, 0, values, removed);

        int count = 0;
        for (int i = 0; i < nodes; i++) {
            if (!removed[i]) count++;
        }

        return count;
    }

    // {sum, count}
    private int dfs2(Map<Integer, List<Integer>> graph, int currentVertex, int[] values, boolean[] removed) {
        int sum = values[currentVertex];

        for (int child : graph.get(currentVertex)) {
            sum += dfs2(graph, child, values, removed);
        }

        if (sum == 0) {
            removeNodes(graph, currentVertex, removed);
        }

        return sum;
    }

    private void removeNodes(Map<Integer, List<Integer>> graph, int currentVertex, boolean[] removed) {
        removed[currentVertex] = true;
        for (int child : graph.get(currentVertex)) {
            removeNodes(graph, child, removed);
        }
    }

}
