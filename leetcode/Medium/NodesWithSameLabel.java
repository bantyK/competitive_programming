import java.util.*;

// 1519 https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/
public class NodesWithSameLabel {

    int[] ans;

    public static void main(String[] args) {
        NodesWithSameLabel obj = new NodesWithSameLabel();
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        System.out.println(Arrays.toString(obj.countSubTrees(7, edges, "abaedcd")));

        int[][] edges1 = new int[][]{{0, 1}, {1, 2}, {0, 3}};
        System.out.println(Arrays.toString(obj.countSubTrees(4, edges1, "bbbb")));

    }

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        ans = new int[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        visited[0] = true;

        dfs(graph, 0, visited, labels);

        return ans;
    }

    private int[] dfs(Map<Integer, List<Integer>> graph, int curr, boolean[] visited, String labels) {
        int[] count = new int[26];
        char currentChar = labels.charAt(curr);

        count[currentChar - 'a']++;

        visited[curr] = true;

        for (int child : graph.get(curr)) {
            if (visited[child]) continue;
            int[] childCounts = dfs(graph, child, visited, labels);

            for (int i = 0; i < 26; i++) {
                count[i] += childCounts[i];
            }
        }
        ans[curr] = count[currentChar - 'a'];
        return count;
    }

}