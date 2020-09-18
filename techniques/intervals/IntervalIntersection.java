import java.util.*;

//986 https://leetcode.com/problems/interval-list-intersections/

public class IntervalIntersection {
    public static void main(String[] args) {
        IntervalIntersection obj = new IntervalIntersection();
        int[][] A = new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] B = new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        int[][] res = obj.intervalIntersection(A, B);
        for (int[] ints : res) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0;
        int j = 0;

        List<int[]> result = new ArrayList<>();
        while (i < A.length && j < B.length) {
            int[] interval1 = A[i];
            int[] interval2 = B[j];

            // first check the non intersection scenarios
            if (interval2[0] > interval1[1]) {
                //i1 ------
                //i2          ------
                // increment the i pointer because i2's end point is greater than i1 hence still has a probability to intersect with some other interval in i1
                i++;
            } else if (interval1[0] > interval2[1]) {
                //i1            ------
                //i2  ------
                // increment the j pointer because i1's end point is greater than i2 hence has a probability to intersect with some other interval in i2
                j++;
            } else {
                // there is an intersection
                result.add(new int[]{Math.max(interval1[0], interval2[0]), Math.min(interval1[1], interval2[1])});
                if (interval1[1] > interval2[1]) {
                    j++;
                } else if (interval2[1] > interval1[1]) {
                    i++;
                } else {
                    i++;
                    j++;
                }
            }
        }
        int[][] res = new int[result.size()][2];

        for (int k = 0; k < result.size(); k++) {
            res[k] = result.get(k);
        }
        return res;
    }
}
