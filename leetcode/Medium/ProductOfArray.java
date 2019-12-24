import java.util.*;

// 238 https://leetcode.com/problems/product-of-array-except-self/
public class ProductOfArray {
    public static void main(String[] args) {
        ProductOfArray obj = new ProductOfArray();

        System.out.println(Arrays.toString(new int[]{362880, 181440, 120960, 90720, 72576, 60480, 51840, 45360, 40320}));
        System.out.println(Arrays.toString(obj.productExceptSelf(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9})));
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        if (n == 0) return result;
        result[0] = 1;

        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int num = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            result[i] = result[i] * num;
            num = num * nums[i];
        }

        return result;
    }

    // Approach 1(with extra space)
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        if(n == 0) return result;

        int[] leftProduct = new int[n];
        leftProduct[0] = 1;
        for(int i = 1; i < n; i++) {
            leftProduct[i] = leftProduct[i-1] * nums[i - 1];
        }

        int[] rightProduct = new int[n];
        rightProduct[n - 1] = 1;

        for(int i = n - 2; i >= 0; i--) {
            rightProduct[i] = rightProduct[i + 1] * nums[i + 1];
        }

        for(int i = 0; i < n; i++) {
            result[i] = leftProduct[i] * rightProduct[i];
        }

        return result;
    }
}
