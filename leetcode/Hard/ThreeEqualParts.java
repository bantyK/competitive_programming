import java.util.*;

// 927 https://leetcode.com/problems/three-equal-parts/
public class ThreeEqualParts {
    public static void main(String[] args) {
        ThreeEqualParts obj = new ThreeEqualParts();
//        int[] num = new int[]{1, 0, 0, 1, 1};
        int[] nums = new int[]{1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0};
        // last   1, 0, 1, 0, 0
        // first  1, 1, 0, 0, 0
        System.out.println(Arrays.toString(obj.threeEqualParts2(new int[]{1, 0, 1, 0, 1})));
        System.out.println(Arrays.toString(obj.threeEqualParts2(nums)));
    }

    // My solution
    public int[] threeEqualParts(int[] arr) {
        int[] not_possible = new int[]{-1, -1};
        int count_1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                count_1++;
            }
        }
        // special case handling for array with all elements as 0
        if (count_1 == 0) return new int[]{0, 2};

        if (count_1 % 3 != 0) return new int[]{-1, -1};

        int count_1_per_part = count_1 / 3; // this is the number of 1's which each part must contain

       /* if we have start from last, take this example:

            1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0

            if we start the traversal from start, we won't know where the first segment will end.
            For example in the example above, we can't know if the first segment is 101 or 101000

            if we start from last index than we can go until we find 1's equal to count_1_per_part.
            and we will know for sure that we have to stop now.

            Algorithm is as follows:

            1. First calculate the last part. First and middle part should be exactly equal to this part

            2. Calculate the first part now. Ignore all the leading zeros from beginning. Once we find a 1, start recording from that index until we recorded
                   number up to the length of the previously calculated last part. If these two are equal than proceed to the calculation of middle part

            3. From the point the first part ended, ignore all the zeros before we get an 1. Start recording until we recoded numbers whose count is equal to
               the length of last part. Compare these with last part. If they are also equal than the array can be split into 3 equal parts. otherwise impossible

        */

        int count_1_last_part = 0;
        StringBuilder last = new StringBuilder();
        int i = arr.length - 1;
        while (count_1_last_part != count_1_per_part) {
            last.append(arr[i]);
            if (arr[i] == 1) count_1_last_part++;
            i--;
        }
        String lastPart = last.reverse().toString();
//        System.out.println(lastPart);

        // First part calculation
        int k = 0;
        while (arr[k] == 0) ++k;

        StringBuilder first = new StringBuilder();
        while (first.length() != lastPart.length()) {
            first.append(arr[k]);
            k++;
        }
//        System.out.println(first.toString());
        if (!first.toString().equals(lastPart)) {
            return not_possible;
        }

        // middle part calculation
        int j = k;
        while (arr[j] == 0) ++j;
        StringBuilder middle = new StringBuilder();
        while (middle.length() != lastPart.length()) {
            middle.append(arr[j]);
            j++;
        }
//        System.out.println(middle.toString());
        if (middle.toString().equals(lastPart)) {
            return new int[]{k - 1, j};
        }

        return not_possible;
    }

    // Same solution as mine but little optimised, from interview perspective it should be same
    // This solution does not use string builder, but use index for matching the pattern hence more optimised

    public int[] threeEqualParts2(int[] arr) {
        int[] not_possible = new int[]{-1, -1};
        int count_1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                count_1++;
            }
        }
        // special case handling for array with all elements as 0
        if (count_1 == 0) return new int[]{0, 2};

        if(count_1 % 3 != 0) return not_possible;

        int count_1_per_part = count_1 / 3;
        int count_1_third_part = 0;
        int i = arr.length - 1;
        while (count_1_third_part != count_1_per_part) {
            if (arr[i] == 1) count_1_third_part++;
            i--;
        }
        int third_part_start_index = i + 1; // the third part starts from here to the end of the array. The first and middle part has to be equal to this

        int first_part_end_point = getIndex(arr, 0, third_part_start_index);
        if (first_part_end_point < 0) return not_possible;

        int middle_part_end_point = getIndex(arr, first_part_end_point, third_part_start_index);
        if (middle_part_end_point < 0) return not_possible;

        return new int[]{first_part_end_point - 1, middle_part_end_point};
    }

    // start comparing from i to j, they must be same (ignoring leading zeros
    private int getIndex(int[] arr, int i, int j) {
        while (arr[i] == 0) ++i;
        while (j < arr.length) {
            if (arr[i] != arr[j]) return -1;
            ++i;
            ++j;
        }
        return i;
    }
}