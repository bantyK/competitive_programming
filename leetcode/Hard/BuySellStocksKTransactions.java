//188 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
public class BuySellStocksKTransactions {
    public static void main(String[] args) {
        BuySellStocksKTransactions obj = new BuySellStocksKTransactions();
        System.out.println(obj.maxProfit2(2, new int[]{3, 2, 6, 5, 0, 3}));
        System.out.println(obj.maxProfit2(2, new int[]{5, 11, 3, 50, 60, 90}));
    }

    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;

        if (k >= len / 2) return quickSolve(prices);

        int[][] profits = new int[k + 1][len];

        for (int t = 1; t <= k; t++) {
            for (int d = 1; d < len; d++) {
                int max = 0;

                // calculating the maximum value that we can get if make 1 transaction at day 'd'
                // and selling a stock at the price of day d, prices[d]
                for (int i = 0; i <= d; i++) {
                    max = Math.max(max, prices[d] + profits[t - 1][i] - prices[i]);
                }

                // When we take profits[t][d-1], we assume that we are not doing at any transaction at day d
                profits[t][d] = Math.max(profits[t][d - 1], max);
            }
        }

        return profits[k][len - 1];
    }

    public int maxProfit2(int k, int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;

        if (k >= len / 2) return quickSolve(prices);


        int[][] profits = new int[k + 1][len];

        for (int t = 1; t <= k; t++) {
            int maxThusFar = Integer.MIN_VALUE;

            //profits[t - 1][d - 1] -> max profit that we can get before current day and before making
            // a transaction
            for (int d = 1; d < len; d++) {
                maxThusFar = Math.max(maxThusFar, profits[t - 1][d - 1] - prices[d - 1]);
                profits[t][d] = Math.max(profits[t][d - 1], maxThusFar + prices[d]);
            }
        }

        return profits[k][len - 1];
    }

    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
}