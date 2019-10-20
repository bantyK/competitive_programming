package solutions.medium;

import solutions.util.ArrayUtils;

//https://leetcode.com/problems/max-increase-to-keep-city-skyline/
public class SkyCityClean {
    public static void main(String[] args) {
        SkyCityClean obj = new SkyCityClean();

        int delta = obj.maxIncreaseKeepingSkyline(
                new int[][]{{3, 0, 8, 4},
                        {2, 4, 5, 7},
                        {9, 2, 6, 3},
                        {0, 3, 1, 0}});

        System.out.println(delta);
    }

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int originalHeightCount = getTotalHeight(grid);
        int[] topBottomSkyline = getTopBottomSkylineHeight(grid);
        int[] leftRightSkyline = getLeftRightSkylineHeight(grid);
        topHeightIncrease(grid, topBottomSkyline);
        leftHeightIncrease(grid, leftRightSkyline);
        int changedCount = getTotalHeight(grid);
        return changedCount - originalHeightCount;
    }

    private int getTotalHeight(int[][] grid) {
        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid.length; column++) {
                count += grid[row][column];
            }
        }
        return count;
    }

    private void leftHeightIncrease(int[][] grid, int[] leftRightSkyline) {
        for (int row = 0; row < grid.length; row++) {
            int maxHeight = leftRightSkyline[row];
            for (int column = 0; column < grid.length; column++) {
                if (grid[row][column] > maxHeight)
                    grid[row][column] = maxHeight;
            }
        }
    }

    private void topHeightIncrease(int[][] grid, int[] topBottomSkyline) {
        for (int column = 0; column < grid.length; column++) {
            int maxHeightForColumn = topBottomSkyline[column];
            for (int row = 0; row < grid.length; row++) {
                grid[row][column] = maxHeightForColumn;
            }
        }
    }

    private int[] getTopBottomSkylineHeight(int[][] grid) {
        int[] topBottomSkyline = new int[grid.length];

        for (int column = 0; column < grid.length; column++) {
            int max = -1;
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][column] > max) {
                    max = grid[row][column];
                }
            }
            topBottomSkyline[column] = max;
        }
        return topBottomSkyline;
    }

    private int[] getLeftRightSkylineHeight(int[][] grid) {
        int[] leftRightSkyline = new int[grid.length];

        for (int row = 0; row < grid.length; row++) {
            int max = -1;
            for (int column = 0; column < grid.length; column++) {
                if (grid[row][column] > max) {
                    max = grid[row][column];
                }
            }
            leftRightSkyline[row] = max;
        }
        return leftRightSkyline;
    }
}
