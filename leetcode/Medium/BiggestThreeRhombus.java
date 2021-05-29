import input.TwoDArrayReader;

import java.util.*;

// 1878 https://leetcode.com/problems/get-biggest-three-rhombus-sums-in-a-grid/
public class BiggestThreeRhombus {

    public static void main(String[] args) {
        int[][] grid = TwoDArrayReader.get2DArray();
        BiggestThreeRhombus obj = new BiggestThreeRhombus();
        obj.calculateRhombusSum(grid, 2, 2, 0);
        System.out.println(Arrays.toString(obj.getBiggestThree(grid)));
    }

    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                seen.add(grid[i][j]);

                for (int L = 1; L < 100; L++) {
                    int curr = calculateRhombusSum(grid, L, i, j);
                    if (curr != 1e9) {
                        seen.add(curr);
                    } else {
                        break;
                    }
                }
            }
        }

        return seen.stream().sorted(Comparator.reverseOrder()).limit(3).mapToInt(Integer::intValue).toArray();
    }


    // Calculate the rhombus sum with the left corner of rhombus starting at (r,c)
    private int calculateRhombusSum(int[][] grid, int size, int r, int c) {
        // making the rhombuses from top to bottom
        if (r + size >= grid.length || r - size < 0 || (c + 2 * size) >= grid[0].length) {
            return (int) 1e9; // some large value which is not possible according to given constraint
        }

        int sum = 0;

        // inner points of the rhombus
        for (int k = 1; k < size; k++) {
            sum += grid[r - k][c + k];
            sum += grid[r + k][c + k];
            sum += grid[r - k][c + 2 * size - k];
            sum += grid[r + k][c + 2 * size - k];
        }

        // corner point of the rhombus
        sum += grid[r][c];
        sum += grid[r][c + 2 * size];
        sum += grid[r + size][c + size];
        sum += grid[r - size][c + size];

        return sum;
    }
}