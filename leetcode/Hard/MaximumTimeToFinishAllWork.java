import java.util.*;

//1723 https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/
public class MaximumTimeToFinishAllWork {

    public static void main(String[] args) {
        MaximumTimeToFinishAllWork obj = new MaximumTimeToFinishAllWork();
        System.out.println(obj.minimumTimeRequired(new int[]{3, 2, 3}, 3));
        System.out.println(obj.minimumTimeRequired(new int[]{1, 2, 4, 7, 8}, 2));
    }


    int min;

    public int minimumTimeRequired(int[] jobs, int k) {
        min = Integer.MAX_VALUE;
        int[] workers = new int[k];
        helper(jobs, 0, 0, workers);
        return min;
    }

    private void helper(int[] jobs, int jobIndex, int currentMax, int[] workers) {
        if (jobIndex == jobs.length) {
            min = Math.min(min, currentMax);
            return;
        }

        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < workers.length; i++) {
            if (seen.contains(workers[i])) {
                continue;
            }

            if (workers[i] + jobs[jobIndex] >= this.min) {
                continue; // pruning,
                // if we continue along this path, we are going to get greater values than our current global min value
                // which is useless since we need the minimum value
            }
            seen.add(workers[i]);
            workers[i] += jobs[jobIndex];
            helper(jobs, jobIndex + 1, Math.max(currentMax, workers[i]), workers);
            workers[i] -= jobs[jobIndex];
        }
    }

    // returns the maximum of workers
    private int getMax(int[] workers) {
        int max = Integer.MIN_VALUE;
        for (int worker : workers) {
            max = Math.max(worker, max);
        }
        return max;
    }
}
