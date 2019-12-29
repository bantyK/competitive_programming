import java.util.*;

// 787 https://leetcode.com/problems/cheapest-flights-within-k-stops/
// Dijkstra's algorithm

public class CheapestFlightPrice {
    public static void main(String[] args) {
        CheapestFlightPrice obj = new CheapestFlightPrice();
        int n = 5;
        int[][] flights = {{0, 1, 4}, {0, 2, 1}, {2, 1, 2}, {1, 3, 1}, {2, 3, 5}, {3, 4, 3}};
        int src = 0;
        int dst = 4;
        int k = 1;

        int cheapestPrice = obj.findCheapestPrice2(n, flights, src, dst, k);
        System.out.println(cheapestPrice);
    }


    // accepted answer
    public int findCheapestPrice2(int n, int[][] flights, int s, int d, int K) {
        Map<Integer, List<Edge>> graph = new HashMap<>();

        // create the graph
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(new Edge(from, to, cost));
        }


        // vertex-distance-K pair
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.offer(new int[]{s, 0, K + 1});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            int vertex = current[0];
            int cost = current[1];
            int stop = current[2];

            if (vertex == d) {
                return cost;
            }

            if (stop > 0) {
                if (!graph.containsKey(vertex))
                    continue;

                for (Edge edge : graph.get(vertex)) {
                    pq.offer(new int[]{edge.to, cost + edge.weight, stop - 1});
                }
            }
        }

        return -1;
    }

    /**
     * Dijktra's algorithm
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int djikstra(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<Edge>> graph = new HashMap<>();

        // create the graph
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];

            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<>());
            }
            graph.get(from).add(new Edge(from, to, cost));
        }

        int[] distance = new int[n]; // distance array
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;

        Integer[] prev = new Integer[n];
//        prev[src] = src;

        boolean[] visited = new boolean[n];
        // vertex - distance pair
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int index = poll[0];
            int minDistance = poll[1];

            visited[index] = true;

            if (distance[index] < minDistance) continue;

            if (graph.containsKey(index)) {
                for (Edge neighbour : graph.get(index)) {
                    if (visited[neighbour.to]) continue;
                    int newDistance = distance[index] + neighbour.weight;
                    // if new distance is less than what we have in the distance array, the update the array, else skip it
                    if (newDistance <= distance[neighbour.to]) {
                        prev[neighbour.to] = index;
                        distance[neighbour.to] = newDistance;
                        pq.offer(new int[]{neighbour.to, newDistance});
                    }
                }
            }
        }

        System.out.println(Arrays.toString(prev));
        List<Integer> shortestPath = findShortestPath(graph, n, src, dst, distance, prev);

        for (int path : shortestPath) {
            System.out.print(path);
        }

        System.out.println();
        return distance[dst];
    }

    private List<Integer> findShortestPath(Map<Integer, List<Edge>> graph, int n, int src, int dst, int[] distance, Integer[] prev) {
        List<Integer> path = new ArrayList<>();
        if (distance[dst] == Integer.MAX_VALUE) return path;
        for (Integer at = dst; at != null; at = prev[at]) {
            path.add(at);
        }

        Collections.reverse(path);
        return path;
    }

    static class Edge {
        final int from;
        final int to;
        final int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
