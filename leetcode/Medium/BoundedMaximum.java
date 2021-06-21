// 795 https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
public class BoundedMaximum {

    public static void main(String[] args) {
        BoundedMaximum obj = new BoundedMaximum();
        System.out.println(obj.numSubarrayBoundedMax(new int[]{2, 9, 2, 5, 6}, 2, 8));
        System.out.println(obj.numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3));
        System.out.println(obj.numSubarrayBoundedMax(new int[]{16, 69, 88, 85, 79, 87, 37, 33, 39, 34}, 55, 57));
    }

    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int start = -1, end = -1;
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            if (L <= A[i] && A[i] <= R) {
                end = i;
                res += (end - start);
            } else if (A[i] < L) {
                res += (end - start);
            } else if (R < A[i]) {
                start = i;
                end = i;
            }
        }

        return res;
    }
}