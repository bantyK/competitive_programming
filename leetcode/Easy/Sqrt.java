//69 https://leetcode.com/problems/sqrtx/
public class Sqrt {
    public static void main(String[] args) {
        Sqrt obj = new Sqrt();
        System.out.println(obj.mySqrt(8));
    }

    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int low = 0;
        int high = x;
        int ans = 0;
        int mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (mid <= x / mid) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
