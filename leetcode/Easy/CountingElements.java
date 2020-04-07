import java.util.*;
//https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/528/week-1/3289/
public class CountingElements {
    public static void main(String[] args) {
        CountingElements obj = new CountingElements();
        System.out.println(obj.countElements(new int[]{1,1,2,2,3}));
    }

    public int countElements(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        int count = 0;
        Arrays.sort(arr);

        Set<Integer> set = new HashSet<>();

        for (int num : arr) {
            set.add(num);
        }

        for (int num : arr) {
            if (set.contains(num + 1)) {
                count += 1;
            }
        }

        return count;
    }
}
