import java.util.*;

//465 https://leetcode.com/problems/optimal-account-balancing/
public class OptimalAccountBalancing {
    public static void main(String[] args) {
        OptimalAccountBalancing obj = new OptimalAccountBalancing();
        System.out.println(obj.minTransfers(new int[][]{{0, 1, 10}, {2, 0, 5}}));
    }

    public int minTransfers(int[][] transactions) {
        if (transactions == null || transactions.length == 0) {
            return 0;
        }

        Map<Integer, Integer> balanceSheet = new HashMap<>();

        for (int[] transaction : transactions) {
            int senderId = transaction[0];
            int receiverId = transaction[1];
            int amount = transaction[2];

            balanceSheet.put(senderId, balanceSheet.getOrDefault(senderId, 0) - amount);
            balanceSheet.put(receiverId, balanceSheet.getOrDefault(receiverId, 0) + amount);
        }

        // After this step, we have the person with the amount of debt that needs to be balanced.
        //Person with 0 balance are already balanced and they do not concern us

        // For the people with non-zero balance, we perform a DFS
        // we only need the balance part. The personId are of no use because we need just the number of transactions required


        int[] balance = new int[balanceSheet.size()];
        int i = 0;
        for (int value : balanceSheet.values()) {
            balance[i++] = value;
        }
        return DFS(balance, 0);
    }

    // The balance with opposite signs can be settled. If the balance are both positive
    // or both negative than they cannot settle each other.
    private int DFS(int[] balance, int start) {
        while (start < balance.length && balance[start] == 0) {
            start++;
        }

        if (start == balance.length) {
            return 0;
        }


        int steps = Integer.MAX_VALUE;

        // balancing the balance at index = start
        for (int i = start + 1; i < balance.length; i++) {
            if (balance[start] * balance[i] < 0) {
                balance[i] = balance[i] + balance[start];
                steps = Math.min(steps, 1 + DFS(balance, start + 1));
                // backtracking
                balance[i] = balance[i] - balance[start];
            }
        }

        return steps;
    }
}