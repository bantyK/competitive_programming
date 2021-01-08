// 1416 https://leetcode.com/problems/restore-the-array/
public class RestoreArray {

    public static void main(String[] args) {
        RestoreArray obj = new RestoreArray();
        System.out.println(obj.numberOfArrays("1317", 2000));
    }

    public int numberOfArrays(String s, int k) {
        return helper(s, k, 0);
    }

    private int helper(String s, int k, int index) {
        if (index == s.length()) return 1;
        if (s.charAt(index) == '0') return 0;

        int ans = 0;
        int num = 0;
        for (int j = index; j < s.length(); j++) {
            num = num * 10 + (s.charAt(j) - '0');
            System.out.println(num);
            if (num > k) break;
            ans += helper(s, k, j + 1);
        }
        return ans;
    }
}
