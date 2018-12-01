// use this class as a template, rename it accordingly
import java.util.Scanner;
class CodechefBegineer5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        String input;
        for(int i = 0; i < t; i++) {
            input = scanner.nextLine();
            int num1 = Integer.parseInt(input.split(" ")[0]);
            int num2 = Integer.parseInt(input.split(" ")[1]);
            System.out.println(num1 + num2);
        }
    }
}