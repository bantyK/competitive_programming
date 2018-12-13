
//https://www.codechef.com/problems/TRUEDARE
import java.util.*;

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
            // if there is any number present in dss which is not present in tss -> Shyam wins
            //otherwise Ram wins
            
            System.out.println(ramWins(tss, dss, trs, drs));
            
        }
    }

    static String ramWins(String tss, String dss, String trs, String drs) {
        Set<String> truthTaskSet = new HashSet<>();
        Set<String> dareTaskSet = new HashSet<>();

        for (String s : trs.split(" ")) {
            truthTaskSet.add(s);
        }

        for (String s : drs.split(" ")) {
            dareTaskSet.add(s);
        }

        for (String s : tss.split(" ")) {
            if (truthTaskSet.contains(s)) {
                truthTaskSet.remove(s);
            } else {
                return "no";
            }
        }

        for (String s : dss.split(" ")) {
            if (dareTaskSet.contains(s)) {
                dareTaskSet.remove(s);
            } else {
                return "no";
            }
        }

        return "yes";
    }
}