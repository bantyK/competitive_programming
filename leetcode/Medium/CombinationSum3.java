import java.util.*;

// 216 https://leetcode.com/problems/combination-sum-iii/
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        helper(n, 1, k, new ArrayList<>(), result);
        return result;
    }

    private void helper(int target, int index, int k, List<Integer> current, List<List<Integer>> result) {
        if (target == 0 && current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (target < 0) return;

        for (int i = index; i < 10; i++) {
            current.add(i);
            helper(target - i, i + 1, k, current, result);
            current.remove(current.size() - 1);
        }
    }
}
