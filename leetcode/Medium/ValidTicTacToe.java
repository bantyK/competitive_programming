// 794 https://leetcode.com/problems/valid-tic-tac-toe-state/
import java.util.*;

public class ValidTicTacToe {
    public static void main(String[] args) {
        ValidTicTacToe obj = new ValidTicTacToe();
        System.out.println(obj.validTicTacToe(new String[]{"OXX","XOX","OXO"}));
    }

    public boolean validTicTacToe(String[] board) {
        if(board.length == 0) return true;

        int numX = 0;
        int numO = 0;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i].charAt(j) == 'X') {
                    numX++;
                } else if(board[i].charAt(j) == 'O') {
                    numO++;
                }
            }
        }

        boolean hasXWon = hasPlayerWon(board, 'X');
        boolean hasOWon = hasPlayerWon(board, 'O');

        if(numO > numX) return false;

        else if(Math.abs(numX - numO) > 1) return false;
        
        else if(numX + numO < 9 && hasXWon && numX == numO) return false;
        
        else  if (hasXWon && hasOWon) return false;

        else if(hasOWon && numX > numO) return false;

        return true;
    
    }

    private boolean hasPlayerWon(String[] board, char player) {
        String toCheck = ""+player+player+player;

        return board[0].equals(toCheck) || 
            board[1].equals(toCheck) || 
            board[2].equals(toCheck) ||
            getColumnString(board, 0).equals(toCheck) ||
            getColumnString(board, 1).equals(toCheck) ||
            getColumnString(board, 2).equals(toCheck) ||
            diagonalString1(board).equals(toCheck) ||
            diagonalString2(board).equals(toCheck);
    }

    private String getColumnString(String[] board, int col) {
        String s = "";
        for(int i = 0; i < 3; i++) {
            s += board[i].charAt(col);
        }

        return s;
    }

    private String diagonalString1(String[] board) {
        String s = "";
        for(int i = 0; i < 3; i++) s += board[i].charAt(i);
        return s;
    }
    
    private String diagonalString2(String[] board) {
        String s = "";
        for(int i = 0; i < 3; i++) s += board[i].charAt(2-i);
        return s;
    }
}