import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

// 48 https://leetcode.com/problems/rotate-image/
public class RotateImage {
    public static void main(String[] args) throws IOException {
        RotateImage obj = new RotateImage();
        int[][] matrix = new TwoDArrayReader().get2DArray();

        obj.rotate(matrix);

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void rotate(int[][] matrix) {
        Collections.reverse(Arrays.asList(matrix));

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

}
