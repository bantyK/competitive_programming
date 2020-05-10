import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 119 https://leetcode.com/problems/pascals-triangle-ii/
public class PascalsTriangle2 {


    public List<Integer> getRow(int rowIndex) {
        List<Integer> row0 = Arrays.asList(1);
        return generate(row0, 0, rowIndex);
    }

    private List<Integer> generate(List<Integer> previous, int currentIndex, int numRows) {
        if (currentIndex == numRows) {
            return previous;
        }

        List<Integer> row = new ArrayList<>();
        row.add(previous.get(0));
        int i = 0;
        for (i = 0; i < previous.size() - 1; i++) {
            row.add(previous.get(i) + previous.get(i + 1));
        }
        row.add(previous.get(i));
        return generate(row, currentIndex + 1, numRows);
    }
}
