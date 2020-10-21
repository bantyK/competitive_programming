// 1007 https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
public class DominoRotations {
    public static void main(String[] args) {
        DominoRotations obj = new DominoRotations();

        System.out.println(obj.minDominoRotations(new int[]{2, 1, 2, 4, 2, 2}, new int[]{5, 2, 6, 2, 3, 2}));
        System.out.println(obj.minDominoRotations(new int[]{3, 5, 1, 2, 3}, new int[]{3, 6, 3, 3, 4}));
        System.out.println(obj.minDominoRotations(new int[]{1, 2, 1, 1, 1, 2, 2, 2}, new int[]{2, 1, 2, 2, 2, 2, 2, 2}));
    }

    public int minDominoRotations(int[] A, int[] B) {
        /** There are 4 possible options
         * 1. All the upper dominoes becomes equal to A0(first domino of upper row)
         * 2. All the upper dominoes becomes equal to B0(first domino of lower row)
         * 3. All the lower dominoes becomes equal to A0.
         * 4. All the lower dominoes becomes equal to B0.
         * Whichever option yields the minimum steps is the answer
         * */

        int option1 = swapsRequired(A, B, A[0]);
        int option2 = swapsRequired(A, B, B[0]);
        int option3 = swapsRequired(B, A, A[0]);
        int option4 = swapsRequired(B, A, B[0]);


        int min = Math.min(Math.min(option1, option2), Math.min(option3, option4));
        return min == Integer.MAX_VALUE ? -1 : min;

    }

    /**
     * returns the minimum number of steps required to make all items of nums1 equal to val by swapping the values with nums2
     */
    private int swapsRequired(int[] nums1, int[] nums2, int val) {
        int numSwaps = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] == val) continue;
            if (nums2[i] != val)
                return Integer.MAX_VALUE; // a large number indicating that the swapping is not possible
            numSwaps++;
        }

        return numSwaps;
    }
}
