// 1894 https://leetcode.com/problems/find-the-student-that-will-replace-the-chalk/
public class StudentReplaceChalk {
    public int chalkReplacer(int[] chalk, int k) {
        long total = 0;
        int n = chalk.length;
        for (int c : chalk) {
            total += c;
        }
        long rounds = k / total;
        long remaining = k - (rounds * total);

        int i = 0;
        while (remaining >= chalk[i]) {
            remaining -= chalk[i];
            i = (i + 1) % n;
        }
        return i;
    }
}