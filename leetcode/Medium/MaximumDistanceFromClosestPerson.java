import java.util.*;

//849 https://leetcode.com/problems/maximize-distance-to-closest-person/
public class MaximumDistanceFromClosestPerson {
    public static void main(String[] args) {
        MaximumDistanceFromClosestPerson obj = new MaximumDistanceFromClosestPerson();
        System.out.println(obj.maxDistance(new int[]{1, 0, 0, 1, 0, 0, 0, 1}));
    }

    public int maxDistToClosest(int[] seats) {
        int len = seats.length;

        int[] left = new int[len];
        int[] right = new int[len];

        Arrays.fill(left, len);
        Arrays.fill(right, len);

        for (int i = 0; i < len; i++) {
            if (seats[i] == 1) left[i] = 0;
            else if (i > 0) left[i] = left[i - 1] + 1;
        }

        for (int i = len - 1; i >= 0; i--) {
            if (seats[i] == 1) right[i] = 0;
            else if (i < len - 1) right[i] = right[i + 1] + 1;
        }


        int maxDistance = 0;

        for (int i = 0; i < len; i++) {
            maxDistance = Math.max(maxDistance, Math.min(left[i], right[i]));
        }

        return maxDistance;
    }

    public int maxDistance(int[] seats) {
        int N = seats.length;
        int prev = -1;
        int future = 0;
        int maxDistance = 0;

        for (int i = 0; i < N; i++) {
            if (seats[i] == 1) {
                prev = i;
            } else {
                while (future < N && seats[future] == 0 || future < i)
                    future++;

                int left = prev == -1 ? N : i - prev;
                int right = future == N ? N : future - i;

                maxDistance = Math.max(maxDistance, Math.min(left, right));
            }
        }

        return maxDistance;
    }

}
