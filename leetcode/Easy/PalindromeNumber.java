package com.company.leet;

public class PalindromeNumber {
    public static void main(String[] args) {
        PalindromeNumber obj = new PalindromeNumber();
        boolean palindrome = obj.isPalindrome(1221);
        System.out.println(palindrome);
    }

    public boolean isPalindrome(int x) {
        int originalNumber = x;
        int reversedNumber = 0;
        for (; x != 0; x /= 10) {
            reversedNumber = reversedNumber * 10 + (x % 10);
        }
        return originalNumber == reversedNumber;
    }
}
