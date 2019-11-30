import java.util.*;
// 1219 https://leetcode.com/problems/path-with-maximum-gold/
public class MaxGoldPath {
    public static void main(String[] args) {
        MaxGoldPath obj = new MaxGoldPath();
        int grid[][] = new int[][]{
            {0, 6, 0},
            {5, 8, 7},
            {0, 9, 0}
        };
        System.out.println(obj.getMaximumGold(grid));
    }

    public int getMaximumGold(int[][] grid) {
        int maxGold = 0;
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] != 0) {
                    maxGold = Math.max(maxGold, dfs(grid, i, j));
                    System.out.println("Max gold so far : (" + i + ", " + j +")" + maxGold);
                }
            }
        }
        
        return maxGold;
    }



    public int dfs(int[][] grid, int row, int col) {
        if(row >= grid.length || row < 0) return 0;
        if(col >= grid[row].length || col < 0) return 0;
        if(grid[row][col] <= 0) return 0;

        int gold = grid[row][col];
        grid[row][col] = -1;

        int bottom = dfs(grid, row + 1, col);
        int top = dfs(grid, row - 1, col);
        int left = dfs(grid, row, col - 1);
        int right = dfs(grid, row, col + 1);

        grid[row][col] = gold;

        return grid[row][col] + Math.max(Math.max(right, left), Math.max(top, bottom));
    }
}