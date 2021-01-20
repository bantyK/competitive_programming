package hackerearth;

import java.io.*;
import java.util.*;

// https://www.hackerearth.com/practice/algorithms/graphs/depth-first-search/practice-problems/algorithm/bishu-and-his-girlfriend/
class TestClass {
    private static Set<Integer> hasGirl;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<Integer, PriorityQueue<Integer>> graph = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            graph.put(i, new PriorityQueue<>());
        }

        while (--n > 0) {
            String[] line = br.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        int q = Integer.parseInt(br.readLine());

        hasGirl = new HashSet<>();
        for (int i = 0; i < q; i++) {
            hasGirl.add(Integer.parseInt(br.readLine()));
        }

        System.out.println(bfs(graph));
    }

    private static int bfs(Map<Integer, PriorityQueue<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            PriorityQueue<Integer> neighbors = graph.getOrDefault(curr, new PriorityQueue<>());
            while (!neighbors.isEmpty()) {
                int neighbor = neighbors.poll();
                if (hasGirl.contains(neighbor)) {
                    return neighbor;
                }
                queue.offer(neighbor);
            }
        }
        return -1;
    }
}
