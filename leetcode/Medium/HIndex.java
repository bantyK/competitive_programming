//275 https://leetcode.com/problems/h-index-ii/
public class HIndex {
    public static void main(String[] args) {
        HIndex obj = new HIndex();

        System.out.println(obj.hIndex(new int[]{5, 5, 5, 5, 5}));
        System.out.println(obj.hIndex(new int[]{1, 1, 1, 1, 1, 1}));
        System.out.println(obj.hIndex(new int[]{0, 1, 3, 4, 6}));
        System.out.println(obj.hIndex(new int[]{1, 2, 2, 3, 3, 3}));
        System.out.println(obj.hIndex(new int[]{0}));
    }

    /**
     * A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each
     * 0 1 3 4 5 6 7 8
     */
    public int hIndex(int[] citations) {
        int low = 0;
        int n = citations.length;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (citations[mid] == n - mid) {
                return citations[mid];
            } else if (citations[mid] > n - mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return n - low;
    }

}
