// 714 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
public class StockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2]; // we end with a stock or we dont have a stock at the end of a day
        
        // dp[i][0] => max profit at the end of day i, if I end up with no stock
        // dp[i][1] => max profit at the end of day i, if I end up with 1 stock
        
        dp[0][0] = 0; // can't sell a stock at day 0
        dp[0][1] = -prices[0] - fee; // we have a stock at day 0, this means that we have bought it today,
        // so the total profit that we have is (-) * price at day 0
        
        for(int i = 1; i < n; i++) {
            // I end up with no stock at the end of this day
            // I had no stock before and I didn't buy any
            // I bought a stock before and now I am selling that stock -> the profit I made after buying that stock + the price that I will be selling on today - transaction
            dp[i][0] = Math.max(dp[i-1][0], dp[i - 1][1] + prices[i]);
                
            // I end up with 1 stock at the end of this day
            // I had one stock before and I didn't sell it today
            // I sold a stock before and now I am buying a new stock -> the profit I made after selling that stock - the price that I will be buying on today - transaction
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        
        return dp[n - 1][0]; // maximum will always be the value when we have no stock at the end of last day    
    }

}
