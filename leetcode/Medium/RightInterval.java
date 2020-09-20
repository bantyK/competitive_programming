import java.util.*;
//436 https://leetcode.com/problems/find-right-interval/
public class RightInterval {
    public static void main(String[] args) {
        RightInterval obj = new RightInterval();
//        System.out.println(Arrays.toString(obj.findRightIntervalBinarySearch(new int[][]{{1, 2}})));
        System.out.println(Arrays.toString(obj.findRightIntervalBinarySearch(new int[][]{{3, 4}, {2, 3}, {1, 2}})));
        System.out.println(Arrays.toString(obj.findRightIntervalBinarySearch(new int[][]{{1, 4}, {2, 3}, {3, 4}})));
        System.out.println(Arrays.toString(obj.findRightIntervalBinarySearch(new int[][]{{1, 2}, {3, 4}, {4, 5}, {5, 6}, {6, 7}})));
    }

    // Brute Force
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] result = new int[n];

        for (int i = 0; i < intervals.length; i++) {
            int idx = -1;
            for (int j = 0; j < n; j++) {
                if (i != j && intervals[j][0] >= intervals[i][1]) {
                    if (idx == -1 || intervals[idx][0] > intervals[j][0]) {
                        idx = j;
                    }
                }
            }
            result[i] = idx;
        }
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Optimised using Binary search
    public int[] findRightIntervalBinarySearch(int[][] intervals) {
        int length = intervals.length;
        int[] result = new int[length];
        Map<Integer, Integer> map = new HashMap<>();
        int[] starts = new int[length];

        for (int i = 0; i < length; i++) {
            int[] interval = intervals[i];
            map.put(interval[0], i);
            starts[i] = interval[0];
        }

        Arrays.sort(starts);

        for (int i = 0; i < length; i++) {
            int largest = findLargestIndex(starts, intervals[i][1]);
            if (largest == -1 || largest == intervals[i][0]) {
                result[i] = -1;
            } else {
                result[i] = map.get(largest);
            }
        }

        return result;
    }

    private int findLargestIndex(int[] starts, int start) {
        int largest = -1;
        int low = 0;
        int high = starts.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (starts[mid] >= start) {
                largest = starts[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return largest;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Using Treemap
    public int[] findRightIntervalUsingTreeMap(int[][] intervals) {
        int result[] = new int[intervals.length];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < intervals.length; i++) {
            treeMap.put(intervals[i][0], i);
        }

        for (int i = 0; i < intervals.length; i++) {
            Map.Entry<Integer, Integer> entry = treeMap.ceilingEntry(intervals[i][1]);
            result[i] = entry != null ? entry.getValue() : -1;
        }
        return result;
    }
}
