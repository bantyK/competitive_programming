import java.util.*;

// 886 https://leetcode.com/problems/possible-bipartition/
public class PossibleBipartition {
    public static void main(String[] args) {
        PossibleBipartition obj = new PossibleBipartition();
//        System.out.println(obj.possibleBipartitionDFS(4, new int[][]{{1, 2}, {2, 3}, {1, 4}}));
//        System.out.println(obj.possibleBipartitionDFS(4, new int[][]{{1, 2}, {2, 3}, {1, 3}}));
//        System.out.println(obj.possibleBipartitionDFS(5, new int[][]{{1, 2}, {2, 3}, {2, 5}, {3, 4}, {1, 3}}));
        System.out.println(obj.possibleBipartitionDFS(5, new int[][]{{1, 2}, {3, 4}, {4, 5}, {3, 5}}));
    }

    public boolean possibleBipartitionDFS(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] dislike : dislikes) {
            int a = dislike[0];
            int b = dislike[1];

            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int[] color = new int[N + 1];
        Arrays.fill(color, -1);

        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (color[i] == -1) {
                color[i] = 1;
                boolean isBipartite = dfs(graph, color, visited, i, -1);
                if (!isBipartite) return false;
            }
        }

        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, int[] color, boolean[] visited, int vertex, int parent) {
        if (parent != -1 && color[vertex] != -1 && color[vertex] != (1 - color[parent])) {
            return false;
        }

        visited[vertex] = true;

        for (int neigh : graph.getOrDefault(vertex, new ArrayList<>())) {
            if (color[neigh] == -1) {
                color[neigh] = 1 - color[vertex];
            } else if (color[neigh] != 1 - color[vertex]) {
                return false;
            }
            if (!visited[neigh]) {
                boolean isBipartite = dfs(graph, color, visited, neigh, vertex);
                if (!isBipartite) return false;
            }
        }
        return true;
    }


    // BFS
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] dislike : dislikes) {
            int a = dislike[0];
            int b = dislike[1];

            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int[] color = new int[N + 1];
        Arrays.fill(color, -1);

        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (color[i] == -1) {
                boolean isBipartite = bfs(graph, color, visited, i);
                if (!isBipartite) return false;
            }
        }

        return true;
    }

    private boolean bfs(Map<Integer, List<Integer>> graph, int[] color, boolean[] visited, int vertex) {
        Queue<Integer> queue = new LinkedList<>();

        color[vertex] = 1;
        queue.add(vertex);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            visited[curr] = true;
            int currentColor = color[curr];

            for (int neigh : graph.getOrDefault(curr, new ArrayList<>())) {
                if (color[neigh] != -1 && color[neigh] != (1 - currentColor)) {
                    return false;
                }
                if (color[neigh] == -1) {
                    color[neigh] = 1 - currentColor;
                }

                if (!visited[neigh]) {
                    queue.offer(neigh);
                }
            }
        }
        return true;
    }
}
