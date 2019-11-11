package solutions.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/combination-sum-ii/
public class CombinationSum2 {
    public static void main(String[] args) {
        CombinationSum2 obj = new CombinationSum2();

        int[] candidates = new int[]{10, 1, 2,7, 6, 1, 5};

        int target = 8;

        List<List<Integer>> result = obj.combinationSum2(candidates, target);

        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j));
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();

        if (candidates == null || candidates.length == 0) return results;

        Arrays.sort(candidates);

        ArrayList<Integer> combination = new ArrayList<>();
        findCombinations(candidates, combination, target, 0, results);
        return results;
    }

    private void findCombinations(int[] candidates, ArrayList<Integer> combination, int target, int startIndex, List<List<Integer>> results) {
        if (target == 0) {
            results.add(new ArrayList<>(combination));
            return;
        }

        if (target < 0) return;

        for (int i = startIndex; i < candidates.length; i++) {
            if (i == startIndex || candidates[i] != candidates[i - 1]) {
                combination.add(candidates[i]);
                findCombinations(candidates, combination, target-candidates[i], i + 1, results);
                combination.remove(combination.size() - 1);
            }
        }
    }
}
