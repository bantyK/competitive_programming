import java.util.*;

//1630 https://leetcode.com/problems/arithmetic-subarrays/
public class ArithmeticSubarrays {

    public static void main(String[] args) {
        ArithmeticSubarrays obj = new ArithmeticSubarrays();


        System.out.println(obj.checkArithmeticSubarrays(
                new int[]{-12, -9, -3, -12, -6, 15, 20, -25, -20, -15, -10},
                new int[]{0, 1, 6, 4, 8, 7},
                new int[]{4, 4, 9, 7, 9, 10})
        );
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            res.add(isAPSorted(nums.clone(), l[i], r[i]));
//            res.add(isAP(nums, l[i], r[i]));
        }

        return res;
    }

    private boolean isAPSorted(int[] arr, int low, int high) {
        if (high - low + 1 <= 2) return true;
        Arrays.sort(arr, low, high + 1);

        int d = arr[low + 1] - arr[low];

        for (int i = low + 1; i <= high; i++) {
            if (arr[i] - arr[i - 1] != d) return false;
        }

        return true;
    }

    // checking AP without sort
    private boolean isAP(int[] arr, int low, int high) {
        int len = high - low + 1;
        if (len == 2) return true;
        Set<Integer> set = new HashSet<>();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = low; i <= high; i++) {
            set.add(arr[i]);
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        int rem = (max - min) % (len - 1);
        if (rem != 0) return false;
        int d = (max - min) / (len - 1);

        int num = min;
        int i = low;
        while (i++ <= high) {
            if (!set.contains(num)) {
                return false;
            }
            num += d;
        }

        return true;
    }
}