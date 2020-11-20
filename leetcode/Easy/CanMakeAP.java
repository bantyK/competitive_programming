import java.util.*;

//1502 https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
public class CanMakeAP {

    public boolean canMakeArithmeticProgression(int[] arr) {
        int len = arr.length;
        if (len == 2) return true;

        Set<Integer> nums = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : arr) {
            nums.add(num);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // rem is the common difference and it must be a whole number because the input are integers
        int rem = (max - min) % (len - 1);
        if (rem != 0) return false; // if the CD is not whole, its not a valid AP.

        int d = (max - min) / (len - 1); // d = (last term - first term) / (n - 1)

        int num = min;
        int i = 0;
        while (i++ < len) {
            if (!nums.contains(num)) {
                return false;
            }
            // consecutive terms with CD = d should be present in the sequence for it to be an AP.
            num += d;
        }

        return true;
    }
}