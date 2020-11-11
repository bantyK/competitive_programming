import java.util.*;

//323 https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
public class NumConnectedComponents {
    int[] parents;
    int[] ranks;

    public static void main(String[] args) {
        CountConnectedComponents obj = new CountConnectedComponents();
        System.out.println(obj.countComponentsBFS(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}));
        System.out.println(obj.countComponents(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
        System.out.println(obj.countComponentsBFS(4, new int[][]{{0, 1}, {2, 3}, {1, 2}}));


    }

    //BFS
    public int countComponentsBFS(int n, int[][] edges) {
        if (n <= 1) return n;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                bfs(graph, i, visited);
                count++;
            }
        }


        return count;
    }

    private void bfs(Map<Integer, List<Integer>> graph, int src, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            visited[curr] = true;
            for(int neigh : graph.get(curr)) {
                if(!visited[neigh]) {
                    queue.offer(neigh);
                }
            }
        }
    }

    //DFS
    public int countComponentsDFS(int n, int[][] edges) {
        if (n <= 1) return n;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited);
                count++;
            }
        }
        return count;
    }

    private void dfs(Map<Integer, List<Integer>> graph, int src, boolean[] visited) {
        visited[src] = true;
        for (int neigh : graph.get(src)) {
            if (!visited[neigh]) {
                dfs(graph, neigh, visited);
            }
        }
    }


    // Union find
    public int countComponents(int n, int[][] edges) {
        if (n == 0) return 0;

        parents = new int[n];
        ranks = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }


        Map<Integer, Integer> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int parent = find(parents[i]);
            groups.put(parent, groups.getOrDefault(parent, 0) + 1);
        }

        return groups.keySet().size();
    }

    public int find(int key) {
        if (parents[key] == key) return key;
        return parents[key] = find(parents[key]);
    }

    public void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if (parentX == parentY) return;
        if (ranks[parentX] >= ranks[parentY]) {
            parents[parentY] = parents[parentX];
            ranks[parentX]++;
        } else {
            parents[parentX] = parents[parentY];
            ranks[parentY]++;
        }
    }
}
