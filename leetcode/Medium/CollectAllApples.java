import java.util.*;

// 1443 https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
public class CollectAllApples {
    public static void main(String[] args) {

//        int[][] edges = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
//        List<Boolean> hasApples = Arrays.asList(false, false, true, false, true, true, false);
//        int n = 7;
//        System.out.println(new CollectAllApples().minTime(n, edges, hasApples) == 8);

//        int[][] edges = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
//        List<Boolean> hasApples = Arrays.asList(false,false,true,false,false,true,false);
//        int n = 7;
//
//        System.out.println(new CollectAllApples().minTime(n, edges, hasApples) == 6);


        int[][] edges = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        List<Boolean> hasApples = Arrays.asList(false, false, false, false, false, false, false);
        int n = 7;

        System.out.println(new CollectAllApples().minTime(n, edges, hasApples) == 0);


//        int n = 4;
//        int[][] edges = {{0,2},{0,3},{1,2}};
//        List<Boolean> hasApple = Arrays.asList(false,true,false,false);
//        System.out.println(new CollectAllApples().minTime(n, edges, hasApple) == 4);


    }

    public int minTime(int n, int[][] edges, List<Boolean> list) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // create an undirected graph
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);

            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] hasApples = new boolean[n];
        for (int i = 0; i < n; i++) {
            hasApples[i] = list.get(i);
        }

        boolean[] visited = new boolean[n];
        return dfs(graph, 0, hasApples, visited).steps;
    }

    private Node dfs(Map<Integer, List<Integer>> graph, int currentVertex, boolean[] hasApples, boolean[] visited) {
        if (visited[currentVertex]) {
            return new Node(0, false);
        }

        int totalSteps = 0;
        boolean hasAppleAtCurrentVertex = hasApples[currentVertex];

        visited[currentVertex] = true;

        // count the number of steps required to collect apples from all the children of the current node
        // If any of the children conttains an apple, we have to travel through its parent, regardless of whether the parent contains an apple or not
        for (int child : graph.getOrDefault(currentVertex, new ArrayList<>())) {
            Node node = dfs(graph, child, hasApples, visited);
            totalSteps += node.steps;
            if (node.appleFound) {
                // if any of the child has any apple, we have to go through the current vertex,
                // even though it does not have any apple.
                // this boolean variable basically tracks that, so that its parent count the steps
                // taken to reach this node
                hasAppleAtCurrentVertex = true;
                totalSteps += 2;
            }
        }

        // gather steps from all the children and finally return the result
        return new Node(totalSteps, hasAppleAtCurrentVertex);
    }

    private static class Node {
        int steps;
        boolean appleFound;

        public Node(int steps, boolean appleFound) {
            this.steps = steps;
            this.appleFound = appleFound;
        }
    }
}
