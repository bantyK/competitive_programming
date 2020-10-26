import java.util.*;

//731 https://leetcode.com/problems/my-calendar-ii/
public class MyCalendarTwo {
    List<int[]> booked;
    MyCalendar cal;

    public MyCalendarTwo() {
        booked = new ArrayList<>();
        // cal = new MyCalendar();
    }

    public static void main(String[] args) {
        MyCalendarTwo obj = new MyCalendarTwo();
    }

    public boolean book(int start, int end) {
        cal = new MyCalendar();
        for (int[] book : booked) {
            int overlappedStart = Math.max(book[0], start);
            int overlappedEnd = Math.min(book[1], end);
            if (overlappedStart < overlappedEnd) {
                // singly booked, check for double booking of overlapped section
                if (cal.checkDoubleBookings(overlappedStart, overlappedEnd)) {
                    return false;
                }
            }
        }
        booked.add(new int[]{start, end});
        return true;
    }

    private static class MyCalendar {
        List<int[]> doubleBooked = new ArrayList<>();

        public boolean checkDoubleBookings(int start, int end) {
            for (int[] book : doubleBooked) {
                if (Math.max(book[0], start) < Math.min(book[1], end)) return true;
            }
            doubleBooked.add(new int[]{start, end});
            return false;
        }
    }
}
