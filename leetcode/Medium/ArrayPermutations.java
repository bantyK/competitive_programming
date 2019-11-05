package src;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/permutations/
public class ArrayPermutations {

    public List<List<Integer>> permute(int[] originalArray) {
        List<List<Integer>> allPermutations = new ArrayList<>();
        generateAllPermutations(new ArrayList<>(), originalArray, allPermutations);
        return allPermutations;
    }

    private void generateAllPermutations(List<Integer> runningChoices, int[] originalArray, List<List<Integer>> allPermutations) {

        if (runningChoices.size() == originalArray.length) {
            allPermutations.add(new ArrayList<>(runningChoices));
            return;
        }

        for (int choice : originalArray) {
            if (runningChoices.contains(choice)) continue;

            runningChoices.add(choice);

            generateAllPermutations(runningChoices, originalArray, allPermutations);

            runningChoices.remove(runningChoices.size() - 1);
        }

    }

    public static void main(String[] args) {
        ArrayPermutations obj = new ArrayPermutations();
        List<List<Integer>> permutations = obj.permute(new int[]{1, 2, 3});

        for(List<Integer> ints : permutations) {
            System.out.println(ints);
        }
    }
}
