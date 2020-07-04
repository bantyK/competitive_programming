import java.util.*;

//957 https://leetcode.com/problems/prison-cells-after-n-days/
public class PrisonDays {
    public static void main(String[] args) {
        PrisonDays obj = new PrisonDays();
        final int[] arr = obj.prisonAfterNDays(new int[]{1, 0, 0, 1, 0, 0, 1, 0}, 1000000000);
        System.out.println(Arrays.toString(arr));
    }

    public int[] prisonAfterNDays(int[] cells, int N) {
        Set<String> seen = new HashSet<>();
        int cycleLen = 0;
        boolean cycleFound = false;
        int[] next;
        for (int i = 0; i < N; ++i) {
            next = nextState(cells);
            String arrString = Arrays.toString(cells);
            if (seen.contains(arrString)) {
                cycleFound = true;
                break;
            } else {
                seen.add(arrString);
                ++cycleLen;
            }
            cells = next;
        }

        if (cycleFound) {
            N %= cycleLen;
            for(int i = 0; i < N; i++) {
                cells = nextState(cells);
            }
        }
        return cells;
    }

    private int[] nextState(int[] cells) {
        int[] temp = new int[cells.length];
        for(int i =1; i < 7; i++) {
            temp[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return temp;
    }
}
