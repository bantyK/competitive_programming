import java.util.Scanner;

class CodeChefBegineer3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int n = Integer.parseInt(line.split(" ")[0]);
        int k = Integer.parseInt(line.split(" ")[1]);
        int count = 0;
        for (int i = 0; i < n; i++) {
            int val = scanner.nextInt();
            // scanner.next();
            if (val % k == 0)
                count += 1;
        }

        System.out.println(count);
    }
}