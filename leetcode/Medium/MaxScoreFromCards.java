import java.util.Arrays;

// 1423 https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
public class MaxScoreFromCards {

    public static void main(String[] args) {
        MaxScoreFromCards obj = new MaxScoreFromCards();
//        int[] nums = new int[]{1,79,80,1,1,1,200,1};
//        System.out.println(obj.maxScore(nums, 3));

        System.out.println(obj.maxScore(new int[]{96, 90, 41, 82, 39, 74, 64, 50, 30}, 8));
    }

    // DP solution
    public int maxScoreDP(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] left = new int[k + 1];
        int[] right = new int[k + 1];
        int len = cardPoints.length;

        for (int i = 1; i <= k; i++) {
            left[i] = left[i - 1] + cardPoints[i - 1];
            right[i] = right[i - 1] + cardPoints[len - i];
        }

        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));

        int maxScore = 0;

        for (int i = 0; i <= k; i++) {
            // here i means taking i cards from left and (k - i) cards from right
            // i = 0 -> 0 card from left and k cards from right
            // i = 1 -> 1 card from left and k - 1 card from right etc
            maxScore = Math.max(maxScore, left[i] + right[k - i]);
        }

        return maxScore;
    }

    // Sliding window solution
    public int maxScore(int[] cardPoints, int k) {
        int totalSum = 0;
        int windowSum = 0;
        int n = cardPoints.length;
        for (int i = 0; i < n; i++) {
            totalSum += cardPoints[i];
            if (i < n - k) {
                windowSum += cardPoints[i];
            }
        }
        if (k == n) return totalSum;

        int maxScore = 0;
        int left = 0;
        int right = n - k;

        for (; right < n; right++) {
            maxScore = Math.max(maxScore, totalSum - windowSum);
            windowSum -= cardPoints[left++];
            windowSum += cardPoints[right];
        }
        maxScore = Math.max(maxScore, totalSum - windowSum);
        return maxScore;
    }


}