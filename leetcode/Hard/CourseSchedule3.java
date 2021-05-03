import input.TwoDArrayReader;

import java.util.*;

//630 https://leetcode.com/problems/course-schedule-iii/
public class CourseSchedule3 {
    public static void main(String[] args) {
        int[][] courses = TwoDArrayReader.get2DArray();
        CourseSchedule3 obj = new CourseSchedule3();
        System.out.println(obj.scheduleCourse(courses));
    }

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]); // sorted on the basis of deadline(shorter first)
        // store the duration of the courses in PQ
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> b - a);
        int time = 0;

        for (int[] course : courses) {
            if (course[0] > course[1]) {
                // if the course duration is greater than its last day then there is noo way we can complete that
                // course in time. Hence no point considering that course
                continue;
            }
            // If the current time is less than the course's guideline, than add that course
            if (time + course[0] <= course[1]) {
                time += course[0];
                maxQ.add(course[0]);
            } else {
                // the current course's deadline is going beyond the current time
                // We can swap this course with one of the previously taken courses but
                // we should do it if this swap gives us more time to add more courses (maximize the count)
                // this will happen only if the duration of this course is less than the highest duration course
                // taken so far
                if (!maxQ.isEmpty() && maxQ.peek() > course[0]) {
                    int maxDuration = maxQ.poll();
                    time -= maxDuration;
                    time += course[0];
                    maxQ.add(course[0]);
                }
            }
        }
        return maxQ.size();
    }

}


/*
    want to maximize the number of courses we can take. Take those courses which take less time to complete(less value of duration)

    // sort by duration

    // keep adding the courses and track the total time taken.

    // also track the completed courses their duration and last day of completion

    // if we come across which we cant take because its last day is before the current day.
    // we can choose to replace any of our previously taken courses with this one
    // we can make this choice only if by making this replacement, we decrease current total time taken

    // use a max priority queue based on duration of each course

 */