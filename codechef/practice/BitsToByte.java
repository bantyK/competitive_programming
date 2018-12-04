import java.util.Scanner;

//https://www.codechef.com/problems/BITOBYT
class BitsToByte {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int quotient = n / 26;
            int remainder = n % 26;

            if (remainder == 0) 
                System.out.println(0 + " " + 0 + " " + (long)Math.pow(2, quotient - 1));
            else if (remainder > 0 && remainder <= 2)
                System.out.println((long)Math.pow(2, quotient) + " " + 0 + " " + 0);
            else if (remainder > 2 && remainder <= 8)
                System.out.println(0 + " " + (long)Math.pow(2, quotient) + " " + 0);
            else if (remainder > 8 && remainder <= 16)
                System.out.println(0 + " " + 0 + " " + (long)Math.pow(2, quotient));
            else
                System.out.println((long)Math.pow(2, quotient + 2) + " " + 0 + " " + " ");
    }
}

    static Count checkBits(int N, Count count) {
        int time = N;
        if (time > 2) {
            count.nibbles = count.bits;
            count.bits = 0;
            count.bytes = 0;
            time -= 2;
            checkNibble(time, count);
        }

        return count;
    }

    private static void checkNibble(int time, Count count) {
        if (time > 8) {
            count.bits = 0;
            count.bytes = count.nibbles;
            count.nibbles = 0;
            time -= 8;
            checkBytes(time, count);
        }
    }

    private static void checkBytes(int time, Count count) {
        if (time > 16) {
            count.bits = count.bytes * 2;
            count.nibbles = 0;
            count.bytes = 0;
            time -= 16;
            checkBits(time, count);
        }
    }
}

class Count {
    long bits;
    long nibbles;
    long bytes;

    public Count(long bits, long nibbles, long bytes) {
        this.bits = bits;
        this.nibbles = nibbles;
        this.bytes = bytes;
    }
}
