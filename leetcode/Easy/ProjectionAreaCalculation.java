package com.company.leet;

public class ProjectionAreaCalculation {
    public static void main(String[] args) {
        ProjectionAreaCalculation obj = new ProjectionAreaCalculation();

        int area = obj.projectionArea2(new int[][]{{2, 2, 2}, {2, 1, 2}, {2, 2, 2}});

        System.out.println(area);
    }

    public int projectionArea2(int[][] grid) {
        int xArea = 0;
        int yArea = 0;
        int zArea = 0;
        int len = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            int yMax = 0;
            int zMax = 0;
            for (int j = 0; j < len; j++) {
                if (grid[i][j] > 0) xArea++;
                if (grid[i][j] > zMax) zMax = grid[i][j];
                if (grid[j][i] > yMax) yMax = grid[j][i];
            }

            yArea += yMax;
            zArea += zMax;
        }
        return xArea + yArea + zArea;
    }

    public int projectionArea(int[][] grid) {
        int x = xAxisArea(grid);
        int y = yAxisArea(grid);
        int z = zAxisArea(grid);
        return x + y + z;
    }

    private int xAxisArea(int[][] grid) {
        int area = 0;
        for (int[] arr : grid) {
            for (int i : arr) {
                if (i > 0) {
                    area += 1;
                }
            }
        }
        return area;
    }

    private int zAxisArea(int[][] grid) {
        int area = 0;
        int len = grid[0].length;
        int max;
        for (int[] arr : grid) {
            max = 0;
            for (int j = 0; j < len; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            area += max;
        }
        return area;
    }

    private int yAxisArea(int[][] grid) {
        int area = 0;
        int len = grid[0].length;
        int max;
        for (int i = 0; i < len; i++) {
            max = 0;
            for (int[] ints : grid) {
                if (ints[i] > max) {
                    max = ints[i];
                }
            }
            area += max;
        }
        return area;
    }
}
