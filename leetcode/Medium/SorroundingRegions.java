package solutions.medium;

//https://leetcode.com/problems/surrounded-regions/
public class SorroundingRegions {

    public static void main(String[] args) {
        SorroundingRegions obj = new SorroundingRegions();
        char[][] grid = new char[][]{
                {'X', 'O', 'X', 'O', 'X', 'O'}
                , {'O', 'X', 'O', 'X', 'O', 'X'}
                , {'X', 'O', 'X', 'O', 'X', 'O'}
                , {'O', 'X', 'O', 'X', 'O', 'X'}};

        printBoard(grid);

        obj.solve(grid);

        printBoard(grid);

    }

    private static void printBoard(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void solve(char[][] board) {

        int nRow = board.length;

        if (nRow == 0) {
            return;
        }

        int nCol = board[0].length;

        boolean[][] visitedArray = new boolean[nRow][nCol];

        // make all the X's position as True
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (board[i][j] == 'X') {
                    visitedArray[i][j] = true;
                }
            }
        }

        // top row
        for (int i = 0; i < nCol; i++) {
            if (board[0][i] == 'O') {
//                visitedArray[0][i] = true;
                dfs(board, visitedArray, 0, i);
            }
        }

        // left row
        for (int i = 0; i < nRow; i++) {
            if (board[i][0] == 'O') {
//                visitedArray[i][0] = true;
                dfs(board, visitedArray, i, 0);
            }
        }
        // right row
        for (int i = 0; i < nRow; i++) {
            if (board[i][nCol - 1] == 'O') {
//                visitedArray[i][nCol - 1] = true;
                dfs(board, visitedArray, i, nCol - 1);
            }
        }
        //bottom row
        for (int i = 0; i < nCol; i++) {
            if (board[nRow - 1][i] == 'O') {
//                visitedArray[nRow - 1][i] = true;
                dfs(board, visitedArray, nRow - 1, i);
            }
        }


        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (!visitedArray[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }

    }

    private void dfs(char[][] board, boolean[][] visitedArray, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 'X') {
            return;
        }

        if (board[i][j] == 'O' && !visitedArray[i][j]) {
            visitedArray[i][j] = true;
            dfs(board, visitedArray, i, j + 1);
            dfs(board, visitedArray, i, j - 1);
            dfs(board, visitedArray, i + 1, j);
            dfs(board, visitedArray, i - 1, j);
        }
    }

    private void printVisitedArray(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
