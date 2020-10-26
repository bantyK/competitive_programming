import java.util.*;

//729 https://leetcode.com/problems/my-calendar-i/
public class MyCalendar1 {
    List<int[]> bookedIntervals;
    TreeSet<int[]> booked;

    public MyCalendar1() {
        bookedIntervals = new ArrayList<>();
        booked = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
    }

    public static void main(String[] args) {
        MyCalendar1 obj = new MyCalendar1();

        obj.book(5, 10);
        obj.book(15, 20);
        obj.book(12, 15);
    }


    // Brute force: O(n2)
    public boolean book1(int start, int end) {
        for (int[] interval : bookedIntervals) {
            int overlapStart = Math.max(interval[0], start);
            int overlapEnd = Math.min(interval[1], end);
            if (overlapStart < overlapEnd) return false;
        }

        bookedIntervals.add(new int[]{start, end});
        return true;
    }

    public boolean book(int start, int end) {
        int[] newBooking = new int[]{start, end};


        int[] floor = booked.floor(newBooking);
        if (floor != null && start < floor[1]) return false;

        int[] ceil = booked.ceiling(newBooking);
        if (ceil != null && ceil[0] < end) return false;

        booked.add(newBooking);
        return true;
    }
}
