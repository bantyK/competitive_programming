import java.util.*;

//229 https://leetcode.com/problems/majority-element-ii/

// Boyer moore majority element
public class MajorityElement2 {
    public static void main(String[] args) {
        MajorityElement2 obj = new MajorityElement2();
        System.out.println(obj.majorityElement(new int[]{2, 2, 9, 3, 9, 3, 9, 3, 9, 3, 9, 3, 9, 3, 9, 3, 9}));
    }

    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = nums[0];
        int candidate2 = nums[0];

        int count1 = 0;
        int count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            }
            if (num == candidate2) {
                count2++;
            }
        }

        List<Integer> result = new ArrayList<>();
        if (count1 > nums.length / 3) result.add(candidate1);
        if (count2 > nums.length / 3) result.add(candidate2);

        return result;
    }
}
