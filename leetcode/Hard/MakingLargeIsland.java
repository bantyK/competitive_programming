
import java.util.*;

//827 https://leetcode.com/problems/making-a-large-island/
public class MakingLargeIsland {

    public int largestIsland(int[][] grid) {
        int largest_area = 0;
        int N = grid.length;

        /**
         * Every island in the grid will be marked with an identifier(id starting with 2)
         * The hashmap below will track the id of an island with its area
         *
         * After this is done, we will check for each zero in the grid, and check if this zero cell
         * is directly connected with any island. If it is, we will include that island area with this zero
         * cell. Calculate this for all 4 direction of the zero cell
         *
         * The highest of all such areas will be our ans
         */

        Map<Integer, Integer> areaMap = new HashMap<>();
        int island_id = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j, island_id);
                    areaMap.put(island_id, area);
                    island_id++;
                }
            }
        }

        boolean zero_found = false;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c] == 0) {
                    zero_found = true;

                    // This set contains the unique island ids which surrounds this zero cell
                    // We need unique ids because it is possible that the same island covers
                    // the cell from two side and in that case, we dont want to count that same island
                    // twice or more number of times.
                    Set<Integer> unique_island_ids = new HashSet<>();

                    if (r > 0 && areaMap.containsKey(grid[r - 1][c])) {
                        unique_island_ids.add(grid[r - 1][c]);
                    }

                    if (r < N - 1 && areaMap.containsKey(grid[r + 1][c])) {
                        unique_island_ids.add(grid[r + 1][c]);
                    }

                    if (c > 0 && areaMap.containsKey(grid[r][c - 1])) {
                        unique_island_ids.add(grid[r][c - 1]);
                    }

                    if (c < N - 1 && areaMap.containsKey(grid[r][c + 1])) {
                        unique_island_ids.add(grid[r][c + 1]);
                    }

                    int area = 0;
                    for (int id : unique_island_ids) {
                        area += areaMap.getOrDefault(id, 0);
                    }
                    largest_area = Math.max(largest_area, 1 + area);
                }
            }
        }


        return zero_found ? largest_area : N * N;
    }

    private int dfs(int[][] grid, int r, int c, int id) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || grid[r][c] == id) {
            return 0;
        }

        grid[r][c] = id;

        int from_top = dfs(grid, r - 1, c, id);
        int from_bottom = dfs(grid, r + 1, c, id);
        int from_left = dfs(grid, r, c - 1, id);
        int from_right = dfs(grid, r, c + 1, id);

        return 1 + from_top + from_bottom + from_left + from_right;
    }


}