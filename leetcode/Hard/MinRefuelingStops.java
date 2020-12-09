import input.TwoDArrayReader;

import java.util.PriorityQueue;

// 871 https://leetcode.com/problems/minimum-number-of-refueling-stops/
// DP and  Heap solution
public class MinRefuelingStops {

    public static void main(String[] args) {
        MinRefuelingStops obj = new MinRefuelingStops();
        int[][] stations = new TwoDArrayReader().get2DArray();
        System.out.println(obj.minRefuelStops(100, 10, new int[][]{{10, 5}, {50, 100}}));
    }

    // DP
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        // {distance, capacity}
        // all given distances are from the starting point i.e., i = 0;

        // dp[i] represent the farthest we can get with i refueling stations
        // we are noting the farthest distance, because we want minimum refueling station count

        long[] dp = new long[n + 1];
        // with no refueling station, we can only get up to the distance equal to the start fuel
        dp[0] = startFuel;

        for (int station = 0; station < n; station++) {
            for (int t = station; t >= 0; t--) {
                if (dp[t] >= stations[station][0]) {
                    // dp[t] is the maximum distance that we can reach if we fill the fuel tank at ith fuel station.

                    // if we do not have enough fuel to reach the fuel station, then we shouldn't compute dp[t+1] for that station
                    // consider ex: [[10,5],[50,100]] startFuel = 10
                    // we can reach the first station with startFuel but we can't reach the second station

                    // if this condition is true it means that we can reach until station number 'station (which is the loop index)'
                    dp[t + 1] = Math.max(dp[t + 1], dp[t] + (long) stations[station][1]);
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            if (dp[i] >= target) {
                // i is the minimum count of refueling station for which the distance that we can
                // cover is greater than or equal to the target
                return i;
            }
        }

        // not possible to target with the given amount of fuel
        return -1;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////Solution Using Heap////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    public int minRefuelStopsHeap(int target, int startFuel, int[][] stations) {
        // The idea is not to refuel when we reach a fuel station if our tank is not empty
        // rather remember the highest amount of fuel that we can fill and retrospectively
        // fill the greatest amount when we run out of fuel

        // When we run out of fuel before reaching the next station, we'll retroactively
        // fuel up: greedily choosing the largest gas stations first.

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int numRefuel = 0;
        int fuelAmount = startFuel;
        int stationIndex = 0;
        while(true) {
            while(stationIndex < stations.length && fuelAmount >= stations[stationIndex][0]) {
                // we have enough fuel to reach up to this station, no need to refuel
                // simply store this value for future use
                maxHeap.offer(stations[stationIndex][1]);
                stationIndex++;
            }

            // at this point, we do not have enough fuel to reach to the next station
            if(fuelAmount >= target) {
                // we have enough fuel to reach upto target, no need of any more addition, simply return
                return numRefuel;
            }

            // here we dont have enough fuel to reach target
            if(maxHeap.isEmpty()) {
                // we dont have anymore fuel also to fill the tank. Hence not possible to reach target
                return -1;
            }

            fuelAmount += maxHeap.poll(); // fill the maximum amount of fuel that we can fill
            numRefuel++;
        }
    }
}