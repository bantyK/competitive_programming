// 1575 https://leetcode.com/problems/count-all-possible-routes/
public class AllPossibleRoutes {
    public static final int MOD = (int) (Math.pow(10, 9) + 7);

    public static void main(String[] args) {
        AllPossibleRoutes obj = new AllPossibleRoutes();
        System.out.println(obj.countRoutes(new int[]{2, 3, 6, 8, 4}, 1, 3, 5));
        System.out.println(obj.countRoutes(new int[]{1, 2, 3}, 0, 2, 40));
    }

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        Integer[][] dp = new Integer[locations.length][fuel + 1];
        long ans = solve(locations, start, finish, fuel, dp);
        return (int) (ans % MOD);
    }

    private long solve(int[] locations, int currentCity, int finish, int remainingFuel, Integer[][] dp) {
        if (remainingFuel < 0) {
            // If the fuel is 0 then we cannot move any further,
            return 0;
        }

        if (dp[currentCity][remainingFuel] != null) return dp[currentCity][remainingFuel];

        // IMPORTANT POINT: Don't end the recursion when we reach the finish city //
        // If the current city is the end city then record this as one of the answer but do not
        // end the recursion, because there could be more valid paths which passes through the end city and
        // reaches the end city again. End city and start city can be visited repeatedly

        long ans = currentCity == finish ? 1 : 0;

        for (int nextCity = 0; nextCity < locations.length; nextCity++) {
            // Go through each and every city (except the city where we are right now)
            // and explore the possible routes
            if (nextCity != currentCity) {
                int fuelRequired = Math.abs(locations[currentCity] - locations[nextCity]);
                if (remainingFuel - fuelRequired >= 0) {
                    ans += (solve(locations, nextCity, finish, remainingFuel - fuelRequired, dp) % MOD);
                }
            }
        }

        return dp[currentCity][remainingFuel] = (int) (ans % MOD);
    }

}