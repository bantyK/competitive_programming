import java.util.*;

//1552 https://leetcode.com/problems/magnetic-force-between-two-balls/
public class MaximizeMagneticForce {
    public static void main(String[] args) {
        MaximizeMagneticForce obj = new MaximizeMagneticForce();
        System.out.println(obj.maxDistance(new int[]{1, 2, 3, 4, 7}, 3));
    }

    public int maxDistance(int[] position, int m) {
        // Array is sorted so that we can place balls at increasing distance in the inValid method below.
        Arrays.sort(position);
        int low = 1;
        int high = position[position.length - 1];
        int optimal = 0;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (isValid(position, m, mid)) {
                optimal = mid;
                // this value is valid. So this could be a potential answer. Save it and look for a value greater than this
                // because we need to maximize the value of optimal
                low = mid + 1;
            } else {
                // this value is too large, reduce the distance
                high = mid - 1;
            }
        }
        return optimal;
    }

    /** This method emulates the process of placing m balls with @param "distance" distance apart
     *  If it can, that means it is a valid position, not necessarily the optimal one though. but it returns true.
     * @param position
     * @param numBalls
     * @param distance
     * @return
     */
    private boolean isValid(int[] position, int numBalls, int distance) {
        int placed = 1;
        int last = position[0];

        for (int i = 0; i < position.length; i++) {
            if (position[i] - last >= distance) {
                last = position[i];
                placed++;
                if (placed == numBalls) return true;
            }
        }
        return false;
    }
}
