import java.util.*;

//975 https://leetcode.com/problems/odd-even-jump/
public class EvenOddJump {
    public static void main(String[] args) {

    }

    public int oddEvenJumps(int[] A) {
        int n = A.length;

        // We need to compare the values of the given array. In case of odd jump, we need to go to a higher value
        // and in case of odd jump, we need to go into the lower value.

        // Hence, the key of the tree map will be the values of the given array.

        // We need to put the values in higer and lower boolean dp arrays, for that we need the index, which
        // is given by the value of the Map Entry.

        TreeMap<Integer, Integer> map = new TreeMap<>();

        boolean[] higher = new boolean[n];
        boolean[] lower = new boolean[n];

        // End index is reachable from itself
        higher[n - 1] = true;
        lower[n - 1] = true;

        map.put(A[n - 1], n - 1);

        int res = 1; // the last index is always reachable, so answer will be at least equal to 1

        for (int i = n - 2; i >= 0; --i) {
            Map.Entry<Integer, Integer> higherEntry = map.ceilingEntry(A[i]);
            Map.Entry<Integer, Integer> lowerEntry = map.floorEntry(A[i]);

            if (higherEntry != null) {
                // from higher, we need to go to the lower index.
                higher[i] = lower[higherEntry.getValue()];
            }

            if (lowerEntry != null) {
                // from lower, we will go into the higher index
                lower[i] = higher[lowerEntry.getValue()];
            }

            map.put(A[i], i);

            // we only need to consider the higher array, because the first jump is always going to be
            // an odd jump which means taking a higher jump
            if (higher[i]) res++;
        }

        return res;

    }
}