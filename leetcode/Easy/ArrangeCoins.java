//441 https://leetcode.com/problems/arranging-coins/

public class ArrangeCoins {
    public static void main(String[] args) {
        ArrangeCoins obj = new ArrangeCoins();
        System.out.println(obj.arrangeCoins(7));
        System.out.println(obj.arrangeCoins(1804289383));
    }

    public int arrangeCoins(int n) {
        if(n == 0) return 0;
        long low = 1;
        long high = n;

        while (low < high) {
            long mid = low + (high - low + 1) / 2;

            long req = mid * (mid + 1) / 2;
            if (req > n) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }

        return (int)low;
    }

}
