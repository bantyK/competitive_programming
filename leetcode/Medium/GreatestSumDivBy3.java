import java.util.Arrays;

//1262 https://leetcode.com/problems/greatest-sum-divisible-by-three/
public class GreatestSumDivBy3 {

    public static void main(String[] args) {
        GreatestSumDivBy3 obj = new GreatestSumDivBy3();
        int[] nums = new int[]{366, 809, 6, 792, 822, 181, 210, 588, 344, 618, 341, 410, 121, 864, 191, 749, 637, 169, 123, 472, 358, 908, 235, 914, 322, 946, 738, 754, 908, 272, 267, 326, 587, 267, 803, 281, 586, 707, 94, 627, 724, 469, 568, 57, 103, 984, 787, 552, 14, 545, 866, 494, 263, 157, 479, 823, 835, 100, 495, 773, 729, 921, 348, 871, 91, 386, 183, 979, 716, 806, 639, 290, 612, 322, 289, 910, 484, 300, 195, 546, 499, 213, 8, 623, 490, 473, 603, 721, 793, 418, 551, 331, 598, 670, 960, 483, 154, 317, 834, 352};
        System.out.println(obj.maxSumDivThree(nums));
        System.out.println(obj.maxSumDivThree(new int[]{3, 6, 5, 1, 8}));
        System.out.println(obj.maxSumDivThree(new int[]{1, 2, 3, 4, 4}));
        System.out.println(obj.maxSumDivThree(new int[]{5, 2, 2, 2}));
        System.out.println(obj.maxSumDivThree(new int[]{2, 19, 6, 16, 5, 10, 7, 4, 11, 6}));
    }


    public int maxSumDivThree(int[] nums) {
        /* dp[i][j] -> what is the maximum sum that we can get using first i elements of nums and has remainder j
                        here we need sums divisible by 3 so j will be 0, 1, and 2

                        why do we need all 3 values ?
                        Lets say we have the number 8 at ith index. 8 % 3 = 2
                        8 is not divisible by 3 and has remainder 2. so if we add 8 to the sum which has remainder 1
                        and these 2 will make a new sum which will be divisible by 3.
                        To handle cases like this, we need all three states in our DP


            Basic idea is as follows:

            If lets say we are at index i, and nums[i] % 3 = 2, then we do following things,

            this number can be added with the dp[i-1][0] and this new sum will have remainder 2, so we will update dp[i][2]
            this number can be added with the dp[i-1][1] and this new sum will have remainder 0, so we will update dp[i][0]
            this number can be added with the dp[i-1][2] and this new sum will have remainder 1, so we will update dp[i][1]

            same logic will apply when nums[i] % 3 = 0 and 1
            if dp[i-1][j] is zero than the other two sums will be considered only if they are greater than 0
        */

        int n = nums.length;
        int[][] dp = new int[n][3];
        dp[0][nums[0] % 3] = nums[0];
        System.out.println(Arrays.toString(dp[0]));
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            int mod = num % 3;
            if (mod == 0) {
                dp[i][0] = dp[i - 1][0] + num;
                dp[i][1] = dp[i - 1][1] > 0 ? dp[i - 1][1] + num : 0;
                dp[i][2] = dp[i - 1][2] > 0 ? dp[i - 1][2] + num : 0;
            } else if (mod == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] > 0 ? dp[i - 1][2] + num : 0);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + num); // if we add this num with the sum(with mod 0) new sum will be mod 1
                dp[i][2] = dp[i - 1][1] > 0 ? dp[i - 1][1] + num : 0;
            } else {
                // mod 2
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] > 0 ? dp[i - 1][1] + num : 0);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] > 0 ? dp[i - 1][2] + num : 0);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + num);
            }

            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[n - 1][0];
    }
}