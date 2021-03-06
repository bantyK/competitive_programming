import java.util.*;

// 1102 https://leetcode.com/problems/path-with-maximum-minimum-value/
public class MinMaxPath {

    int m, n;
    int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        MinMaxPath obj = new MinMaxPath();
        int[][] matrix = new int[][]{{5, 4, 5}, {1, 2, 6}, {7, 4, 6}};

        System.out.println(obj.maximumMinimumPath(matrix));
    }

    public int maximumMinimumPath(int[][] A) {
        m = A.length;
        n = A[0].length;

        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                values.add(A[i][j]);
            }
        }

        Collections.sort(values);
        int lo = 0;
        int hi = values.size() - 1;

        int ans = 0;
        while (lo <= hi) {
            boolean[][] visited = new boolean[m][n];
            int mid = lo + (hi - lo) / 2;

            if (dfs(A, visited, 0, 0, values.get(mid))) {
                ans = values.get(mid);
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    // Largest value which can be minimum
    // Only consider those path whose score is greater than equal to min
    private boolean dfs(int[][] matrix, boolean[][] visited, int r, int c, int min) {
        if (matrix[r][c] < min) {
            return false;
        }

        if (r == m - 1 && c == n - 1) {
            return true;
        }

        boolean ans = false;

        for (int[] dir : directions) {
            int newR = r + dir[0];
            int newC = c + dir[1];

            if (newR >= 0 && newC >= 0 && newR < m && newC < n && !visited[newR][newC] && matrix[newR][newC] >= min) {
                visited[newR][newC] = true;
                ans = ans || dfs(matrix, visited, newR, newC, min);
            }
        }

        return ans;

    }
}