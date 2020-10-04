import java.util.*;

//670 https://leetcode.com/problems/maximum-swap/
public class MaximumSwaps {
    public static void main(String[] args) {
        MaximumSwaps obj = new MaximumSwaps();
        System.out.println(obj.maximumSwap(1239));
    }

    public int maximumSwap(int num) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        String numString = String.valueOf(num);
        for (int i = 0; i < numString.length(); i++) {
            indexMap.put(numString.charAt(i) - '0', i);
        }
        int max = num;
        for (int i = 0; i < numString.length(); i++) {
            if (numString.charAt(i) - '0' < 9) {
                for (int n = 9; n >= 0; n--) {
                    if (indexMap.containsKey(n) && indexMap.get(n) > i) {
                        char[] chars = numString.toCharArray();
                        char temp = chars[i];
                        chars[i] = (char) (n + '0');
                        chars[indexMap.get(n)] = temp;
                        int newNum = Integer.parseInt(new String(chars));
                        if (newNum > max) {
                            max = newNum;
                            break;
                        }
                    }
                }
            }
        }
        return max;
    }
}
