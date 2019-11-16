package solutions.medium;

import solutions.util.ArrayUtils;

import java.util.*;

// 56 https://leetcode.com/problems/merge-intervals/
public class MergeIntervals {
    public static void main(String[] args) {
        MergeIntervals obj = new MergeIntervals();

        ArrayUtils.print2DArray(obj.merge(new int[][]{
                {1, 4},
                {4, 5}
        }));
    }

    public int[][] mergeUsingStack(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });

        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] top = stack.peek();
            if (top[1] < intervals[i][0]) {
                // non overlapping
                stack.push(intervals[i]);
            } else if (top[1] < intervals[i][1]) {
                top[1] = intervals[i][1];
                stack.pop();
                stack.push(top);
            }
        }
        int[][] result = new int[stack.size()][2];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }

        return result;
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }

        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

        int[] current = intervals[0];
        List<int[]> outputList = new ArrayList<>();
        outputList.add(current);

        for (int[] interval : intervals) {
            int currentEnd = current[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            if (currentEnd >= nextStart) {
                current[1] = Math.max(currentEnd, nextEnd);
            } else {
                current = interval;
                outputList.add(current);
            }

        }

        return outputList.toArray(new int[outputList.size()][]);

    }

}
