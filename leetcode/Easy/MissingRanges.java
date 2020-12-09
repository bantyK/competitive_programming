import java.util.*;
//163 https://leetcode.com/problems/missing-ranges/
public class MissingRanges {
    public static void main(String[] args) {
        MissingRanges obj = new MissingRanges();
        System.out.println(obj.findMissingRanges(new int[]{6, 7, 11, 12, 14}, 0, 20));
        System.out.println(obj.findMissingRanges(new int[]{}, -3, -1));
        System.out.println(obj.findMissingRanges(new int[]{-2}, -3, -1));
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        if(n == 0) {
            res.add(lower + "->" + upper);
            return res;
        }

        if(nums[0] != lower) {
            addRange(lower, nums[0] - 1, res);
        }
        for(int i = 0; i < n - 1; i++) {
            if(nums[i+1] == nums[i] + 1) continue;
            else {
                addRange(nums[i] + 1, nums[i + 1] - 1, res);
            }
        }
        if(nums[n - 1] != upper) {
            addRange(nums[n - 1] + 1, upper, res);
        }
        return res;
    }

    private void addRange(int rangeStartIndex, int rangeEndIndex, List<String> list) {
        if(rangeStartIndex == rangeEndIndex) {
            list.add(String.valueOf(rangeStartIndex));
        } else {
            list.add(rangeStartIndex+ "->" + rangeEndIndex);
        }
    }
}