import java.util.Arrays;

//66 https://leetcode.com/problems/plus-one/
public class PlusOne {
    public static void main(String[] args) {
        PlusOne obj = new PlusOne();
        System.out.println(Arrays.toString(obj.plusOne(new int[]{9, 9})));
        System.out.println(Arrays.toString(obj.plusOne(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(obj.plusOne(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0})));
        System.out.println(Arrays.toString(obj.plusOne(new int[]{7, 2, 8, 5, 0, 9, 1, 2, 9, 5, 3, 6, 6, 7, 3, 2, 8, 4, 3, 7, 9, 5, 7, 7, 4, 7, 4, 9, 4, 7, 0, 1, 1, 1, 7, 4, 0, 0, 6})));
        System.out.println(Arrays.toString(obj.plusOne(new int[]{7, 2, 8, 5, 0, 9, 1, 2, 9, 5, 3, 6, 6, 7, 3, 2, 8, 4, 3, 7, 9, 5, 7, 7, 4, 7, 4, 9, 4, 7, 0, 1, 1, 1, 7, 4, 0, 0, 9})));
        System.out.println(Arrays.toString(obj.plusOne(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9})));
    }

    public int[] plusOne(int[] digits) {
        int carry = 1;
        int n = digits.length;
        if (n == 0) return new int[0];
        int temp;
        for (int i = n - 1; i >= 0; i--) {
            temp = digits[i] + carry;
            if (temp >= 10) {
                digits[i] = temp % 10;
                carry = 1;
            } else {
                digits[i] = temp;
                carry = 0;
            }
        }

        if (carry == 1) {
            int[] res = new int[n + 1];
            res[0] = 1;
            return res;
        }
        return digits;

    }
}
