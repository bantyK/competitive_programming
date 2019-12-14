// 5127 https://leetcode.com/problems/remove-covered-intervals/
public class RemovedCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        boolean[] removed = new boolean[intervals.length];
        int remaining = 0;

        for (int i = 0; i < intervals.length; i++) {
            if (removed[i]) continue;
            int[] a = intervals[i];
            for (int j = i + 1; j < intervals.length; j++) {
                int[] b = intervals[j];

                int x = Math.max(a[0], b[0]);
                int y = Math.min(a[1], b[1]);

                if (x == a[0] && y == a[1]) {
                    removed[i] = true;
                } else if (x == b[0] && y == b[1]) {
                    removed[j] = true;
                }
            }
        }

        for (int i = 0; i < removed.length; i++) {
            if (!removed[i]) remaining++;
        }
   return remaining;
    }
}
