//941 https://leetcode.com/problems/valid-mountain-array/
public class ValidMountainArray {
    public static void main(String[] args) {
        ValidMountainArray obj = new ValidMountainArray();
        System.out.println(obj.validMountainArray(new int[]{0, 2, 3, 4, 5, 2, 1, 0}));
    }

    public boolean validMountainArray(int[] A) {
        int len = A.length;
        if (len < 3) return false;
        int start = 0;
        int end = len - 1;
        while (start < end) {
            if (A[start + 1] > A[start]) {
                start++;
            } else if (A[end - 1] > A[end]) {
                end--;
            } else {
                break;
            }
        }
        return start != 0 && end != len - 1 && start == end;
    }
}
