import java.util.Scanner;

public class Main {

    static void printOdd(int n) {
        if(n < 1)
            return;
        if(n % 2 == 1)
            System.out.println(n);
        printOdd(n - 1);
    }

    static void printEven(int start, int n) {
        if (start > n)
            return;
        if (start % 2 == 0)
            System.out.println(start);
        printEven(start + 1, n);
    }
  
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        printOdd(n);
        printEven(2, n);
	}

}