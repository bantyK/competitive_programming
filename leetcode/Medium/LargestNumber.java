import java.util.*;

// 179 https://leetcode.com/problems/largest-number/
public class LargestNumber {
    public static void main(String[] args) {
        LargestNumber obj = new LargestNumber();
        int[] nums = {0, 0};
        String res = obj.largestNumber(nums);
        System.out.println(res);
    }

    private String largestNumber(int[] nums) {
        String[] numsString = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            numsString[i] = String.valueOf(nums[i]);
        }

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String num1 = s1 + s2;
                String num2 = s2 + s1;

                return num2.compareTo(num1);
            }
        };

        Arrays.sort(numsString, comparator);

        StringBuilder builder = new StringBuilder();
        for (String s : numsString) {
            builder.append(s);
        }

        if (builder.charAt(0) == '0') return "0";

        return builder.toString();
    }
}
