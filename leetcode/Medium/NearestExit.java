import java.util.*;

// 1926 https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/
public class NearestExit {
	public static void main(String[] args) {
		char[][] maze = new char[][]{{'+','+','.','+'},{'.','.','.','+',{}}};
	}

	public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Set<Cell> exitPoints = new HashSet<>();

        // top and bottom row
        for (int j = 0; j < n; j++) {
            if (maze[0][j] == '.') {
                exitPoints.add(new Cell(0, j));
            }
            if (maze[m - 1][j] == '.') {
                exitPoints.add(new Cell(m - 1, j));
            }
        }

        // left and right columns
        for (int i = 0; i < m; i++) {
            if (maze[i][0] == '.') {
                exitPoints.add(new Cell(i, 0));
            }
            if (maze[i][n - 1] == '.') {
                exitPoints.add(new Cell(i, n - 1));
            }
        }

        exitPoints.remove(new Cell(entrance[0], entrance[1]));

        Queue<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(entrance[0], entrance[1]));

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Cell cell = queue.poll();

                if (exitPoints.contains(cell)) {
                    return steps;
                }

                for (int[] direction : directions) {
                    int nextRow = cell.x + direction[0];
                    int nextCol = cell.y + direction[1];

                    if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || maze[nextRow][nextCol] == '+') {
                        continue;
                    }

                    queue.offer(new Cell(nextRow, nextCol));
                    maze[nextRow][nextCol] = '+'; // visited
                }
            }

            steps++;
        }

        return -1;
    }

    private static class Cell {
        int x, y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return x == cell.x && y == cell.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}