package solutions.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/spiral-matrix/
public class SpiralMatrix {
    public static void main(String[] args) {
        SpiralMatrix obj = new SpiralMatrix();

        List<Integer> integers = obj.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}});

        for (int i : integers) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int direction = 0;

        while (bottom >= top && right >= left) {

            // print top row
            if (direction == 0) {
                for (int i = left; i <= right; i++) {
                    result.add(matrix[top][i]);
                }
                top++;
            } else if (direction == 1) {
                for (int i = top; i <= bottom; i++) {
                    result.add(matrix[i][right]);
                }
                right--;
            } else if (direction == 2) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            } else if (direction == 3) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
            direction = (direction + 1) % 4;

        }

        return result;
    }
}
