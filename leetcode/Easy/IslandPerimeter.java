package solutions.easy;

// https://leetcode.com/problems/island-perimeter/
public class IslandPerimeter {
    public static void main(String[] args) {
        IslandPerimeter obj = new IslandPerimeter();


        int[][] grid = new int[][]{
                {1, 1}
        };

        System.out.println(
                obj.islandPerimeter(grid)
        );
    }

    // actual submitted solution
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {

                    if (i == 0 || grid[i - 1][j] == 0) perimeter += 1;
                    if (i == rows - 1 || grid[i + 1][j] == 0) perimeter += 1;
                    if (j == 0 || grid[i][j - 1] == 0) perimeter += 1;
                    if (j == cols - 1 || grid[i][j + 1] == 0) perimeter += 1;
                }
            }
        }
        return perimeter;
    }

    // DFS solution, very slow
    public int islandPerimeterDFS(int[][] grid) {
        int r = 0;
        int c = 0;
        boolean found = false;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    found = true;
                    r = i;
                    c = j;
                    break;
                }
            }
            if (found)
                break;
        }

        return perimeter(grid, r, c);
    }

    private int perimeter(int[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == 0 || grid[i][j] == 2)
            return 0;

        grid[i][j] = 2;

        int perimeter = 0;

        if (i == 0 || grid[i - 1][j] == 0) perimeter += 1;
        if (i == rows - 1 || grid[i + 1][j] == 0) perimeter += 1;
        if (j == 0 || grid[i][j - 1] == 0) perimeter += 1;
        if (j == cols - 1 || grid[i][j + 1] == 0) perimeter += 1;

        perimeter += perimeter(grid, i + 1, j);
        perimeter += perimeter(grid, i - 1, j);
        perimeter += perimeter(grid, i, j + 1);
        perimeter += perimeter(grid, i, j - 1);

        return perimeter;
    }
}


