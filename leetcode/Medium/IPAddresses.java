import java.util.*;

// 93 https://leetcode.com/problems/restore-ip-addresses/
public class IPAddresses {
    public static void main(String[] args) {
        IPAddresses obj = new IPAddresses();

        String inputStr = "25525511133";
        for (String s : obj.restoreIpAddresses(inputStr)) {
            System.out.println(s);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> results = new ArrayList<>();
        generator(s, results, "", 0, 0);
        return results;
    }

    private void generator(String s, String currentString, int currentIndexInString, int segmentCount, List<String> results) {
        // IP address will have 4 segments and the final result should must include all the string.
        if (segmentCount == 4 && currentIndexInString == s.length()) {
            // valid case.
            results.add(currentString.substring(0, currentString.length() - 1));
            return;
        }

        // they both have to be true at the same time, if anyone of them is true then that is an invalid case
        if (segmentCount == 4 || currentIndexInString == s.length()) return;

        char c = s.charAt(currentIndexInString);
        if (c == '0') {
            generator(s, currentString + c + ".", currentIndexInString + 1, segmentCount + 1, results);
        } else {

            for (int len = 1; len <= 3 && len + currentIndexInString <= s.length(); len++) {
                String substring = s.substring(currentIndexInString, currentIndexInString + len);
                int integerValue = Integer.parseInt(substring);
                if (integerValue >= 0 && integerValue <= 255) {
                    // under the range
                    generator(s, currentString + integerValue + ".", currentIndexInString + len, segmentCount + 1, results);
                } else {
                    break;
                }
            }
        }
    }
}
