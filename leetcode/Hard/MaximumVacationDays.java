//568 https://leetcode.com/problems/maximum-vacation-days/
public class MaximumVacationDays {

    public static void main(String[] args) {
        MaximumVacationDays obj = new MaximumVacationDays();
        System.out.println(obj.maxVacationDays(
                new int[][]{{0, 0, 1}, {1, 0, 1}, {1, 1, 0}},
                new int[][]{{6, 0, 3}, {7, 7, 7}, {3, 3, 3}})
        );

        System.out.println(obj.maxVacationDays(
                new int[][]{{0, 1, 1}, {1, 0, 1}, {1, 1, 0}},
                new int[][]{{1, 3, 1}, {6, 0, 3}, {3, 3, 1}})
        );

        System.out.println(obj.maxVacationDays(
                new int[][]{{0, 1, 1}, {1, 0, 1}, {1, 1, 0}},
                new int[][]{{7, 7, 7}, {6, 0, 3}, {3, 3, 1}})
        );

//        [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
    }

    public int maxVacationDays(int[][] flights, int[][] days) {
        // read the question and solve it in paper
        int numFlights = flights.length;
        int numWeek = days[0].length;

        Integer[][] cache = new Integer[numFlights][numWeek];
        return helper(flights, days, 0, 0, cache);
    }

    private int helper(int[][] flights, int[][] days, int week, int city, Integer[][] cache) {
        if (city >= flights.length || week >= days[0].length) {
            return 0;
        }

        if (cache[city][week] != null) {
            // if we have already calculated this result, return it without doing any calculation
            return cache[city][week];
        }

        /* we have 2 options:
            1. Either stay at the same city. Add the vacation days of the current city in the current week

            2. Second option is, move to the cities wherever it is possible to move and add that city's vacation days.
                Move to all the cities like this and the city which is giving the maximum vacation day is selected.
         */


        // Here we are staying in the same city.
        // took the vacation day for that week, because we decided to stay there, and recurse on the rest of the week
        // since we decided to stay at the same city, the city value will not change
        int vacationDaysIfStayAtCurrentCity = days[city][week] + helper(flights, days, week + 1, city, cache);

        int vacationDaysIfDonotStayAtCurrentCity = 0;
        for (int i = 0; i < flights.length; i++) {
            // move to all the cities where we can move.
            if (flights[city][i] == 1) {
                // spend that week in that city, so take its vacation day. Since we moved to the city i, the city variable is changed accordingly
                // because we moved to that city. The rest of calculation will happen from that city
                int val = days[i][week] + helper(flights, days, week + 1, i, cache);
                // once all the paths from that city is explored, we have a certain number of vacation day from that city.
                // out of all those days, choose the one which gives the maximum value
                vacationDaysIfDonotStayAtCurrentCity = Math.max(vacationDaysIfDonotStayAtCurrentCity, val);
            }
        }
        cache[city][week] = Math.max(vacationDaysIfDonotStayAtCurrentCity, vacationDaysIfStayAtCurrentCity);
        return cache[city][week];
    }
}