package solutions.easy;

import solutions.util.ArrayUtils;

import java.util.*;

//985  https://leetcode.com/problems/sum-of-even-numbers-after-queries/
public class EvenSumQueries {
    public static void main(String[] args) {
        EvenSumQueries obj = new EvenSumQueries();

        int[] ints = obj.sumEvenAfterQueries(new int[]{-1, 3, -3, 9, -6, 8, -5},
                new int[][]{{-5, 1}, {10, 2}, {-6, 3}, {3, 2}, {9, 5}, {7, 5}, {8, 0}});

        ArrayUtils.printArray(ints);
    }

    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {

        int previousEvenSum = 0;
        int[] result = new int[A.length];
        int k = 0;

        for (int i : A) {
            if (i % 2 == 0) {
                previousEvenSum += i;
            }
        }

        for (int[] query : queries) {
            int val = query[0];
            int index = query[1];

            if (A[index] % 2 == 0) {
                previousEvenSum -= A[index];
                A[index] += val;

                if (val % 2 == 0) {
                    previousEvenSum += A[index];
                }
            } else {
                A[index] += val;
                if (A[index] % 2 == 0) {
                    previousEvenSum += A[index];
                }
            }

            result[k++] = previousEvenSum;
        }

        return result;
    }
}
