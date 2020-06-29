import java.util.*;

//1476 https://leetcode.com/problems/subrectangle-queries/
public class SubrectangleQueries {

    // for approach 1
    int[][] rectangle;

    // For approach 2
    int[][] r;
    private LinkedList<int[]> histories = new LinkedList<>();

    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;

        r = new int[rectangle.length][0];
        int i = 0;
        for (int[] row : rectangle) {
            r[i++] = row.clone();
        }

    }

    public static void main(String[] args) {
        int[][] rectangle = new int[][]{{1, 2, 1}, {4, 3, 4}, {3, 2, 1}, {1, 1, 1}};
        SubrectangleQueries obj = new SubrectangleQueries(rectangle);
        System.out.println(obj.getValue2(0,2));
        obj.updateSubrectangle2(0,0,3,2,5);
        System.out.println(obj.getValue2(0,2));

    }

    //////////////////// Approach 2 ////////////////////

    public void updateSubrectangle2(int row1, int col1, int row2, int col2, int newValue) {
        histories.push(new int[]{row1, row2, col1, col2, newValue});
    }

    public int getValue2(int row, int col) {
        for (int[] history : histories) {
            if (row >= history[0] && row <= history[1] && col >= history[2] && col <= history[3]) {
                return history[4];
            }
        }

        return r[row][col];
    }

    //////////////////// Approach 1 (Brute force)////////////////////
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                rectangle[i][j] = newValue;
            }
        }
    }

    public int getValue(int row, int col) {
        return rectangle[row][col];
    }
}
