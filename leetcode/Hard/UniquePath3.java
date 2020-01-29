import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

//980 https://leetcode.com/problems/unique-paths-iii/
public class UniquePath3 {
    private static int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int res = 0;

    public static void main(String[] args) throws IOException {
        UniquePath3 obj = new UniquePath3();
        final int[][] grid = new TwoDArrayReader().get2DArray();
        final int res = obj.uniquePathsIII(grid);
        System.out.println(res);
    }

    public int uniquePathsIII(int[][] grid) {
        res = 0;

        int numZeros = 0;
        int[] startIndex = new int[2];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    numZeros += 1;
                } else if (grid[i][j] == 1) {
                    startIndex = new int[]{i, j};
                }
            }
        }
        dfs(grid, startIndex[0], startIndex[1], new ArrayList<>(), numZeros);

        return res;
    }

    private boolean dfs(int[][] grid, int i, int j, List<int[]> currentPath, int numZeros) {
        if (i < 0 || i >= grid.length) return false;
        if (j < 0 || j >= grid[i].length) return false;
        if (grid[i][j] == -1 || grid[i][j] == -2) return false;
        if (grid[i][j] == 2) return true;
        int temp = grid[i][j];
        grid[i][j] = -2;

        currentPath.add(new int[]{i, j});

        for (int[] dir : directions) {
            int nextX = i + dir[0];
            int nextY = j + dir[1];

            if (dfs(grid, nextX, nextY, currentPath, numZeros)) {
                if (currentPath.size() == numZeros + 1) {
                    res += 1;
                }
            }
        }
        currentPath.remove(currentPath.size() - 1);
        grid[i][j] = temp;
        return false;
    }
}
