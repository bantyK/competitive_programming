import java.util.*;
//8 https://leetcode.com/problems/string-to-integer-atoi/
public class Atoi {
    public static void main(String[] args) {
        Atoi obj = new Atoi();
        System.out.println(obj.atoi("123w"));
        System.out.println(obj.atoi("w123w"));
        System.out.println(obj.atoi(" 123"));
        System.out.println(obj.atoi(" 1 23"));
        System.out.println(obj.atoi("0-1"));
    }

    public int atoi(String str) {
        long res = 0;
        boolean negative = false;
        boolean started = false;
        Set<Character> acceptable = new HashSet<>();
        acceptable.add('+');
        acceptable.add('-');
        acceptable.add('0');
        acceptable.add('1');
        acceptable.add('2');
        acceptable.add('3');
        acceptable.add('4');
        acceptable.add('5');
        acceptable.add('6');
        acceptable.add('7');
        acceptable.add('8');
        acceptable.add('9');

        for(char c : str.toCharArray()) {
            if(c == ' ') {
                if(started) {
                    break;
                } else {
                    continue;
                }
            }

            if(acceptable.contains(c)) {
                started = true;
                if(c == '-') {
                    negative = true;
                } else if(c == '+') {
                    negative = false;
                } else {
                    signed = true;
                    res = res * 10 + (c - '0');
                    if(res >= Integer.MAX_VALUE) {
                        if(negative) res = Integer.MIN_VALUE;
                        else res = Integer.MAX_VALUE;
                        break;
                    }
                }
            } else {
                if(!started) return 0;
                else {
                    break;
                }
            }
        }

        if(res != Integer.MAX_VALUE || res != Integer.MIN_VALUE) {
            res = negative ? (-1 * res) : res;
        }
        return (int)res;
    }
    
}