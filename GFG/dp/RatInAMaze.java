import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

// https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
public class RatInAMaze {
    static int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static String[] directionString = new String[]{"R", "D", "L", "U"};

    public static void main(String[] args) throws IOException {
        RatInAMaze obj = new RatInAMaze();
        int[][] grid = new TwoDArrayReader().get2DArray();
        List<String> strings = printAllPaths(grid);
        System.out.println(strings);

    }

    public static List<String> printAllPaths(int[][] grid) {
        List<String> allPaths = new ArrayList<>();
        int rows = grid.length;
        if (rows == 0) return allPaths;
        int cols = grid[0].length;
        Set<String> visited = new HashSet<>();
        dfs(grid, 0, 0, new StringBuilder(), allPaths, visited);
        allPaths.sort(String::compareTo);
        return allPaths;
    }

    private static void dfs(int[][] grid, int i, int j, StringBuilder builder, List<String> paths, Set<String> visited) {
        if (i < 0 || i >= grid.length) return;
        if (j < 0 || j >= grid[0].length) return;
        if (grid[i][j] == 0) return;
        if (visited.contains(i + "" + j)) return;

        if (i == j && i == grid.length - 1) {
            paths.add(builder.toString());
            return;
        }

        visited.add(i + "" + j);

        for (int x = 0; x < 4; x++) {
            int[] dir = direction[x];
            int newI = i + dir[0];
            int newJ = j + dir[1];

            if (!visited.contains(newI + "" + newJ)) {
                dfs(grid, newI, newJ, builder.append(directionString[x]), paths, visited);
                builder.deleteCharAt(builder.length() - 1);
                visited.remove(newI + "" + newJ);
            }
        }
    }
}
