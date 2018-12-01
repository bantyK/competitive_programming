// use this class as a template, rename it accordingly
import java.util.Scanner;
class CodechefBegineer6 {

    static int sum(int val) {
        if(val / 10 == 0) return val;
        else return (val % 10) + sum(val / 10);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        int val;
        for(int i = 0; i < t; i++) {
            val = scanner.nextInt();
            System.out.println(sum(val));
        }
    }
}