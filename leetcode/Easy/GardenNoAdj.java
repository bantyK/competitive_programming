import java.util.*;

// 1042 https://leetcode.com/problems/flower-planting-with-no-adjacent/
public class GardenNoAdj {

    public int[] gardenNoAdj(int n, int[][] paths) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] path : paths) {
            graph.putIfAbsent(path[0], new ArrayList<>());
            graph.get(path[0]).add(path[1]);

            graph.putIfAbsent(path[1], new ArrayList<>());
            graph.get(path[1]).add(path[0]);
        }

        int[] res = new int[n];
        for (int v : graph.keySet()) {
            for (int i = 1; i <= 4; i++) {
                boolean used = false;
                for (int neighbour : graph.get(v)) {
                    if (res[neighbour - 1] == i) {
                        used = true;
                        break;
                    }
                }

                if (!used) {
                    res[v - 1] = i;
                    break;
                }

            }
        }
        return res;
    }
}
