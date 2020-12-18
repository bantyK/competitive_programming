import java.util.*;

public class LIS {
    public static void main(String[] args) {
       System.out.println(new LIS().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
       System.out.println(new LIS().lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
       System.out.println(new LIS().lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));
       System.out.println(new LIS().lengthOfLIS(new int[]{2, 5, 3, 7, 11, 8, 10, 13, 6}));
        System.out.println(new LIS().lengthOfLIS2(new int[]{10,9,2,5,3,7,101,18}));
    }

    //O(n^2)
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int longest = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                    longest = Math.max(dp[i], longest);
                }
            }
        }
        return longest;
    }

    //O(nlog(n))
    // Patience sort
    // https://www.youtube.com/watch?v=TocJOW6vx_I
    // https://www.youtube.com/watch?v=22s1xxRvy28
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        List<Integer> buckets = new ArrayList<>();
        buckets.add(nums[0]);

        for (int i = 1; i < n; i++) {
            if (nums[i] > buckets.get(buckets.size() - 1)) {
                buckets.add(nums[i]);
            } else {
                int index = binarySearch(buckets, nums[i]);
                buckets.set(index, nums[i]);
            }
        }
        return buckets.size();
    }

    // return the index of the smallest number which is greater than num
    private int binarySearch(List<Integer> buckets, int num) {
        int low = 0;
        int high = buckets.size() - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (buckets.get(mid) < num) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}