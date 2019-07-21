package com.company.leet;

import java.util.ArrayList;
import java.util.List;

public class SelfDividingNumber {

    public static void main(String[] args) {
        SelfDividingNumber obj = new SelfDividingNumber();
        System.out.println(obj.selfDividingNumbers(0, 10000));
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDividingNumber(i)) {
                result.add(i);
            }
        }
        return result;
    }

    public boolean isSelfDividingNumber(int number) {
        if(number == 0) return false;
        int remainder;
        int quotient = number;
        while (quotient > 0) {
            remainder = quotient % 10;
            if (remainder == 0)
                return false;
            else if (number % remainder != 0)
                return false;
            quotient = quotient / 10;
        }

        return true;
    }
}
