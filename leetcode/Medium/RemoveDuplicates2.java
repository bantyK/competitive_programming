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
}
