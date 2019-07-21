package com.company.leet;

import java.util.Arrays;

public class ParitySort {

    public static void main(String[] args) {
        int[] A = new int[]{0, 2};

        System.out.println(Arrays.toString(sortArrayByParity(A)));

    }

    private static int[] sortArrayByParity(int[] A) {
        int start = 0;
        int end = A.length - 1;
        int temp;

        while (start < end) {
            while (A[start] % 2 == 0 && start < end)
                start++;

            while (A[end] % 2 == 1 && end > start)
                end--;

            temp = A[start];
            A[start] = A[end];
            A[end] = temp;
            start++;
            end--;
        }

        return A;
    }
}
