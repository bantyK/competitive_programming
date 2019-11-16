package solutions.easy;

import java.util.*;

// 252 https://leetcode.com/problems/meeting-rooms/
public class MeetingRoom {
    public static void main(String[] args) {
        MeetingRoom obj = new MeetingRoom();
        boolean b = obj.canAttendMeeting(new int[][]{
                {1, 4},
                {5, 6},
                {5, 7}
        });
        System.out.println(b);
    }

    public boolean canAttendMeeting(int[][] intervals) {
        if (intervals.length < 2) return true;

        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] current = stack.peek();
            int[] interval = intervals[i];
            if (current[1] > interval[0]) {
                return false;
            } else {
                stack.push(interval);
            }
        }

        return true;
    }
}
