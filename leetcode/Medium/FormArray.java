//#1764 https://leetcode.com/problems/form-array-by-concatenating-subarrays-of-another-array/
public class FormArray {
    public boolean canChoose(int[][] groups, int[] nums) {
        int grpIndex = 0;

        for (int i = 0; i < nums.length && grpIndex < groups.length; i++) {
            if (nums[i] == groups[grpIndex][0]) {
                int j = i;
                int x = 0;
                while (j < nums.length && x < groups[grpIndex].length && nums[j] == groups[grpIndex][x]) {
                    j++;
                    x++;
                }
                if (x == groups[grpIndex].length) {
                    grpIndex++;
                    i = j - 1;
                }
            }
        }

        return grpIndex == groups.length;
    }
}