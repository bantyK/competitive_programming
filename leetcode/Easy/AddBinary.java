// 67 https://leetcode.com/problems/add-binary/
public class AddBinary {
    public static void main(String[] args) {
        AddBinary obj = new AddBinary();
        String s = obj.addBinary("11", "1");
        System.out.println(s);
    }

    public String addBinary(String a, String b) {
        StringBuilder builder = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';

            builder.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) builder.append(carry);
        return builder.reverse().toString();
    }
}