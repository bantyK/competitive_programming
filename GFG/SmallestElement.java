package binarysearch;

// https://practice.geeksforgeeks.org/problems/minimum-element-in-a-sorted-and-rotated-array/0
import java.util.*;
public class SmallestElement {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        s.nextLine();

        for(int k = 0; k < t; k++) {
            int n = s.nextInt();
            s.nextLine();

            int[] nums = new int[n];
            String[] line = s.nextLine().split(" ");
            for(int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(line[i]);
            }

            System.out.println(findMin(nums));
        }
    }

    private static int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while(low <= high) {
            int mid = (low + high) / 2;

            if(nums[mid] >= nums[low]) {
                min = Math.min(nums[low], min);
                low = mid + 1;
            } else {
                min = Math.min(nums[mid], min);
                high = mid - 1;
            }
        }

        return min;
    }
}
