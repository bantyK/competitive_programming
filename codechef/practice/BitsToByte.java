import java.util.Scanner;

//https://www.codechef.com/problems/BITOBYT
class BitsToBytes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            System.out.println(check(n));
        }
    }

    static String check(int n) {
        int quotient = n / 26;
        int remainder = n % 26;

        if (quotient == 0) {
            if (remainder <= 2) {
                return "1 0 0";
            } else if (remainder <= 10) {
                return "0 1 0";
            } else {
                return "0 0 1";
            }
        } else {
            if (remainder == 0)
                return (0 + " " + 0 + " " + (long) Math.pow(2, quotient - 1));
            else if (remainder > 0 && remainder <= 2)
                return ((long) Math.pow(2, quotient) + " " + 0 + " " + 0);
            else if (remainder > 2 && remainder <= 10)
                return (0 + " " + (long) Math.pow(2, quotient) + " " + 0);
            else
                return (0 + " " + 0 + " " + (long) Math.pow(2, quotient));
        }
    }

}

