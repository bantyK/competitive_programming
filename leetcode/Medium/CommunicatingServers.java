import java.util.*;

// 1267 https://leetcode.com/problems/count-servers-that-communicate/
public class CommunicatingServers {
    public static void main(String[] args) {
        CommunicatingServers obj = new CommunicatingServers();
        System.out.println(obj.countServers(new int[][] { { 0, 1 }, { 1, 0 } }));
    }

    public int countServers(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[] rowCount = new int[rows];
        int[] colCount = new int[cols];
        int totalCommunicatingServers = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    rowCount[row]++;
                    colCount[col]++;
                    totalCommunicatingServers++;
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (rowCount[row] == 1 && colCount[col] == 1) {
                    // there is only one server in this row and col,
                    // hence there is no commnunication between.
                    // communicating server count needs to be decreased
                    totalCommunicatingServers--;
                }
            }
        }
        return totalCommunicatingServers;
    }

}