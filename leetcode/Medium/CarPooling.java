import java.util.*;

//1094 https://leetcode.com/problems/car-pooling/
public class CarPooling {
    public static void main(String[] args) {
        CarPooling obj = new CarPooling();
        System.out.println(obj.carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4));
        System.out.println(obj.carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 5));
        System.out.println(obj.carPooling(new int[][]{{2, 1, 5}, {3, 5, 7}}, 3));
        System.out.println(obj.carPooling(new int[][]{{3, 2, 7}, {3, 7, 9}, {9, 3, 9}}, 12));
        System.out.println(obj.carPooling(new int[][]{{3, 2, 8}, {4, 4, 6}, {10, 8, 9}}, 11));
    }

    public boolean carPooling(int[][] trips, int remainingCapacity) {
        Arrays.sort(trips, (a, b) -> {
            if (a[1] == b[1]) return a[2] - b[2];
            else return a[1] - b[1];
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        for (int[] trip : trips) {
            if (pq.isEmpty()) {
                if (remainingCapacity < trip[0]) return false;
                remainingCapacity -= trip[0];
                pq.offer(trip);
            } else {
                // if the end time of currently running trip is more than the start time of next trip
                int nextTripStartTime = trip[1];
                while (!pq.isEmpty() && nextTripStartTime >= pq.peek()[2]) {
                    int[] latestEndingTrip = pq.poll();
                    remainingCapacity += latestEndingTrip[0];
                }

                if (remainingCapacity < trip[0]) return false;
                pq.offer(trip);
                remainingCapacity -= trip[0];
            }
        }

        return true;
    }
}
