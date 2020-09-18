import java.util.*;

//57 https://leetcode.com/problems/insert-interval/
public class InsertInterval {
    public static void main(String[] args) {
        InsertInterval obj = new InsertInterval();
//        int[][] intervals = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[][] intervals = new int[][]{{1, 5}, {6, 8}};
        int[][] res = obj.insert(intervals, new int[]{3, 7});
        for (int[] row : res)
            System.out.println(Arrays.toString(row));
    }

    public int[][] insertOptimised(int[][] intervals, int[] newInterval) {
        int start = newInterval[0];
        int end = newInterval[1];
        int i = 0;
        List<int[]> result = new ArrayList<>();
        while(i < intervals.length && start > intervals[i][1]) {
            result.add(intervals[i]);
        }
        while(i < intervals.length && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
        }
        result.add(new int[]{start, end});
        while(i < intervals.length) {
            result.add(intervals[i]);
        }

        return asArray(result);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        if (intervals.length == 0) {
            result.add(newInterval);
            return asArray(result);
        }

        int i = 0;
        while (i < intervals.length && newInterval[0] > intervals[i][1]) {
            // ------
            //         -------
            result.add(intervals[i]);
            i++;
        }
        if (i < intervals.length && newInterval[1] < intervals[i][0]) {
            result.add(newInterval);

            while (i < intervals.length) {
                result.add(intervals[i++]);
            }
            return asArray(result);
        } else if (i == intervals.length) {
            result.add(newInterval);
            return asArray(result);
        } else {
            // need to merge the starting points
            intervals[i][0] = Math.min(intervals[i][0], newInterval[0]);
            if(i == intervals.length - 1) {
                intervals[i][1] = Math.max(intervals[i][1], newInterval[1]);
                result.add(intervals[i]);
                return asArray(result);
            }
            int j = i + 1;
            while (j < intervals.length && newInterval[1] > intervals[j][0]) {
                j++;
            }
            if (j == intervals.length) {
                intervals[i][1] = Math.max(newInterval[1], intervals[j-1][1]);
                result.add(intervals[i]);
            } else {
                if (newInterval[1] < intervals[j][0]) {
                    //   -----      --------
                    //     -------
                    intervals[i][1] = Math.max(newInterval[1], intervals[j-1][1]);
                    result.add(intervals[i]);
                    while (j < intervals.length) {
                        result.add(intervals[j++]);
                    }
                    return asArray(result);
                } else if(newInterval[1] == intervals[j][0]) {
                    //      -----------
                    //   ---
                    intervals[i][1] = intervals[j][1];
                    j++;
                } else {
                    intervals[i][1] = Math.max(intervals[j][1], newInterval[1]);
                }
                result.add(intervals[i]);
                while (j < intervals.length) {
                    result.add(intervals[j++]);
                }
            }
            return asArray(result);
        }
    }

    private int[][] asArray(List<int[]> list) {
        int[][] arr = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
