package solutions.medium;

import java.util.*;

import solutions.util.ArrayUtils;

// 986: https://leetcode.com/problems/interval-list-intersections/
public class OverlappingIntervals {
    public static void main(String[] args) {
        OverlappingIntervals obj = new OverlappingIntervals();
        int[][] A = new int[][]{
                {0, 2},
                {5, 10},
                {13, 23},
                {24, 25}

        };

        int[][] B = new int[][]{
                {1, 5},
                {8, 12},
                {15, 24},
                {25, 26}

        };


        int[][] results = obj.intervalIntersection(A, B);
        ArrayUtils.print2DArray(results);
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A.length == 0 || B.length == 0) {
            return new int[][]{};
        }

        if (A.length < B.length) {
            int[][] temp = A;
            A = B;
            B = temp;
        }

        List<int[]> res = new ArrayList<>();

        int[] interval1;
        int[] interval2;

        int a = 0;
        int b = 0;

        while (a < A.length && b < B.length) {
            interval1 = A[a];
            interval2 = B[b];

            int startMax = Math.max(interval1[0], interval2[0]);
            int endMin = Math.min(interval1[1], interval2[1]);

            if (startMax <= endMin) {
                res.add(new int[]{startMax, endMin});
            }

            if (interval1[1] == endMin ) {
                a++;
            }
            if (interval2[1] == endMin) {
                b++;
            }
        }

        int[][] result = new int[res.size()][2];

        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }

        return result;
    }

}
