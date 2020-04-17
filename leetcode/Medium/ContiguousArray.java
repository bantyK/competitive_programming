import java.util.*;

//525 https://leetcode.com/problems/contiguous-array/
public class ContiguousArray {
    public static void main(String[] args) {
        ContiguousArray obj = new ContiguousArray();

//        System.out.println(obj.findMaxLength(new int[]{0,0,1,1,0,1,1}));
        System.out.println(obj.findMaxLength(new int[]{0, 0, 1, 0, 0, 0, 1, 1}));

    }

    public int findMaxLength(int[] nums) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum--;
            } else {
                sum++;
            }

            if(map.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - map.get(sum));
            } else {
                map.put(sum, i);
            }

        }

        return maxLength;
    }
}
