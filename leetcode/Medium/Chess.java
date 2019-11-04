package solutions.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/queens-that-can-attack-the-king/
public class Chess {
    public static void main(String[] args) {
        Chess obj = new Chess();
        int[][] queensCoordinates = new int[][]{{0,1},{1,0},{4,0},{0,4},{3,3},{2,4}};
        int[] kingCoordinates = new int[]{0, 0};
        List<List<Integer>> lists = obj.queensAttacktheKing(queensCoordinates, kingCoordinates);

        for (List<Integer> coordinates : lists) {
            System.out.println(coordinates);
        }
    }

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> res = new ArrayList<>();

        int startX = king[0];
        int startY = king[1];

        HashSet<String> queenPos = new HashSet<>();

        for (int[] q : queens) {
            queenPos.add(q[0] + "" + q[1]);
        }

        List<Integer> right = getRightQueen(queenPos, startX, startY);
        if (right.size() > 0) {
            res.add(right);
        }

        List<Integer> left = getLeftQueen(queenPos, startX, startY);
        if (left.size() > 0) {
            res.add(left);
        }

        List<Integer> top = getTopQueen(queenPos, startX, startY);
        if (top.size() > 0) {
            res.add(top);
        }

        List<Integer> bottom = getBottomQueen(queenPos, startX, startY);
        if (bottom.size() > 0) {
            res.add(bottom);
        }

        List<Integer> topLeft = getTopLeftQueen(queenPos, startX, startY);
        if (topLeft.size() > 0) {
            res.add(topLeft);
        }

        List<Integer> topRight = getTopRightQueen(queenPos, startX, startY);
        if (topRight.size() > 0) {
            res.add(topRight);
        }

        List<Integer> bottomRight = getBottomRightQueen(queenPos, startX, startY);
        if (bottomRight.size() > 0) {
            res.add(bottomRight);
        }

        List<Integer> bottomLeft = getBottomLeftQueen(queenPos, startX, startY);
        if (bottomLeft.size() > 0) {
            res.add(bottomLeft);
        }

        return res;
    }

    private List<Integer> getTopQueen(HashSet<String> queenPos, int startX, int startY) {
        List<Integer> coordinates = new ArrayList<>();
        for (int i = startX - 1; i >= 0; i--) {
            if (queenPos.contains(i + "" + startY)) {
                coordinates.add(i);
                coordinates.add(startY);
                return coordinates;
            }
        }
        return coordinates;
    }


    private List<Integer> getBottomQueen(HashSet<String> queenPos, int startX, int startY) {
        List<Integer> coordinates = new ArrayList<>();
        for (int i = startX + 1; i < 8; i++) {
            if (queenPos.contains(i + "" + startY)) {
                coordinates.add(i);
                coordinates.add(startY);
                return coordinates;
            }
        }
        return coordinates;
    }

    private List<Integer> getRightQueen(HashSet<String> queenPos, int startX, int startY) {
        List<Integer> coordinates = new ArrayList<>();
        for (int i = startY + 1; i < 8; i++) {
            if (queenPos.contains(startX + "" + i)) {
                coordinates.add(startX);
                coordinates.add(i);
                return coordinates;
            }
        }
        return coordinates;
    }

    private List<Integer> getLeftQueen(HashSet<String> queenPos, int startX, int startY) {
        List<Integer> coordinates = new ArrayList<>();
        for (int i = startY - 1; i >= 0; i--) {
            if (queenPos.contains(startX + "" + i)) {
                coordinates.add(startX);
                coordinates.add(i);
                return coordinates;
            }
        }
        return coordinates;
    }

    private List<Integer> getTopLeftQueen(HashSet<String> queenPos, int startX, int startY) {
        List<Integer> coordinates = new ArrayList<>();
        int x = startX - 1;
        int y = startY - 1;
        while (x >= 0 && y >= 0) {
            if (queenPos.contains(x + "" + y)) {
                coordinates.add(x);
                coordinates.add(y);
                return coordinates;
            }
            x--;
            y--;
        }
        return coordinates;
    }

    private List<Integer> getTopRightQueen(HashSet<String> queenPos, int startX, int startY) {
        List<Integer> coordinates = new ArrayList<>();
        int x = startX - 1;
        int y = startY + 1;
        while (x >= 0 && y < 8) {
            if (queenPos.contains(x + "" + y)) {
                coordinates.add(x);
                coordinates.add(y);
                return coordinates;
            }
            x--;
            y++;
        }
        return coordinates;
    }

    private List<Integer> getBottomRightQueen(HashSet<String> queenPos, int startX, int startY) {
        List<Integer> coordinates = new ArrayList<>();
        int x = startX + 1;
        int y = startY + 1;
        while (x < 8 && y < 8) {
            if (queenPos.contains(x + "" + y)) {
                coordinates.add(x);
                coordinates.add(y);
                return coordinates;
            }
            x++;
            y++;
        }
        return coordinates;
    }

    private List<Integer> getBottomLeftQueen(HashSet<String> queenPos, int startX, int startY) {
        List<Integer> coordinates = new ArrayList<>();
        int x = startX + 1;
        int y = startY - 1;
        while (x < 8 && y >= 0) {
            if (queenPos.contains(x + "" + y)) {
                coordinates.add(x);
                coordinates.add(y);
                return coordinates;
            }
            x++;
            y--;
        }
        return coordinates;
    }

}
