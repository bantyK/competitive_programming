// 750 https://leetcode.com/problems/number-of-corner-rectangles/
public class CornerRectangles {
    public static void main(String[] args) {
        CornerRectangles obj = new CornerRectangles();
        System.out.println(obj.countCornerRectangles(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}));
    }

    public int countCornerRectangles(int[][] grid) {
        int totalCount = 0;

        int numRows = grid.length;
        int numCols = grid[0].length;

        // row1 and row2 are two horizontal lines
        // We will try to cut these 2 horizontal lines with vertical lines
        // everytime there is are valid cuts(grid with value as 1) that constitute a count
        // from all these valid cuts, we can form rectangles choosing any two valid cuts
        // from n options we need to choose 2. Nc2
        for (int row1 = 0; row1 < numRows - 1; row1++) {
            for (int row2 = row1 + 1; row2 < numRows; row2++) {
                int totalGoodVerticalLines = 0;

                for (int col = 0; col < numCols; col++) {
                    if (grid[row1][col] == 1 && grid[row2][col] == 1) {
                        // a valid cut
                        totalGoodVerticalLines++;
                    }
                }

                // this is nothing but Nc2
                totalCount += totalGoodVerticalLines * (totalGoodVerticalLines - 1) / 2;
            }

        }

        return totalCount;
    }
}
