import java.util.*;
//47 https://leetcode.com/problems/permutations-ii/
public class Permutations2 {
    public static void main(String[] args) {
        Permutations2 obj = new Permutations2();

        List<List<Integer>> permutations = obj.permuteUnique(new int[]{1, 1, 2});

        for (List<Integer> permutation : permutations) {
            System.out.println(permutation);
        }
    }

    public List<List<Integer>> permuteUnique(int[] arr) {
        Set<List<Integer>> permutations = new HashSet<>();
        boolean[] visited = new boolean[arr.length];
        permutationHelper(arr, permutations, new ArrayList<>(), visited);
        return new ArrayList<>(permutations);
    }

    private void permutationHelper(int[] arr, Set<List<Integer>> permutations, List<Integer> currentList, boolean[] visited) {
        if (currentList.size() == arr.length) {
            permutations.add(new ArrayList<>(currentList));
            return;
        }

        for (int i = 0; i < arr.length; i++) {

            if (visited[i]) continue;

            visited[i] = true;

            currentList.add(arr[i]);

            permutationHelper(arr, permutations, currentList, visited);

            currentList.remove(currentList.size() - 1);

            visited[i] = false;
        }
    }


}
