import java.util.ArrayList;
import java.util.List;

// 51 https://leetcode.com/problems/n-queens/
public class Nqueens {
    public static void main(String[] args) {
        Nqueens obj = new Nqueens();
        final List<List<String>> lists = obj.solveNQueens(4);

        System.out.println(lists);
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        solveNQueens(n, 0, new ArrayList<>(), result);
        return result;
    }

    private void solveNQueens(int n, int row, List<Integer> placements, List<List<String>> results) {
        if (row == n) {
            results.add(generateBoardFromPlacements(placements, n));
            return;
        }

        for (int col = 0; col < n; col++) {
            placements.add(col);
            if (isValid(placements)) {
                solveNQueens(n, row + 1, placements, results);
            }

            placements.remove(placements.size() - 1);
        }
    }

    private List<String> generateBoardFromPlacements(List<Integer> placements, int n) {
        List<String> board = new ArrayList<>();

        for (Integer placement : placements) {
            StringBuilder builder = new StringBuilder();

            for (int col = 0; col < n; col++) {
                builder.append(col == placement ? "Q" : ".");
            }

            board.add(builder.toString());
        }

        return board;
    }

    private boolean isValid(List<Integer> placements) {
        // this is the most recent placed queen and we are checking the validity of its placement only
        int rowId = placements.size() - 1;
        for (int id = 0; id < rowId; id++) {
            int columnDistance = Math.abs(placements.get(id) - placements.get(rowId));
            int rowDistance = rowId - id;
            if (columnDistance == 0 || columnDistance == rowDistance) {
                return false;
            }
        }
        return true;
    }
}
