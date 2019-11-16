package solutions.medium;

import java.util.*;

// 253: https://leetcode.com/problems/meeting-rooms-ii/
public class MeetingRoom2 {
    public static void main(String[] args) {
        MeetingRoom2 obj = new MeetingRoom2();
    }

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length < 2) return 1;
        Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);

        PriorityQueue<Interval> minHeap = new PriorityQueue<>((i1, i2) -> i1.end - i2.end);

        minHeap.add(intervals[0]);

        for (Interval current : intervals) {
            Interval earliest = minHeap.poll();

            if (current.start >= earliest.end) {
                earliest.end = current.end;
            } else {
                minHeap.add(current);
            }

            minHeap.add(earliest);
        }

        return minHeap.size();
    }

    static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
