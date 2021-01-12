import java.util.*;

//1548 https://leetcode.com/problems/the-most-similar-path-in-a-graph/
public class MostSimilarPath2 {
    public static void main(String[] args) {
        MostSimilarPath2 obj = new MostSimilarPath2();
    }

    // The thing that we want to store in the DP table is that, when we reached
    // a city, which index in the target path are we calculating
    // If lets say we reached city "ABC" and at that time, we were comparing this
    // with the city at index j of the targetPath array, then the dp table entry
    // will look like this. dp[i][j] = minEditDistance.
    // i is the index of city "ABC" in the names table. 'j' is the index which we
    // were checking in the targetPath array and minEditDistance is the min edit
    // distance that we calculated at this point. This minEditDistance is the total
    // minimum distance, after we are done comparing all the children of the
    // "ABC" city.
    private PathInfo[][] dp;
    private String[] names;
    private String[] targetPath;
    private Map<Integer, List<Integer>> graph;

    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        this.dp = new PathInfo[names.length][targetPath.length];
        this.names = names;
        this.targetPath = targetPath;
        this.graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        PathInfo best = new PathInfo(Integer.MAX_VALUE, null);
        for (int i = 0; i < n; i++) {
            PathInfo localBest = dfs(i, 0);
            if (localBest.distance < best.distance) {
                best = localBest;
            }
        }

        return best.path;
    }

    private PathInfo dfs(int cityIndex, int targetPathIndex) {
        if (dp[cityIndex][targetPathIndex] != null) {
            return dp[cityIndex][targetPathIndex];
        }
        List<Integer> path = new ArrayList<>();
        path.add(cityIndex);

        int editDistance = names[cityIndex].equals(targetPath[targetPathIndex]) ? 0 : 1;

        if (targetPathIndex == targetPath.length) {
            return new PathInfo(editDistance, path);
        }
        PathInfo best = new PathInfo(Integer.MAX_VALUE, null);

        for (int neighbor : graph.get(cityIndex)) {
            PathInfo local = dfs(neighbor, targetPathIndex + 1);
            if (local.distance < best.distance) {
                best = local;
            }
        }
        path.addAll(best.path);
        best.distance += editDistance;
        dp[cityIndex][targetPathIndex] = best;
        return best;
    }

    private static class PathInfo {
        List<Integer> path;
        int distance;

        public PathInfo(int distance, List<Integer> path) {
            this.path = path;
            this.distance = distance;
        }
    }
}