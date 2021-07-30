//43 https://leetcode.com/problems/multiply-strings/
public class MultiplyStrings {
    public static void main(String[] args) {
        MultiplyStrings obj = new MultiplyStrings();
        System.out.println(obj.multiply("123", "456"));
    }

    public String multiply(String num1, String num2) {
        String res = "0";
        int times = 0;
        for (int i = num2.length() - 1; i >= 0; i--) {
            StringBuilder temp = new StringBuilder(multiply(num1, num2.charAt(i) - '0'));
            int t = times;
            while (t > 0) {
                temp.append("0");
                t--;
            }
            System.out.println(temp);
            res = add(res, temp.toString());
            times++;
        }

        return res;
    }

    private String multiply(String num1, int multiplier) {
        StringBuilder builder = new StringBuilder();
        int temp = 0;
        int carry = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            temp = ((num1.charAt(i) - '0') * multiplier) + carry;
            if (temp >= 10) {
                builder.append(temp % 10);
                carry = temp / 10;
            } else {
                builder.append(temp);
                carry = 0;
            }
        }
        if (carry > 0) builder.append(carry);

        return builder.reverse().toString();
    }

    private String add(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        int temp;
        while (i >= 0 && j >= 0) {
            temp = (num1.charAt(i) - '0') + (num2.charAt(j) - '0') + carry;
            if (temp >= 10) {
                builder.append(temp % 10);
                carry = 1;
            } else {
                builder.append(temp);
                carry = 0;
            }
            i--;
            j--;
        }

        while (i >= 0) {
            temp = (num1.charAt(i) - '0') + carry;
            if (temp >= 10) {
                builder.append(temp % 10);
                carry = 1;
            } else {
                builder.append(temp);
                carry = 0;
            }
            i--;
        }

        while (j >= 0) {
            temp = (num2.charAt(j) - '0') + carry;
            if (temp >= 10) {
                builder.append(temp % 10);
                carry = 1;
            } else {
                builder.append(temp);
                carry = 0;
            }
            j--;
        }
        if (carry > 0) builder.append(carry);

        return builder.reverse().toString();
    }
}