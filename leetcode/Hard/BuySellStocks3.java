//123 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
public class BuySellStocks3 {
    public static void main(String[] args) {
        BuySellStocks3 obj = new BuySellStocks3();
        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(obj.maxProfit2(prices));
        System.out.println(obj.maxProfit2(new int[]{1, 2, 3, 4, 5}));
        System.out.println(obj.maxProfit2(new int[]{7, 6, 4, 3, 1}));
    }

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;

        // The two profits can happen between index at 0 to k(imaginary index) and between index at k + 1 and len.
        // These two profits can be stored in 2 arrays profits1 and profits2
        // profits1 will indicate the profit from left to right, meaning from 0 to k.
        // and profits2 will go from right to left and indicate a profit from k+1 to n.
        int[] profits1 = new int[len];
        int[] profits2 = new int[len];

        int minPrice = prices[0];

        profits1[0] = 0;
        for (int i = 1; i < len; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            profits1[i] = Math.max(profits1[i - 1], prices[i] - minPrice);
        }

//        System.out.println(Arrays.toString(profits1));

        int maxSellPrice = prices[len - 1];
        profits2[len - 1] = 0;
        for (int i = len - 2; i >= 0; i--) {
            maxSellPrice = Math.max(maxSellPrice, prices[i]);
            profits2[i] = Math.max(profits2[i + 1], maxSellPrice - prices[i]);
        }

//        System.out.println(Arrays.toString(profits2));

        int maxProfit = 0;
        for (int i = 0; i < len; i++) {
            maxProfit = Math.max(maxProfit, profits1[i] + profits2[i]);
        }

        return maxProfit;
    }

    // Shorter version of version 1, instead of 2 loops using only 1
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;

        int[] profits1 = new int[len];
        int[] profits2 = new int[len];

        int minPrice = prices[0];
        profits1[0] = 0;
        int maxSellPrice = prices[len - 1];
        profits2[len - 1] = 0;

        for (int i = 1; i < len; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            profits1[i] = Math.max(profits1[i - 1], prices[i] - minPrice);

            int j = len - 1 - i;
            maxSellPrice = Math.max(maxSellPrice, prices[j]);
            profits2[j] = Math.max(profits2[j + 1], maxSellPrice - prices[j]);
        }

        int maxProfit = 0;
        for (int i = 0; i < len; i++) {
            maxProfit = Math.max(maxProfit, profits1[i] + profits2[i]);
        }

        return maxProfit;
    }
}
