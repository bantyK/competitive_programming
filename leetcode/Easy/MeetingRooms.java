import java.util.*;


//252 https://leetcode.com/problems/meeting-rooms/
public class MeetingRooms {
    public static void main(String[] args) {
        MeetingRooms obj = new MeetingRooms();


        final List<Interval> intervals = Arrays.asList(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20));
        System.out.println(obj.canAttendMeetings(intervals));
    }

    /**
     * Solution by interval comparison
     *
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        intervals.sort((i1, i2) -> i1.start - i2.start);

        for(int i = 0; i < intervals.size() - 1; i++) {
            Interval first = intervals.get(i);
            Interval second = intervals.get(i + 1);

            if(first.end >= second.start) return false;
        }

        return true;
    }


    private static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
