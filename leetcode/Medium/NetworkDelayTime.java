import java.util.*;

// 743 https://leetcode.com/problems/network-delay-time/
public class NetworkDelayTime {
    public static void main(String[] args) {
        NetworkDelayTime obj = new NetworkDelayTime();
        int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1,}, {4, 4, 0}};
        int n = 5;
        int k = 2;
        final int res = obj.networkDelayTime(times, n, k);
        System.out.println(res);
    }


    /**
        Djikstra's algorithm
    */
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int[] time : times) {
            addEdge(graph, time[0], time[1], time[2]);
        }
        
        return djikstra(graph, N, K);
    }
    
    private int djikstra(Map<Integer, List<int[]>> graph, int n, int src) {
        boolean[] visited = new boolean[n + 1];
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;
        distances[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[]{src, 0});
        
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int distance = curr[1];
            visited[node] = true;
            
            if(graph.containsKey(node)) {
                for(int[] nei : graph.get(node)) {
                    int nextNode = nei[0];
                    int more = nei[1];
                    int newDistance = distance + more;
                    if(!visited[nextNode]) {
                        if(newDistance < distances[nextNode]) {
                            distances[nextNode] = newDistance;
                            pq.offer(new int[]{nextNode, newDistance});    
                        }
                        
                    }
                }        
            }
        }
        
        int maxDistance = 0;
        
        for(int i = 1; i < distances.length; i++) {
            maxDistance = Math.max(maxDistance, distances[i]);
        }
        
        return maxDistance == Integer.MAX_VALUE ? -1 : maxDistance;
    }
    
    private void addEdge(Map<Integer, List<int[]>> graph, int src, int dest, int dist) {
        graph.putIfAbsent(src, new ArrayList<>());
        graph.get(src).add(new int[]{dest, dist});
    }

    /**
     * Extremely slow
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime2(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] time : times) {
            graph.putIfAbsent(time[0], new ArrayList<>());
            graph.get(time[0]).add(new int[]{time[1], time[2]}); // neighbour - distance
        }

        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = -1;
        dfs(graph, k, 0, distances);

        int max = Integer.MIN_VALUE;
        for (int d : distances) {
            max = Math.max(d, max);
        }

        return max == Integer.MAX_VALUE ? -1 : max;
    }

    private void dfs(Map<Integer, List<int[]>> graph, int node, int currDistance, int[] distances) {
        if (currDistance >= distances[node]) return;
        distances[node] = currDistance;

        if (graph.containsKey(node)) {
            for (int[] neighbour : graph.get(node)) {
                dfs(graph, neighbour[0], currDistance + neighbour[1], distances);
            }
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] time : times) {
            graph.putIfAbsent(time[0], new ArrayList<>());
            graph.get(time[0]).add(new int[]{time[1], time[2]}); // neighbour - distance
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, k}); // distance - node pair

        boolean[] visited = new boolean[n + 1];
        int res = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currDistance = curr[0];
            int currNode = curr[1];

            if (visited[currNode]) continue;
            visited[currNode] = true;

            res = currDistance;
            n--;
            if (graph.containsKey(currNode)) {
                for (int[] neighbour : graph.get(currNode)) {
                    pq.add(new int[]{currDistance + neighbour[1], neighbour[0]});
                }
            }
        }

        return n == 0 ? res : -1;
    }


    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node {
        int val;
    }
}
