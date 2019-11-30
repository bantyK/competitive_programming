import java.util.*;

// 5112 https://leetcode.com/problems/hexspeak/
public class HexSpeak {
    public static void main(String[] args) {
        HexSpeak obj = new HexSpeak();

        System.out.println(toHexspeak("619879596177"));
    }

    public static String toHexspeak(String num) {
        String hexString = Long.toHexString(Long.parseLong(num)).toUpperCase();
        List<Character> allowedChars = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'I', 'O');

        char[] charArray = hexString.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '1') charArray[i] = 'I';
            else if(c == '0') charArray[i] = 'O';
        }

        for(char c : charArray) {
            if(!allowedChars.contains(c)) {
                return "ERROR";
            }
        }

        return new String(charArray);

    }



}
