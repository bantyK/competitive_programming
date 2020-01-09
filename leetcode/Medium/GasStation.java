import java.util.*;

// 134 https://leetcode.com/problems/gas-station/
public class GasStation {
    public static void main(String[] args) {
        GasStation obj = new GasStation();
        int[] gas = new int[]{2, 3, 4};
        int[] cost = new int[]{3, 4, 3};

        System.out.println(obj.canCompleteCircuit(gas, cost));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        int diff = 0;
        int n = gas.length;
        int start = 0;
        for (int i = 0; i < n; ++i) {
            sum += gas[i] - cost[i];
            if (sum < 0) {
                diff += sum;
                sum = 0;
                start = i + 1;
            }
        }
        diff += sum;
        return diff < 0 ? -1 : start;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        int startIndex = 0;

        for (startIndex = 0; startIndex < gas.length; startIndex++) {
            boolean completed = true;
            if (gas[startIndex] >= cost[startIndex]) {
                int j = (startIndex + 1) % n;
                int gasLeft = gas[startIndex] - cost[startIndex];
                while (j != startIndex) {
                    if (gasLeft + gas[j] < cost[j]) {
                        completed = false;
                        break;
                    }
                    gasLeft += gas[j];
                    gasLeft -= cost[j];
                    j = (j + 1) % n;
                }

                if (completed) {
                    return startIndex;
                }
            }

        }
        return -1;
    }
}
