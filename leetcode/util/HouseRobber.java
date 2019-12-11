import java.util.*;

/*
* Contains useful information regarding recursion and backtracking
* */
public class HouseRobber {
    public static void main(String[] args) {
        HouseRobber obj = new HouseRobber();
        System.out.println(obj.getMaximumGold(new int[]{3, 1, 2, 5, 4, 2}));
        System.out.println(obj.getMaximumGoldRecursive(new int[]{3, 1, 2, 5, 4, 2}));
    }


    // recursive approach
    public int getMaximumGoldRecursive(int[] houses) {
        return helper(houses, 0);
    }

    private int helper(int[] houses, int houseIndex) {
        if (houseIndex >= houses.length) {
            return 0;
        }

        return Math.max((houses[houseIndex] + helper(houses, houseIndex + 2)), houses[houseIndex + 1]);
    }


    // Generate all subsets and then find the maximum sum. Time O(2^n)
    public int getMaximumGold(int[] houses) {
        List<List<Integer>> allPossibleCombinations = new ArrayList<>();
        generateSubsets(houses, allPossibleCombinations, new ArrayList<>(), 0);
        int maxSum = 0;
        for (List<Integer> subset : allPossibleCombinations) {
            int sum = subset.stream().mapToInt(Integer::intValue).sum();
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    private void generateSubsets(int[] gold, List<List<Integer>> allPossibleCombinations, List<Integer> currentCombination, int index) {
        if (currentCombination.size() > 0) {
            allPossibleCombinations.add(new ArrayList<>(currentCombination));
        }

        if (index >= gold.length) return;

        for (int i = index; i < gold.length; i++) {
            currentCombination.add(gold[i]);
            generateSubsets(gold, allPossibleCombinations, currentCombination, i + 2);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}