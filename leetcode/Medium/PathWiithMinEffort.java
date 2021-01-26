import input.TwoDArrayReader;

//1631 https://leetcode.com/problems/path-with-minimum-effort/
public class PathWiithMinEffort {

    public static void main(String[] args) {
        int[][] heights = new int[][]{
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };

        System.out.println(new PathWiithMinEffort().minimumEffortPath(new TwoDArrayReader().get2DArray()));
    }

    public int minimumEffortPath(int[][] heights) {
        int low = 0;
        int high = 1000000;
        int minEffort = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (valid(heights, mid)) {
                minEffort = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return minEffort;
    }

    public boolean valid(int[][] heights, int maxEffort) {
        boolean[][] visited = new boolean[heights.length][heights[0].length];

        return dfs(heights, 0, 0, maxEffort, heights[0][0], visited);
    }

    private boolean dfs(int[][] heights, int i, int j, int maxEffort, int prevValue, boolean[][] visited) {
        if (i < 0 || i >= heights.length) return false;
        if (j < 0 || j >= heights[0].length) return false;
        if (visited[i][j]) return false;
        if (Math.abs(heights[i][j] - prevValue) > maxEffort) return false;

        if (i == heights.length - 1 && j == heights[0].length - 1) return true;

        visited[i][j] = true;

        return dfs(heights, i + 1, j, maxEffort, heights[i][j], visited)
                || dfs(heights, i - 1, j, maxEffort, heights[i][j], visited)
                || dfs(heights, i, j + 1, maxEffort, heights[i][j], visited)
                || dfs(heights, i, j - 1, maxEffort, heights[i][j], visited);
    }
}
