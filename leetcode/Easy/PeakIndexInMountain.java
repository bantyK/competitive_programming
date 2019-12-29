import java.util.*;
// 852 https://leetcode.com/problems/peak-index-in-a-mountain-array/
public class PeakIndexInMountain {
    public static void main(String[] args) {
        int[] arr = {0,1,2,3};
        System.out.println(peakIndexInMountainArray(arr));
    }

    public static int peakIndexInMountainArray(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        
        while(low <= high) {
            int mid = (high + low) / 2;

            if(arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) {
                return mid;
            } else if(arr[mid] > arr[mid + 1]) {
                high = mid -1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}