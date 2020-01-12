import java.util.*;

// 166 https://leetcode.com/problems/fraction-to-recurring-decimal/
public class FractionToDecimal {
    public static void main(String[] args) {
        FractionToDecimal obj = new FractionToDecimal();
        System.out.println(obj.fractionToDecimal(1, Integer.MIN_VALUE));
    }

    public String fractionToDecimal(int num, int den) {
        if (den == 0) {
            return "inf";
        }
        if (num == 0) return "0";

        // converted to long to avoid overflow
        long numerator = num;
        long denominator = den;

        // this flag will be used later to add a negative sign at the front in case any of the values are negative
        boolean negative = numerator * denominator < 0;

        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);


        String result = "";
        long quo = numerator / denominator;
        result += quo;
        long rem = (numerator % denominator) * 10;
        if (rem == 0) {
            if (negative) {
                return "-" + result;
            } else {
                return result;
            }
        }
        result += ".";
        Map<Long, Integer> map = new HashMap<>();
        while (rem != 0) {
            if (map.containsKey(rem)) {
                int index = map.get(rem);
                String part1 = result.substring(0, index);
                String part2 = result.substring(index);
                result = part1 + "(" + part2 + ")";
                if (negative) {
                    return "-" + result;
                } else {
                    return result;
                }
            }
            result += rem / denominator;
            map.put(rem, result.length() - 1);
            rem = (rem % denominator) * 10;
        }
        if (negative) {
            return "-" + result;
        } else {
            return result;
        }
    }
}
