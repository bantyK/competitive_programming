//593 https://leetcode.com/problems/valid-square/
public class ValidSquare {
    public static void main(String[] args) {
        ValidSquare obj = new ValidSquare();

        System.out.println(obj.validSquare(new int[]{1, 0},
                new int[]{-1, 0},
                new int[]{0, 1},
                new int[]{0, -1})
        );

        System.out.println(obj.validSquare(
                new int[]{0, 0},
                new int[]{1, 1},
                new int[]{1, 0},
                new int[]{0, 1})
        );

        System.out.println(obj.validSquare(
                new int[]{0, 0},
                new int[]{0, 0},
                new int[]{0, 0},
                new int[]{0, 0})
        );
    }


    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {

        long maxLen = 0, nonMaxLen = 0;

        // There are 6 lengths possible, 4 sides and 2 diagonals

        // Diagonals have highest length and both diagonals should be of same length
        // 4 sides should be of same length
        long[] lengths = new long[]{
                length(p1, p2),
                length(p2, p4),
                length(p3, p4),
                length(p1, p3),
                length(p1, p4),
                length(p2, p3)
        };

        // maxLen is the length of diagonal
        for (int i = 0; i < 6; i++) {
            maxLen = Math.max(maxLen, lengths[i]);
        }

        int maxCount = 0;
        for (int i = 0; i < 6; i++) {
            if (lengths[i] == maxLen) {
                maxCount++;
            } else {
                nonMaxLen = lengths[i]; // non max length is the length of side
            }
        }

        if (maxCount != 2) return false;

        for (int i = 0; i < 6; i++) {
            if (lengths[i] != maxLen && lengths[i] != nonMaxLen) {
                // if the length is not equal to diagonals length, it must be equal to side's length
                return false;
            }
        }

        return nonMaxLen != 0; // 0 length square is not considered as a square
    }

    private long length(int[] p1, int[] p2) {
        return (long) Math.pow(p1[0] - p2[0], 2) + (long) Math.pow(p1[1] - p2[1], 2);
    }
}
