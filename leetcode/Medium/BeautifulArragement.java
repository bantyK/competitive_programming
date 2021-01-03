// 526 https://leetcode.com/problems/beautiful-arrangement/submissions/
public class BeautifulArragement {

    public static void main(String[] args) {
        BeautifulArragement obj = new BeautifulArragement();
        System.out.println(obj.countArrangement(3));
        System.out.println(obj.countArrangement(7));
        System.out.println(obj.countArrangement(8));
        System.out.println(obj.countArrangement(15));
    }

    int count = 0;

    public int countArrangement(int n) {
        count = 0;
        helper(n, 1, new boolean[n + 1]);
        return count;
    }

    private void helper(int n, int pos, boolean[] used) {
        if (pos > n) {
            // if we end up here, then all the numbers are in their right position,
            // hence, this is a valid combination, increment the count
            count += 1;
            return;
        }

        for (int i = 1; i <= n; i++) {
            // unused and a valid combination,
            // if the current position and number does not form a valid combination
            // no need to go  through that path.

            // If this is a valid combination, then we will go through the path
            if (!used[i] && (pos % i == 0 || i % pos == 0)) {
                used[i] = true;
                helper(n, pos + 1, used);
                used[i] = false;
            }
        }
    }

}
