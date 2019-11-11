package solutions.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/combination-sum/
public class CombinationSum {
    public static void main(String[] args) {
        CombinationSum obj = new CombinationSum();
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 100;

        List<List<Integer>> result = obj.combinationSum(candidates, target);

        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j));
            }
            System.out.println();
        }

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if (candidates == null || candidates.length == 0) return result;

        Arrays.sort(candidates);
        findCombinations(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void findCombinations(int[] candidates, int startIndex, int target, ArrayList<Integer> combinations, List<List<Integer>> result) {

        if (target == 0) {
            // Combinations contains numbers which adds to target
            result.add(new ArrayList<>(combinations));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (candidates[i] > target) break;
            combinations.add(candidates[i]);
            findCombinations(candidates, i, target - candidates[i], combinations, result);
            combinations.remove(combinations.size() - 1);
        }
    }

}
