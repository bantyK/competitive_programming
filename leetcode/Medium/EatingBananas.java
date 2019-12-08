//875 https://leetcode.com/problems/koko-eating-bananas/
public class EatingBananas {
    public static void main(String[] args) {
        EatingBananas obj = new EatingBananas();
        int[] piles = new int[]{332484035, 524908576, 855865114, 632922376, 222257295, 690155293, 112677673, 679580077, 337406589, 290818316, 877337160, 901728858, 679284947, 688210097, 692137887, 718203285, 629455728, 941802184};
        int H = 823855818;
        System.out.println(obj.minEatingSpeed(piles, H));
    }

    public int minEatingSpeed(int[] piles, int H) {
        int low = 1;
        int high = findMax(piles);

        while (low < high) {
            int mid = (high - low) / 2 + low;
            int i = 0;
            int day = 0;
            while (i < piles.length) {
                day += piles[i] / mid;
                if (piles[i] % mid != 0) {
                    day += 1;
                }
                i++;
            }

            if (day < H) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private int findMax(int[] piles) {
        int max = Integer.MIN_VALUE;
        for (int i : piles) {
            max = Math.max(max, i);
        }

        return max;
    }
}
