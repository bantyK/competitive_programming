
//https://www.codechef.com/problems/HS08TEST

import java.util.Scanner;

class CodeChefBegineer2 {

    static double printBalance(double accountBalance, int transactionAmount) {
        double totalTransactionAmount = transactionAmount + 0.5;
        if (transactionAmount % 5 != 0 || totalTransactionAmount > accountBalance) {
            return accountBalance;
        } else {
            return accountBalance - totalTransactionAmount;
        }
    }

    public static void main(String[] args) {
        double accountBalance;
        int transactionAmount;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        transactionAmount = Integer.parseInt(input.split(" ")[0]);
        accountBalance = Double.parseDouble(input.split(" ")[1]);
        System.out.printf("%.2f\n", printBalance(accountBalance, transactionAmount));
        scanner.close();
    }
}