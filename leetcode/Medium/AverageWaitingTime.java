// 1701 https://leetcode.com/problems/average-waiting-time/
public class AverageWaitingTime {

    public static void main(String[] args) {
        AverageWaitingTime obj = new AverageWaitingTime();
        int[][] customers = {{5, 2}, {5, 4}, {10, 3}, {20, 1}};
        System.out.println(obj.averageWaitingTime(customers));

        customers = new int[][]{{1, 2}, {2, 5}, {4, 3}};
        System.out.println(obj.averageWaitingTime(customers));

    }

    public double averageWaitingTime(int[][] customers) {
        int numCustomers = customers.length;
        if (numCustomers == 0) return 0;

        int customerIndex = 0;
        int lastEnd = Integer.MIN_VALUE;
        double totalWaitingTime = 0;

        while (customerIndex < numCustomers) {
            int start = Math.max(lastEnd, customers[customerIndex][0]);
            int end = start + customers[customerIndex][1];
            totalWaitingTime += end - customers[customerIndex][0];
            customerIndex++;
            lastEnd = end;
        }

        return totalWaitingTime / numCustomers;
    }
}