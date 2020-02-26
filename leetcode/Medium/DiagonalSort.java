import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

//1329 https://leetcode.com/problems/sort-the-matrix-diagonally/
public class DiagonalSort {
    public static void main(String[] args) throws IOException {
        DiagonalSort obj = new DiagonalSort();
        int[][] mat = new TwoDArrayReader().get2DArray();
        int[][] res = obj.diagonalSort(mat);

        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
    }

    public int[][] diagonalSort(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        Map<Integer, PriorityQueue<Integer>> diagonals = new HashMap<>();

        int min = -1 * (rows - 1);
        int max = cols - 1;

        for (int i = min; i <= max; i++) {
            diagonals.put(i, new PriorityQueue<>((a, b) -> a - b));
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int key = j - i;
                diagonals.get(key).add(mat[i][j]);
            }
        }

        int[][] res = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = diagonals.get(j - i).poll();
            }
        }
        return res;
    }
}
