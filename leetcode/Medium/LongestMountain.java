//845: https://leetcode.com/problems/longest-mountain-in-array/
public class LongestMountain {
    public static void main(String[] args) {
        LongestMountain obj = new LongestMountain();
        System.out.println(obj.longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
        System.out.println(obj.longestMountain(new int[]{0, 1, 2, 3, 2, 3, 4, 5, 6, 5, 4}));
    }

    public int longestMountain(int[] A) {
        int len = A.length;
        if (len == 0) return 0;
        int longest = 0;
        for (int i = 1; i < len - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                // this is a valid peak
                int j = i;
                while (j > 0 && A[j] > A[j - 1]) {
                    j--;
                }
                int k = i + 1;
                while (k < len && A[k] < A[k - 1]) {
                    k++;
                }
                int length = k - j;
                longest = Math.max(length, longest);
            }
        }
        return longest;
    }
}
