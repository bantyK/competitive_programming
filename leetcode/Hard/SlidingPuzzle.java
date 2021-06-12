import java.util.*;

public class SlidingPuzzle {
    int[][] solvedBoard = new int[][]{{1, 2, 3}, {4, 5, 0}};

    public static void main(String[] args) {
        SlidingPuzzle slidingPuzzle = new SlidingPuzzle();
        System.out.println(slidingPuzzle.slidingPuzzle(new int[][]{{1, 2, 3}, {4, 0, 5}}));
        System.out.println(slidingPuzzle.slidingPuzzle(new int[][]{{4, 1, 2}, {5, 0, 3}}));
    }

    public int slidingPuzzle(int[][] board) {
        Map<Integer, List<Integer>> swaps = new HashMap<>();
        // the board is considered as a string -> [[0,1,2],[3,4,5]] => [0,1,2,3,4,5]
        // if the zero is at index = 0, it can be swapped with numbers at pos = 1 and 3
        swaps.put(0, Arrays.asList(1, 3));
        swaps.put(1, Arrays.asList(0, 2, 4));
        swaps.put(2, Arrays.asList(1, 5));
        swaps.put(3, Arrays.asList(0, 4));
        swaps.put(4, Arrays.asList(1, 3, 5));
        swaps.put(5, Arrays.asList(2, 4));

        String target = "123450";
        StringBuilder start = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                start.append(board[i][j]);
            }
        }
        int res = 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(start.toString());
        Set<String> seen = new HashSet<>();
        seen.add(start.toString());

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();

                if (target.equals(curr)) {
                    return res;
                }

                int zeroIndex = curr.indexOf('0');

                for (int swapIndex : swaps.get(zeroIndex)) {
                    String next = swapWithZeroPos(curr, swapIndex, zeroIndex);
                    if (seen.add(next)) {
                        queue.offer(next);
                    }
                }
            }
            res++;
        }

        return -1;
    }

    private String swapWithZeroPos(String str, int pos, int zeroIndex) {
        StringBuilder builder = new StringBuilder(str);
        char ch = builder.charAt(pos);
        builder.setCharAt(pos, '0');
        builder.setCharAt(zeroIndex, ch);
        return builder.toString();
    }
}