import java.util.*;

//1291 https://leetcode.com/problems/sequential-digits/
public class SequentialDigits {
    public static void main(String[] args) {
        SequentialDigits obj = new SequentialDigits();
        System.out.println(obj.sequentialDigits(1000, 13000));
    }

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();

        for (int digit = 1; digit <= 9; digit++) {
            int num = digit;
            int next = digit;

            while (num <= high && next <= 9) {
                if (num >= low) {
                    result.add(num);
                }
                next += 1;
                num = num * 10 + next;
            }
        }

        Collections.sort(result);
        return result;
    }

}
