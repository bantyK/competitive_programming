//1344  https://leetcode.com/problems/angle-between-hands-of-a-clock/
public class ClockHandAngle {
    public double angleClock(int hour, int minutes) {
        if (hour == 12) hour = 0;
        double hourHandAngle = 30 * hour;
        hourHandAngle += (minutes * 0.5);

        double minutesHandAngle = minutes * 6;

        double res = Math.abs(hourHandAngle - minutesHandAngle);

        return res >= 180 ? 360 - res : res;

    }
}
