//27 https://leetcode.com/problems/remove-element/
public class RemoveElement {
    public static void main(String[] args) {
        RemoveElement obj = new RemoveElement();
        System.out.println(obj.removeElement(new int[]{0,1,2,2,3,0,4,2}, 2));
    }

    public int removeElement(int[] nums, int val) {
        int k = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}