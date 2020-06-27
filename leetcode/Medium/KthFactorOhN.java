//1492 https://leetcode.com/problems/the-kth-factor-of-n/
public class KthFactorOfN {
    public int kthFactor(int n, int k) {
        List<Integer> factors = new ArrayList<>();
        factors.add(1);
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
            }
        }

        return factors.size() < k ? -1 : factors.get(k - 1);
    }
}
