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
        Set<List<Integer>> subsets = new HashSet<>();
        boolean[] visited = new boolean[nums.length];
        generateSubsets(nums, subsets, new ArrayList<>(), 0, visited);
        return new ArrayList<>(subsets);
    }

    private void generateSubsets(int[] nums, Set<List<Integer>> subsets, List<Integer> current, int index, boolean[] visited) {
        subsets.add(new ArrayList<>(current));

        if (index >= nums.length) return;

        for (int i = index; i < nums.length; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            current.add(nums[i]);
            generateSubsets(nums, subsets, current, i + 1, visited);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

}
