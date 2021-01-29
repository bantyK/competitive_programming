// 302 https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/
public class SmallestBlackPixelRectangle {
    public static void main(String[] args) {
        char[][] image = new char[][]{{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}};
        int x = 0;
        int y = 2;

        System.out.println(new SmallestBlackPixelRectangle().minArea(image, x, y));
    }

    /////// Using DFS ///////
    private int top, bottom, left, right;

    public int minArea(char[][] image, int x, int y) {

        top = x;
        bottom = x;
        left = y;
        right = y;

        dfs(image, x, y);

        return (right - left + 1) * (bottom - top + 1);
    }

    private void dfs(char[][] image, int r, int c) {
        if (r < 0 || r >= image.length) return;
        if (c < 0 || c >= image[0].length) return;
        if (image[r][c] == '0') return;

        top = Math.min(top, r);
        bottom = Math.max(bottom, r);
        left = Math.min(left, c);
        right = Math.max(right, c);

        image[r][c] = '0';

        dfs(image, r + 1, c);
        dfs(image, r - 1, c);
        dfs(image, r, c + 1);
        dfs(image, r, c - 1);
    }


    /////// Naive approach. O(mn) ///////
    public int minAreaNaive(char[][] image, int x, int y) {
        int top = x;
        int bottom = x;
        int left = y;
        int right = y;

        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[0].length; c++) {
                if (image[r][c] == '1') {
                    top = Math.min(top, r);
                    bottom = Math.max(bottom, r);
                    left = Math.min(left, c);
                    right = Math.max(right, c);
                }
            }
        }

        int width = right - left;
        int height = bottom - top;

        return height * width;
    }
}
