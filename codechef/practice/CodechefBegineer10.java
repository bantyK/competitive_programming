import java.util.Scanner;

//https://www.codechef.com/problems/BITOBYT
class CodechefBegineer10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        int n;
        for (int i = 0; i < t; i++) {
            Count count = new Count(1, 0, 0);
            n = scanner.nextInt();
            checkBits(n, count);
            System.out.println(count.bits + " " + count.nibbles + " " + count.bytes);
        }
    }

    private static Count checkBits(int N, Count count) {
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
    int bits;
    int nibbles;
    int bytes;

    public Count(int bits, int nibbles, int bytes) {
        this.bits = bits;
        this.nibbles = nibbles;
        this.bytes = bytes;
    }
}
