import java.util.*;

//1288 https://leetcode.com/problems/remove-covered-intervals/
public class RemoveCoveredIntervals {
    public static void main(String[] args) {
        RemoveCoveredIntervals obj = new RemoveCoveredIntervals();
        System.out.println(obj.removeCoveredIntervals(new int[][]{{1, 4}, {3, 6}, {2, 8}}) == 2);
        System.out.println(obj.removeCoveredIntervals(new int[][]{{3, 10}, {4, 10}, {5, 11}}) == 2);
        System.out.println(obj.removeCoveredIntervals(new int[][]{{1, 2}, {1, 4}, {3, 4}}) == 1);
        System.out.println(obj.removeCoveredIntervals(new int[][]{{0, 10}, {5, 12}}) == 2);
        System.out.println(obj.removeCoveredIntervals(new int[][]{{1, 4}, {2, 3}}) == 1);
        System.out.println(obj.removeCoveredIntervals(new int[][]{{1, 10}, {1, 9}}) == 1);

        int[][] intervals = new int[][]{{34335, 39239}, {15875, 91969}, {29673, 66453}, {53548, 69161}, {40618, 93111}};
        System.out.println(obj.removeCoveredIntervals(intervals) == 2);
    }

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (interval1, interval2) -> {
            if (interval1[0] == interval2[0]) {
                return interval2[1] - interval1[1];
            }

            return interval1[0] - interval2[0];
        });
        int remaining = 0;
        for (int i = 0; i < intervals.length; i++) {
            remaining++;
            int j;
            j = i + 1;
            while (j < intervals.length && intervals[i][1] >= intervals[j][0] && intervals[i][1] >= intervals[j][1]) {
                // this means intervals[j] is completely covered by intervals[i];
                j++;
            }

            i = j - 1; // all the intervals between i and j are discarded
        }

        return remaining;
    }
}

