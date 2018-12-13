
//https://www.codechef.com/problems/TRUEDARE

import java.util.Scanner;

class TruthDare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        int tr, dr, ts, ds;
        String tss, dss, trs, drs;

        for (int i = 0; i < t; i++) {
            tr = scanner.nextInt();
            scanner.nextLine();
            trs = scanner.nextLine();

            dr = scanner.nextInt();
            scanner.nextLine();
            drs = scanner.nextLine();

            ts = scanner.nextInt();
            scanner.nextLine();
            tss = scanner.nextLine();

            ds = scanner.nextInt();
            scanner.nextLine();
            dss = scanner.nextLine();

            // if there is a number present in tss which is not present in trs ||
            // if there is any number present in dss which is not present in tss -> Shyam
            // wins
            // otherwise Ram wins

            System.out.println(ramWins(trs, drs, tss, dss));

        }
    }

    static String ramWins(String truthTaskRam, String dareTaskRam, String truthTaskShyam, String dareTaskShyam) {
        for (String task : truthTaskShyam.split(" ")) {
            if (!truthTaskRam.contains(task)) {
                return "no";
            }
        }

        for (String task : dareTaskShyam.split(" ")) {
            if (!dareTaskRam.contains(task)) {
                return "no";
            }
        }

        return "yes";
    }
}