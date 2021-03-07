// 1786 https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/
public class RestrictedPaths {
    int MOD = (int) (Math.pow(10, 9) + 7);

    public int countRestrictedPaths(int n, int[][] edges) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int dist = edge[2];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(new int[] { to, dist });

            graph.putIfAbsent(to, new ArrayList<>());
            graph.get(to).add(new int[] { from, dist });
        }

        int[] distances = new int[n + 1];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[n] = 0;

        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] { n, 0 });

        while (!pq.isEmpty()) {
            int[] shortestDistanceNode = pq.poll();
            int curr = shortestDistanceNode[0];
            int dist = shortestDistanceNode[1];
            visited[curr] = true;

            if (distances[curr] < dist) {
                // if we have already found a better distance, no need to add it again
                continue;
            }

            for (int[] edge : graph.get(curr)) {
                int next = edge[0];
                int weight = edge[1];
                if (!visited[next]) {
                    int newDistance = distances[curr] + weight;

                    // edge relaxation
                    if (newDistance < distances[next]) {
                        distances[next] = newDistance;
                        pq.offer(new int[] { next, newDistance });
                    }
                }
            }

        }

        // System.out.println(Arrays.toString(distances));

        Map<Integer, Long> dp = new HashMap<>();
        long count = dfs(graph, n, 1, distances, dp);

        return (int) (count % MOD);
    }

    private long dfs(Map<Integer, List<int[]>> graph, int n, int currentVertex, int[] distances,
            Map<Integer, Long> dp) {
        if (currentVertex == n)
            return 1;

        if (dp.containsKey(currentVertex))
            return dp.get(currentVertex);

        long count = 0;

        for (int[] edge : graph.get(currentVertex)) {
            int next = edge[0];
            if (distances[currentVertex] > distances[next]) {
                count += (dfs(graph, n, next, distances, dp) % MOD);
            }
        }

        dp.put(currentVertex, count);

        return count;
    }
}