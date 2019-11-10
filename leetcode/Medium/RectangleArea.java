package solutions.medium;

//https://leetcode.com/problems/rectangle-area/
public class RectangleArea {
    public static void main(String[] args) {
        RectangleArea obj = new RectangleArea();

        System.out.println(
                obj.computeArea(-2, -2, 2, 2, -2, -2, 2, 2) == 16
        );
    }

    private int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = Math.abs((C - A) * (D - B));
        int area2 = Math.abs((G - E) * (H - F));

        int left = Math.max(A, E);
        int right = Math.min(C, G);

        int top = Math.min(D, H);
        int bottom = Math.max(B, F);

        int overlapingArea = 0;

        if (right > left && top > bottom) {
            overlapingArea = (right - left) * (top - bottom);
        }

        return area1 + area2 - overlapingArea;
    }
}
