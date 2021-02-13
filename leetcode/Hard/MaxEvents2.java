import input.TwoDArrayReader;

import java.util.*;

// 1751 https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/
public class MaxEvents2 {

    public static void main(String[] args) {
        int[][] events = new TwoDArrayReader().get2DArray();
        System.out.println(new MaxEvents2().maxValue(events, 2));
    }

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (e1, e2) -> {
            if (e1[0] == e2[0]) return e1[1] - e2[1];
            return e1[0] - e2[0];
        });

        Integer[][] dp = new Integer[events.length][k + 1];
        return helper(events, 0, k, dp);
    }

    private int helper(int[][] events, int index, int k, Integer[][] dp) {
        if (index >= events.length || k == 0) {
            return 0;
        }

        if (dp[index][k] != null) return dp[index][k];

        // value1 is the profit we will get if we attend event at index
        int value1 = events[index][2];
        // This can be optimised with a binary search
        int nextEventIndex = findNextEvent(events, index);

        /*
        Linear search
            int nextEventIndex = index + 1;
            for (; nextEventIndex < events.length; nextEventIndex++) {
                if (events[nextEventIndex][0] > events[index][1]) {
                    break;
                }
            }
         */

        if (nextEventIndex < events.length) {
            value1 += helper(events, nextEventIndex, k - 1, dp);
        }

        // value2 is the profit we will get if we choose to not attent the event at index
        int value2 = helper(events, index + 1, k, dp);

        // the maximum of option1 and option2 is the result
        return dp[index][k] = Math.max(value1, value2);
    }

    private int findNextEvent(int[][] events, int index) {
        // return the index of the event whose start time is greater than the end time of currentEvent

        int low = index + 1;
        int high = events.length;
        int bestIndex = low;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (events[mid][0] > events[index][1]) {
                // this is a valid event, check if there are any events smaller than this
                bestIndex = mid;
                high = mid - 1;
            } else {
                // this is a valid event, end time of the event is less than or equal to start time of event at index
                // need to look for events with greater start time
                low = mid + 1;
            }
        }

        return bestIndex;

    }
}
