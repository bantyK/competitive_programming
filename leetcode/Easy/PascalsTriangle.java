import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 118 https://leetcode.com/problems/pascals-triangle/
public class PascalsTriangle {
    public static void main(String[] args) {
        PascalsTriangle obj = new PascalsTriangle();
        final List<List<Integer>> rows = obj.generate(5);
        for(List<Integer> row : rows) {
            System.out.println(row);
        }
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if(numRows == 0) return result;

        List<Integer> row0 = Arrays.asList(1);
        result.add(row0);

        generate(row0, 1, numRows, result);
        return result;
    }

    private void generate(List<Integer> previous, int currentIndex, int numRows, List<List<Integer>> result) {
        if(currentIndex == numRows) {
            return;
        }

        List<Integer> row = new ArrayList<>();
        row.add(previous.get(0));
        int i = 0;
        for(i = 0; i < previous.size() - 1; i++) {
            row.add(previous.get(i) + previous.get(i + 1));
        }
        row.add(previous.get(i));
        result.add(row);
        generate(row, currentIndex+1, numRows, result);
    }
}
