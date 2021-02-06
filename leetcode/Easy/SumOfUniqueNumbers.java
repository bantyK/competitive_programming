//1748 https://leetcode.com/problems/sum-of-unique-elements/

public int sumOfUnique(int[] nums) {
    int n = nums.length;
    int[] count = new int[101];
        
    for(int num : nums) {
        count[num] += 1;
    }
    int sum = 0;
        
    for(int i = 0; i < nums.length; i++) {
        if(count[nums[i]] == 1) {
            sum += nums[i];
        }    
    }

    return sum;
 }
