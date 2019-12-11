import java.util.*;

//78 https://leetcode.com/problems/subsets/
public class Subsets {
    public static void main(String[] args) {
        Subsets obj = new Subsets();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> subsets = obj.subsets(nums);
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        generateSubsets(nums, subsets, new ArrayList<>(), 0);
        return subsets;
    }

    private void generateSubsets(int[] nums, List<List<Integer>> subsets, List<Integer> current, int index) {
        subsets.add(new ArrayList<>(current));

        if (index >= nums.length) return;

        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            generateSubsets(nums, subsets, current, i + 1);
            current.remove(current.size() - 1);
        }
    }

}
