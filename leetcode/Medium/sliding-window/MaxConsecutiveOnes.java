package sliding;

// 1004 https://leetcode.com/problems/max-consecutive-ones-iii/
public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        MaxConsecutiveOnes obj = new MaxConsecutiveOnes();

        int[] a = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k = 3;
        int len = obj.longestOnes(a, k);
        System.out.println(len);

        a = new int[]{0, 0, 0, 1};
        k = 4;
        len = obj.longestOnes(a, k);
        System.out.println(len);

        a = new int[]{0, 0, 0, 1};
        k = 1;
        len = obj.longestOnes(a, k);
        System.out.println(len);
    }

    public int longestOnes(int[] a, int k) {
        int count = 0;
        int start = 0;
        int maxLen = 0;
        for (int end = 0; end < a.length; end++) {
            if (a[end] == 0) {
                count++;
            }

            while (count > k) {
                if (a[start] == 0) {
                    count--;
                }
                start++;
            }

            maxLen = Math.max(end - start + 1, maxLen);
        }
        return maxLen;
    }
}