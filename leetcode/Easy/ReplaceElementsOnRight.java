//1299 https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/
public class ReplaceElementsOnRight {
    public static void main(String[] args) {
        ReplaceElementsOnRight obj = new ReplaceElementsOnRight();
    }

    public int[] replaceElements(int[] arr) {
        int[] res = new int[arr.length];
        int n = arr.length - 1;
        res[n] = -1;
        int maxSoFar = arr[n];

        for (int i = n - 1; i >= 0; i--) {
            res[i] = maxSoFar;
            if (arr[i] > maxSoFar) {
                maxSoFar = arr[i];
            }
        }

        return res;
    }
}
