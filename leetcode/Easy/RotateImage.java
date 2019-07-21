package com.company.leet;

import java.io.IOException;

public class RotateImage {

    public static void main(String[] args) throws IOException {
        int[][] A = new int[][]{{1, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 0, 1, 1}, {1, 0, 1, 1, 0}};

        int singleArrayLength = A[0].length;
        int start = 0;
        int end = start + singleArrayLength;
        int temp;
        int x, y;
        for (int i = 0; i < A.length; i++) {
            x = start;
            y = end - 1;
            while (x < y) {
                temp = A[i][x];
                A[i][x] = A[i][y];
                A[i][y] = temp;

                if (A[i][x] == 1)
                    A[i][x] = 0;
                else
                    A[i][x] = 1;

                if (A[i][y] == 1)
                    A[i][y] = 0;
                else
                    A[i][y] = 1;
                x++;
                y--;
            }

            if (x == y) {
                if (A[i][x] == 1) A[i][x] = 0;
                else A[i][x] = 1;
            }
        }

        print(A);
    }

    private static void print(int[][] mat) {
        for (int[] arr : mat) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
