//1335 https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
public class MinDifficultyOfJobSchedule {
    public static void main(String[] args) {

        int[] jobDifficulty = new int[]{380, 302, 102, 681, 863, 676, 243, 671, 651, 612, 162, 561, 394, 856, 601, 30, 6, 257, 921, 405, 716, 126, 158, 476, 889, 699, 668, 930, 139, 164, 641, 801, 480, 756, 797, 915, 275, 709, 161, 358, 461, 938, 914, 557, 121, 964, 315};
        int d = 10;
        System.out.println(new MinDifficultyOfJobSchedule().minDifficulty(jobDifficulty, d));
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        int len = jobDifficulty.length;
        if (d > len) {
            // there are no enough jobs available to do atleast 1 job per day
            // for cases like ex: 2
            return -1;
        }

        Integer[][] cache = new Integer[len][d + 1]; // dp cache
        return helper(jobDifficulty, len, 0, d, cache);
    }

    // i -> partition start index
    // The idea is to split the array into d parts in such a way that the sum of maximum in those parts is minimum
    // the split happens like this

    // 6, 5, 4, 3, 1
    // split1: (6), (5) (4,3,1)
    // split2: (6), (5,4), (3,1)
    // split3: (6), (5,4,3), (1)
    // split4: (6,5), (4), (3,1)
    // split5: (6,5), (4,3), (1)
    // split5: (6,5), (4,3), (1)
    // etc

    // here i basically represent the start index of the current subset we are processing

    // there are multiple repeted subsets, which we dont have to calculate again and again. Thats where the DP and the cache comes in

    private int helper(int[] jobDifficulty, int n, int i, int daysLeft, Integer[][] cache) {
        if (cache[i][daysLeft] != null) {
            return cache[i][daysLeft];
        }

        if (daysLeft == 1) {
            // there is only 1 day left. we have to complete all tasks at the same day. Hence the result will be the maximum of the job difficulties
            int max = 0;
            while (i < n) {
                max = Math.max(max, jobDifficulty[i]);
                i++;
            }
            return max;
        }

        int res = Integer.MAX_VALUE;
        int maxDifficulty = 0; // maximum difficulty of the current subset.
        // we record the maximum difficulty of a subset and then check the rest of the array, also decrement a day
        for (int j = i; j < n - daysLeft + 1; j++) {
            // we have a subset [i,j],
            // find the maximum of this subset and then recurse on the remaining array
            maxDifficulty = Math.max(maxDifficulty, jobDifficulty[j]);
            // decrement a day
            // final answer is the sum of all the maxDifficulties. hence the addition
            res = Math.min(res, maxDifficulty + helper(jobDifficulty, n, j + 1, daysLeft - 1, cache));
        }

        cache[i][daysLeft] = res;
        return cache[i][daysLeft];
    }
}