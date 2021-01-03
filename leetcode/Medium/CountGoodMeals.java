// 1711: https://leetcode.com/problems/count-good-meals/
public class CountGoodMeals {
	public int countPairs(int[] deliciousness) {
        Map<Integer, Integer> count = new HashMap<>();
        long res = 0;
        int n = deliciousness.length;

        for (int num : deliciousness) {
            int power = 1;
            // the highest possible power of 2 (integer) will be 2 ^ 32
            for (int i = 0; i < 32; i++) {
                int diff = power - num;
                if (count.containsKey(diff)) {
                    res += count.get(diff);
                    res %= 1000000007;
                }
                power *= 2;
            }

            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        return (int) res;
    }
}
