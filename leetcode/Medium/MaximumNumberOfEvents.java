import java.util.*;

// 1353 https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/

public class MaximumNumberOfEvents {

    public static void main(String[] args) {
        MaximumNumberOfEvents obj = new MaximumNumberOfEvents();
        System.out.println(obj.maxEvents(new int[][]{{1, 5}, {1, 5}}));
        System.out.println(obj.maxEvents(new int[][]{{1, 2}, {1, 2}, {1, 5}, {1, 5}, {3, 3}}));
        System.out.println(obj.maxEvents(new int[][]{{7, 11}, {7, 11}, {7, 11}, {9, 10}, {9, 11}}));
    }

    public int maxEvents(int[][] events) {
        int n = events.length;

        // sort by start time, if start times are same then by their end times both in ascending order
        Arrays.sort(events, (e1, e2) -> {
            if (e1[0] == e2[0]) {
                return e1[1] - e2[1];
            }
            return e1[0] - e2[0];
        });

        // return the event which ends sooner, will only store the end times
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int attendedEventCount = 0;
        int minDays = Integer.MAX_VALUE;
        int maxDays = Integer.MIN_VALUE;
        for (int[] event : events) {
            minDays = Math.min(minDays, event[0]);
            maxDays = Math.max(maxDays, event[1]);
        }

        int currentDay = minDays;
        int eventIndex = 0;

        while (currentDay <= maxDays) {
            while (eventIndex < n && events[eventIndex][0] <= currentDay) {
                // because the event list is sorted, we dont have to worry about the start times of the events
                // Sorting gurantees that that the events we are considering have start days greater than equal to current Day
                minHeap.offer(events[eventIndex][1]);
                eventIndex++;
            }

            while (!minHeap.isEmpty() && minHeap.peek() < currentDay) {
                // the events which has already ended, cannot be considered again
                minHeap.poll();
            }
            if (!minHeap.isEmpty()) {
                // attend the event which is ending earliest
                attendedEventCount++;
                minHeap.poll();
            }

            currentDay++;
        }
        return attendedEventCount;
    }
}
