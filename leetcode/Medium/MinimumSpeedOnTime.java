// 1870 https://leetcode.com/problems/minimum-speed-to-arrive-on-time/
class MinimumSpeedOnTime {
    public int minSpeedOnTime(int[] dist, double hour) {
        int low = 1;
        int high = (int) 1e9;

        int bestTime = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (valid(dist, mid, hour)) {
                bestTime = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return bestTime == Integer.MAX_VALUE ? -1 : bestTime;
    }

    private boolean valid(int[] dist, int mid, double hour) {
        double time = 0;
        for (int i = 0; i < dist.length; i++) {
            double t = (dist[i] * 1.0) / mid;
            if (i < dist.length - 1)
                time += Math.ceil(t);
            else
                time += t;
        }
        return Double.compare(time, hour) <= 0;
    }
}
