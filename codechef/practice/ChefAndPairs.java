//https://www.codechef.com/problems/CPAIRS
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class ChefAndPairs {
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        int oddNumbers, pairs, n;
        String input;
        String[] splitInput;
        for (int i = 0; i < t; i++) {
            n = scanner.nextInt();
            scanner.nextLine();
            input = scanner.nextLine();
            splitInput = input.split(" ");
            oddNumbers = 0;
            pairs = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (Integer.parseInt(splitInput[j]) % 2 == 0) {
                    pairs += oddNumbers;
                } else {
                    oddNumbers++;
                }
            }
            System.out.println(pairs);
        }

        scanner.close();
        System.exit(0);
    }
}