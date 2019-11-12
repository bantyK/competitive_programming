package solutions.medium;

import solutions.util.ArrayUtils;

//1109  https://leetcode.com/problems/corporate-flight-bookings/
import java.util.*;

public class CorporateFlight {
    public static void main(String[] args) {
        CorporateFlight obj = new CorporateFlight();
        int[][] bookings = new int[][]{
                {1, 2, 10},
                {2, 6, 10},
                {2, 3, 20},
                {2, 5, 25},
        };

        ArrayUtils.printArray(obj.corpFlightBookings(bookings, 6));
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        Map<Integer, Integer> bookingCount = new TreeMap<>();

        int result[] = new int[n];

        for (int[] booking : bookings) {
            for (int i = booking[0]; i <= booking[1]; i++) {
                result[i - 1] += booking[2];
            }
        }

        return result;
    }


    // A better solution.
    public int[] corpFlightBookingsBetter(int[][] bookings, int n) {
        Map<Integer, Integer> bookingCount = new TreeMap<>();
        int result[] = new int[n];

        for (int[] booking : bookings) {
            result[booking[0] - 1] += booking[2];
            if (booking[1] < n) result[booking[1]] -= booking[2];
        }

        for (int i = 1; i < n; ++i) result[i] += result[i - 1];

        return result;
    }
}
