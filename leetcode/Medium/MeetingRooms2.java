import java.util.*;


//253 https://leetcode.com/problems/meeting-rooms-ii/
public class MeetingRooms2 {
    public static void main(String[] args) {
        MeetingRooms2 obj = new MeetingRooms2();


        final List<Interval> intervals = Arrays.asList(new Interval(1,10), new Interval(5, 10), new Interval(8, 20));
        System.out.println(obj.minMeetingRooms(intervals));
    }


    public int minMeetingRooms(List<Interval> intervals) {
        intervals.sort((i1, i2) -> i1.start - i2.start);

        PriorityQueue<Interval> pq = new PriorityQueue<>((i1, i2) -> i1.end - i2.end);

        for (Interval interval : intervals) {
            if (pq.isEmpty()) {
                pq.offer(interval);
            } else {
                if (interval.start >= pq.peek().end) {
                    pq.poll();
                }
                pq.offer(interval);
            }
        }

        return pq.size();
    }

    private static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
