import java.util.*;
//303 https://leetcode.com/problems/range-sum-query-immutable/
public class NumArray {
    public static void main(String[] args) {
        NumArray obj = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});

        int sum = obj.sumRange(0, 5);
        System.out.println(sum);
    }

    private int[] rangeSum;
    
    public NumArray(int[] nums) {
        rangeSum = new int[nums.length + 1];
        rangeSum[0] = 0;
        
        for(int i = 1; i <= nums.length; i++) {
            rangeSum[i] = rangeSum[i-1] + nums[i-1];    
        }
    }
    
    public int sumRange(int i, int j) {
        return rangeSum[j + 1] - rangeSum[i];
    }
}