
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.hackerearth.com/practice/algorithms/graphs/depth-first-search/practice-problems/algorithm/areas-0475fb6e/
public class Areas {
    private static int rows = 0;
    private static int cols = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            String[] line = br.readLine().split(" ");
            rows = Integer.parseInt(line[0]);
            cols = Integer.parseInt(line[1]);
            char[][] grid = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                String line2 = br.readLine();
                for (int j = 0; j < cols; j++) {
                    grid[i][j] = line2.charAt(j);
                }
            }

            dfs(grid);
        }
    }

    private static void dfs(char[][] grid) {
        int count = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '.') {
                    count++;
                    builder.append(helper(grid, i, j))
                            .append(" ");
                }
            }
        }
        System.out.println(count);
        System.out.println(builder.toString().trim());
    }

    private static int helper(char[][] grid, int i, int j) {
        if (i < 0 || i >= rows) return 0;
        if (j < 0 || j >= cols) return 0;
        if (grid[i][j] == '#') return 0;

        grid[i][j] = '#';
        int area = 1;

        area += helper(grid, i + 1, j);
        area += helper(grid, i - 1, j);
        area += helper(grid, i, j - 1);
        area += helper(grid, i, j + 1);

        return area;
    }
}
