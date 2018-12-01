
//https://www.codechef.com/problems/HS08TEST
import java.util.Scanner;

class CodeChefBegineer2 {

    static double printBalance(double accountBalance, double transactionAmount) {
        if (transactionAmount % 5 != 0 || transactionAmount > accountBalance) {
            return accountBalance;
        } else {
            return accountBalance - (transactionAmount + 0.5);
        }
    }

    public static void main(String[] args) {
        double accountBalance, transactionAmount;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        transactionAmount = Double.parseDouble(input.split(" ")[0]);
        accountBalance = Double.parseDouble(input.split(" ")[1]);

        System.out.println(printBalance(accountBalance, transactionAmount));
        scanner.close();
    }
}