package com.company.leet;

import java.util.Arrays;

public class SortedSquares {

    public static void main(String[] args) {
        int[] A = new int[]{-10, -9, -8, -7, -6, 0, 1, 2, 3};
        System.out.println(Arrays.toString(sortedSquares(A)));
    }

    private static int[] sortedSquares(int[] A) {
        int startNegIndex = 0;
        int endNegIndex = 0;
        int temp;

        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                endNegIndex = i - 1;
                break;
            }
        }

        while (startNegIndex <= endNegIndex) {
            temp = A[startNegIndex];
            A[startNegIndex] = A[endNegIndex];
            A[endNegIndex] = temp;

            startNegIndex++;
            endNegIndex--;
        }

        return A;
    }

    private static int[] sortedSquares1(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0)
                A[i] *= -1;
        }

        Arrays.sort(A);

        for (int i = 0; i < A.length; i++) {
            A[i] *= A[i];
        }

        return A;
    }
}
