// 1482 https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
public class MinDaysToMakeBouquets {
    public static void main(String[] args) {
        MinDaysToMakeBouquets obj = new MinDaysToMakeBouquets();
        System.out.println(obj.minDays(new int[]{1, 10, 2, 9, 3, 8, 4, 7, 5, 6}, 4, 2));
        System.out.println(obj.minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3));
        System.out.println(obj.minDays(new int[]{1000000000, 1000000000}, 1, 1));
        System.out.println(obj.minDays(new int[]{1, 10, 3, 10, 2}, 3, 2));
        System.out.println(obj.minDays(new int[]{1, 10, 3, 10, 2}, 3, 1));
    }

    /**
     * @param bloomDay
     * @param m        number of bouquets to make
     * @param k        number of adjacent flowers required
     * @return min days required
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int len = bloomDay.length;
        if (m * k > len) {
            return -1; // not enough flowers to make m bouquets
        }
        // answer will lie between these values
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int day : bloomDay) {
            min = Math.min(day, min);
            max = Math.max(day, max);
        }
        int minDays = max;

        while (min <= max) {
            int mid = min + (max - min) / 2;
            boolean canMakeMBouquets = canMakeBouquets(bloomDay, m, k, mid);
            if (canMakeMBouquets) {
                // try to minimise this
                minDays = mid;
                max = mid - 1;
            } else {
                // not possible to make required number of bouquets, increase the number of days
                min = mid + 1;
            }
        }

        return minDays;
    }

    private boolean canMakeBouquets(int[] bloomDay, int m, int k, int mid) {
        int numBouquets = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] > mid) continue;
            boolean canMake = true;
            int flowersUsed = 0;
            int j = i;
            while (j < bloomDay.length && j < i + k) {
                if (bloomDay[j] > mid) {
                    canMake = false;
                    break;
                }
                j++;
                flowersUsed++;
            }
            if (canMake && flowersUsed == k) {
                numBouquets++;
                if (numBouquets == m) return true;
            }
            i = j - 1;
        }

        return false;
    }
}
