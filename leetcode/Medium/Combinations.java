import java.util.*;

// 77 https://leetcode.com/problems/combinations/
public class Combinations {
    public static void main(String[] args) {
        Combinations obj = new Combinations();
        obj.combine(4, 2);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombinations(n, result, new ArrayList<>(), k, 1);
        return result;
    }

    private void generateCombinations(int n, List<List<Integer>> result, List<Integer> current, int k, int index) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i <= n; i++) {
            current.add(i);
            generateCombinations(n, result, current, k, i + 1);
            current.remove(current.size() - 1);
        }
    }
}
