import java.util.*;

public class MostSimilarPath {
    public static void main(String[] args) {
        MostSimilarPath obj = new MostSimilarPath();
        // int n = 5;
        // int[][] roads = { { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 2, 4 }
        // };
        // String[] names = { "ATL", "PEK", "LAX", "DXB", "HND" };
        // String[] targetPath = { "ATL", "DXB", "PEK", "LAX" };

        int n = 4;
        int[][] roads = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 }, { 0, 2 } };
        String[] names = { "A", "B", "C", "D" };
        String[] targetPath = { "A", "C", "D" };
        System.out.println(obj.mostSimilar(n, roads, names, targetPath));

        n = 6;
        roads = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
        names = new String[] { "ATL", "PEK", "LAX", "ATL", "DXB", "HND" };
        targetPath = new String[] { "ATL", "DXB", "HND", "DXB", "ATL", "LAX", "PEK" };
        System.out.println(obj.mostSimilar(n, roads, names, targetPath));

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

    Integer[][] dp;
    String[] names;
    String[] targetPath;
    List<List<Integer>> adjMatrix;
    int[][] nextBestChoice;

    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        this.names = names;
        this.targetPath = targetPath;
        this.dp = new Integer[names.length][targetPath.length];
        this.nextBestChoice = new int[names.length][targetPath.length];
        this.adjMatrix = new ArrayList<>();

        for (int i = 0; i < n; i++)
            adjMatrix.add(new ArrayList<>());

        for (int[] road : roads) {
            adjMatrix.get(road[0]).add(road[1]);
            adjMatrix.get(road[1]).add(road[0]);
        }

        int min = Integer.MAX_VALUE;
        // this is the starting vertex from where we will get the path of minimum edit
        // distance
        int start = 0;

        for (int i = 0; i < names.length; i++) {
            // we will calculate the minimum distance from each vertex
            int minEditDistance = dfs(i, 0);

            // whichever city gives the minimum edit distance, we will start tracing the
            // result path from that vertex
            if (minEditDistance < min) {
                min = minEditDistance;
                start = i;
            }
        }

        System.out.println("Min distance: " + min);
        System.out.println("Start vertex: " + start);

        for (int i = 0; i < nextBestChoice.length; i++) {
            System.out.println(Arrays.toString(nextBestChoice[i]));
        }
        System.out.println();

        List<Integer> res = new ArrayList<>();
        while (res.size() < this.targetPath.length) {
            res.add(start);
            start = this.nextBestChoice[start][res.size() - 1];
        }
        return res;
    }

    // namesIndex -> index for the names array
    // targetPathIndex -> index for target path array
    private int dfs(int namesIndex, int targetPathIndex) {
        if (dp[namesIndex][targetPathIndex] != null) {
            return dp[namesIndex][targetPathIndex];
        }

        int editDistance = names[namesIndex].equals(targetPath[targetPathIndex]) ? 0 : 1;

        if (targetPathIndex == this.targetPath.length - 1) {
            // according to question the valid names array should be same as target path
            // if names index becomes equal to target path, we are done
            return editDistance;
        }

        int min = Integer.MAX_VALUE;
        // compute the minimum distance from each neighbor. Save the min distance
        // as well as the neighbor which gave the minimum distance
        for (int neighbor : adjMatrix.get(namesIndex)) {
            int editDistanceOfNeighbors = dfs(neighbor, targetPathIndex + 1);
            if (editDistanceOfNeighbors < min) {
                // the minimum edit distance from the start city(city at nodeIndex)
                // and the city at targetPath at index targetPathIndex
                // passes through this neighbor
                min = editDistanceOfNeighbors;
                this.nextBestChoice[namesIndex][targetPathIndex] = neighbor;
            }
        }

        editDistance += min;
        // memoisation
        dp[namesIndex][targetPathIndex] = editDistance;
        return editDistance;
    }
}