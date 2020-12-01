import input.TwoDArrayReader;

// 1631 https://leetcode.com/problems/path-with-minimum-effort/
public class MinimumEffortPath {

    public static void main(String[] args) {
        MinimumEffortPath obj = new MinimumEffortPath();
        int[][] grid = new TwoDArrayReader().get2DArray();
        System.out.println(obj.minimumEffortPath(grid));
    }

    public int minimumEffortPath(int[][] A) {
        int ans = Integer.MAX_VALUE;
        int low = 0;
        int high = Integer.MIN_VALUE;

        int rows = A.length;
        int cols = A[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                high = Math.max(high, A[i][j]);
            }
        }

        System.out.println(low);
        System.out.println(high);

        while (low <= high) {
            int mid = low + (high - low) / 2;

            boolean[][] visited = new boolean[rows][cols];

            if (dfs(A, 0, 0, mid, visited)) {
                System.out.println("Possible for : " + mid);
                ans = Math.min(ans, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean dfs(int[][] grid, int row, int col, int maxDiffAllowed, boolean[][] visited) {
        if (row == grid.length - 1 && col == grid[0].length - 1) return true;

        boolean possible = false;

        visited[row][col] = true;

        // try going up
        if (row > 0 && !visited[row - 1][col] && Math.abs(grid[row - 1][col] - grid[row][col]) <= maxDiffAllowed) {
            possible = possible || dfs(grid, row - 1, col, maxDiffAllowed, visited);
        }

        // try going down
        if (row < grid.length - 1 && !visited[row + 1][col] && Math.abs(grid[row + 1][col] - grid[row][col]) <= maxDiffAllowed) {
            possible = possible || dfs(grid, row + 1, col, maxDiffAllowed, visited);
        }

        //try going left
        if (col > 0 && !visited[row][col - 1] && Math.abs(grid[row][col - 1] - grid[row][col]) <= maxDiffAllowed) {
            possible = possible || dfs(grid, row, col - 1, maxDiffAllowed, visited);
        }

        // try going right
        if (col < grid[0].length - 1 && !visited[row][col + 1] && Math.abs(grid[row][col + 1] - grid[row][col]) <= maxDiffAllowed) {
            possible = possible || dfs(grid, row, col + 1, maxDiffAllowed, visited);
        }

        return possible;
    }

}