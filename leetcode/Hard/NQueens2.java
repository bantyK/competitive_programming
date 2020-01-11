import java.util.*;

// 52 https://leetcode.com/problems/n-queens-ii/
public class NQueens2 {

    private static int count = 0;

    public int totalNQueens(int n) {
        count = 0;
        solveNQueens(n, 0, new ArrayList<Integer>());
        return count;
    }

    private void solveNQueens(int n, int row, List<Integer> placement) {
        if(row == n) {
            count++;
            return;
        }

        for(int col = 0; col < n; col++) {
            placement.add(col);
            if(isValid(placement, n)) {
                solveNQueens(n, row + 1, placement);
            }

            placement.remove(placement.size() - 1);
        }
    }

    private boolean isValid(List<Integer> placement, int n) {
        int row = placement.size() - 1;
        for(int id = 0; id < row; id++) {
            int colDiff = Math.abs(placement.get(id) - placement.get(row));
            int rowDiff = row - id;

            if(colDiff == 0 || rowDiff == colDiff) return false;
        }

        return true;
    }
}
