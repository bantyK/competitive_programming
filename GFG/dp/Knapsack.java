public class Knapsack {
    public static void main(String[] args) {
        Knapsack obj = new Knapsack();
        int[] profits = new int[]{1, 6, 10, 16};
        int[] weights = new int[]{1, 2, 3, 5};
        int maxCapacity = 7;
        int maxProfit = solveKnapsack(profits, weights, maxCapacity);
        System.out.println(maxProfit);
    }

    private static int solveKnapsackBottomUp(int[] profits, int[] weights, int maxCapacity) {
        int n = profits.length;
        int[][] dp = new int[n][maxCapacity + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }

        for (int capacity = 0; capacity <= maxCapacity; capacity++) {
            if (weights[0] <= capacity)
                dp[0][capacity] = profits[0];
        }

        for (int i = 1; i < n; i++) {
            for (int c = 1; c <= maxCapacity; c++) {
                int profit1 = 0, profit2 = 0;
                if (weights[i] <= c) {
                    // choose the item at index i
                    profit1 = profits[i] + dp[i - 1][c - weights[i]];
                }
                // don't choose the item at index i
                profit2 = dp[i - 1][c];

                dp[i][c] = Math.max(profit1, profit2);
            }
        }

        return dp[n - 1][maxCapacity];

    }

    // Top-Bottom approach
    private static int solveKnapsackDP(int[] profits, int[] weights, int maxCapacity) {
        Integer[][] dp = new Integer[profits.length][maxCapacity + 1];
        return recurseDP(profits, weights, maxCapacity, 0, dp);
    }

    private static int recurseDP(int[] profits, int[] weights, int capacity, int index, Integer[][] dp) {
        if (capacity <= 0 || index >= profits.length) {
            return 0;
        }

        if (dp[index][capacity] != null) {
            return dp[index][capacity];
        }

        // this is the profit that we get when we select the element at index = currentIndex
        int profitWhenElementSelected = 0;
        if (weights[index] <= capacity) {
            profitWhenElementSelected = profits[index] + recurseDP(profits, weights, capacity - weights[index], index + 1, dp);
        }

        // this is the profit when we don't select the element at index = currentIndex
        int profitWhenElementNotSelected = recurseDP(profits, weights, capacity, index + 1, dp);

        int max = Math.max(profitWhenElementSelected, profitWhenElementNotSelected);
        dp[index][capacity] = index;
        return dp[index][capacity];
    }


    private static int solveKnapsack(int[] profits, int[] weights, int maxCapacity) {
        return recurse(profits, weights, maxCapacity, 0);
    }

    private static int recurse(int[] profits, int[] weights, int capacity, int currentIndex) {
        if (capacity <= 0 || currentIndex >= profits.length) {
            return 0;
        }

        // this is the profit that we get when we select the element at index = currentIndex
        int profitWhenElementSelected = 0;
        if (weights[currentIndex] <= capacity) {
            profitWhenElementSelected = profits[currentIndex] + recurse(profits, weights, capacity - weights[currentIndex], currentIndex + 1);
        }

        // this is the profit when we don't select the element at index = currentIndex
        int profitWhenElementNotSelected = recurse(profits, weights, capacity, currentIndex + 1);

        return Math.max(profitWhenElementSelected, profitWhenElementNotSelected);
    }


}
