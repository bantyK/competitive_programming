import java.util.*;

// 1192 https://leetcode.com/problems/critical-connections-in-a-network/
public class CriticalConnections {
    int id = 0;
    int[] ids;
    int[] low;
    boolean[] visited;

    public static void main(String[] args) {
        CriticalConnections obj = new CriticalConnections();
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0,1));
        connections.add(Arrays.asList(1,2));
        connections.add(Arrays.asList(2,0));
        connections.add(Arrays.asList(1,3));
        System.out.println(obj.criticalConnections(9, connections));
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = createGraph(n);
        for(List<Integer> connection : connections) {
            addEdge(graph, connection.get(0), connection.get(1));
        }

        id = 0;
        ids = new int[n];
        low = new int[n];
        visited = new boolean[n];

        List<List<Integer>> criticalConnections = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, ids, low, visited, i, -1, criticalConnections);
            }
        }
        // System.out.println(Arrays.toString(low));
        return criticalConnections;
    }

    private List<List<Integer>> createGraph(int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    private void addEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    private void dfs(List<List<Integer>> graph, int[] ids, int[] low, boolean[] visited, int at, int parent, List<List<Integer>> criticalConnections) {
        visited[at] = true;
        low[at] = ids[at] = id++;
        // System.out.println(id);
        for (Integer to : graph.get(at)) {
            if (to == parent) continue;

            if (!visited[to]) {
                dfs(graph, ids, low, visited, to, at, criticalConnections);
                low[at] = Math.min(low[at], low[to]);
                if (ids[at] < low[to]) {
                    criticalConnections.add(Arrays.asList(at, to));
                }
            } else {
                low[at] = Math.min(low[at], low[to]);
            }
        }
    }
}

