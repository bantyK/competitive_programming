// 1694 https://leetcode.com/problems/reformat-phone-number/
public class ReformatPhoneNumber {

    public String reformatNumber(String number) {
        number = number.replaceAll("-", "");
        number = number.replaceAll("\\s", "");

        int len = number.length();
        if (len == 2) {
            return number;
        }

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (; i < len - 4; i += 3) {
            builder.append(number, i, i + 3).append("-");
        }

        if (len - i == 4) {
            builder.append(number.substring(i, i + 2)).append("-").append(number.substring(i + 2));
        } else if (len - i == 3 || len - i == 2) {
            builder.append(number.substring(i));
        }

        return builder.toString();
    }
}
