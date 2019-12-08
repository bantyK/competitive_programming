//1011 https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
public class ShipCapacity {
    public static void main(String[] args) {
        ShipCapacity obj = new ShipCapacity();
        int[] weights = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int D = 5;
        System.out.println(obj.shipWithinDays(weights, D));
    }

    public int shipWithinDays(int[] weights, int D) {
        // the minimum weight that the ship must have to carry the package. This will come if the number of days are unlimited.
        // The ship have to carry the weight of the max element.
        int low = max(weights);
        // the maximum weight that the ship has to carry will be when the ship has to carry all the items on a single day.
        int high = sum(weights);

        // The answer will be between this low and high. This is a sorted list so we can do a binary search.
        while (low <= high) {
            int mid = (low + high) / 2;
            // Lets us assume that mid is the minimum weight.
            // Calculate the minimum number of days required if we consider mid as minimum weight
            boolean valid = checkValid(weights, mid, D);
            // if the assumption is valid, then we need to check with a value lower than mid
            if (valid) {
                high = mid - 1;
            } else {
                // if the assumption is not valid, then we need to check with a value higher than mid
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean checkValid(int[] weights, int mid, int D) {
        int index = 0;
        int count = 0;

        while (index < weights.length) {
            int currentShipmentWeight = 0;

            while (index < weights.length && currentShipmentWeight + weights[index] <= mid) {
                currentShipmentWeight += weights[index];
                index += 1;
            }
            count++;
            if (count > D) return false;
        }
        return count > D;
    }

    private int max(int[] weights) {
        int maxElem = Integer.MIN_VALUE;
        for (int i : weights) {
            maxElem = Math.max(maxElem, i);
        }
        return maxElem;
    }

    private int sum(int[] weights) {
        int total = 0;
        for (int w : weights) total += w;
        return total;
    }
}


