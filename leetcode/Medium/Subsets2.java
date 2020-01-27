import java.util.*;

//90 https://leetcode.com/problems/subsets-ii/
public class Subsets2 {
    public static void main(String[] args) {
        Subsets2 obj = new Subsets2();
        int[] nums = new int[]{4, 4, 4, 1, 4};
        List<List<Integer>> subsets = obj.subsetsWithDup(nums);

        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        withDuplicates(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private void withDuplicates(int[] nums, int index, ArrayList<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));

        if(index >= nums.length) return;

        for(int i = index; i < nums.length; i++) {
            if(i > index && nums[i] == nums[i - 1]) continue;
            current.add(nums[i]);
            withDuplicates(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

}
