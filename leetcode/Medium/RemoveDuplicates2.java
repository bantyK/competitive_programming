// 80 https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
public class RemoveDuplicates2 {
	public int removeDuplicates(int[] nums) {
		int i = 0;

		for(int n : nums) {
			if(i < 2 || n > nums[i]) {
				nums[i] = n;
				i++;
			}
		}
		return i;
	}

	// Another solution
	// Over-write the position which has more than 2 duplicates
	// In implementation we over-write everytime the count is less than 2
	// if the count of any number goes beyond 2, we dont over-write and wait for a number whose
	// count is less than 2
	public int removeDuplicates2(int[] nums) {
        int j = 1, count = 1;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            if(count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}
