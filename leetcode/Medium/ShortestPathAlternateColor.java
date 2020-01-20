import java.util.*;

// 1129 https://leetcode.com/problems/shortest-path-with-alternating-colors/
public class ShortestPathAlternateColor {
    public static void main(String[] args) {
        ShortestPathAlternateColor obj = new ShortestPathAlternateColor();
        int n = 5;
        int[][] redEdges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        int[][] blueEdges = new int[][]{{1, 2}, {2, 3}, {3, 1}};

//        n = 3;
//        redEdges = new int[][]{{0, 1}, {0, 2}};
//        blueEdges = new int[][]{{1,0}};
        final int[] values = obj.shortestAlternatingPaths2(n, redEdges, blueEdges);
        System.out.println(Arrays.toString(values));
    }

    /**
     * WIP
     *
     * @param n
     * @param red_edges
     * @param blue_edges
     * @return
     */
    public int[] shortestAlternatingPaths2(int n, int[][] red_edges, int[][] blue_edges) {
        int[][] g = new int[n][n];
        buildGraph(g, n, blue_edges, red_edges);

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        int len = 0;

        int[] res = new int[n];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 0;
        Set<String> visited = new HashSet<>();
        visited.add(0 + "" + 0);
        while (!q.isEmpty()) {
            int size = q.size();
            len++;
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int node = curr[0];
                int color = curr[1];

                for (int j = 1; j < n; j++) {
                    int newColor = g[node][j];
                    if (!visited.contains(j + "" + newColor) && newColor != color) {
                        visited.add(j + "" + newColor);
                        q.offer(new int[]{j, newColor});
                        res[j] = Math.min(res[j], len);
                    }
                }
            }
        }

        for (int i = 0; i < res.length; i++) {
            if (res[i] == Integer.MAX_VALUE) {
                res[i] = -1;
            }
        }

        return res;
    }

    /**
     * 1 --> Only RED
     * 2 --> Only BLUE
     * 3 --> BOTH
     *
     * @param g
     * @param n
     * @param blue_edges
     * @param red_edges
     */
    private void buildGraph(int[][] g, int n, int[][] blue_edges, int[][] red_edges) {
        for (int[] edge : red_edges) {
            g[edge[0]][edge[1]] = 1;
        }

        for (int[] edge : blue_edges) {
            g[edge[0]][edge[1]] += 2;
        }

    }


    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        final int RED = 1;
        final int BLUE = 2;

        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < red_edges.length; i++) {
            int[] edge = red_edges[i];
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1], RED}); // edge - color pair
        }

        for (int i = 0; i < blue_edges.length; i++) {
            int[] edge = blue_edges[i];
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1], BLUE}); // edge - color pair
        }

        int[] res = new int[n];
        res[0] = 0;
        for (int i = 1; i < n; i++) {
            res[i] = bfs(graph, i, n);
        }

        return res;
    }

    public int bfs(Map<Integer, List<int[]>> graph, int dest, int n) {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new int[]{0, -1});
        visited.add(0 + "" + 0);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int node = curr[0];
                int color = curr[1];
                if (node == dest) return level;
                if (graph.containsKey(node)) {
                    for (int[] neighbour : graph.get(node)) {
                        int nextColor = neighbour[1];
                        int next = neighbour[0];
                        if (!visited.contains(next + "" + nextColor) && color != nextColor) {
                            queue.offer(new int[]{next, nextColor});
                            visited.add(next + "" + nextColor);
                        }
                    }
                }
            }

            level++;
        }

        return -1;
    }

}
