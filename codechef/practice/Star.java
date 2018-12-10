import java.util.Scanner;

public class Main {

	static void printStar(int n) {
  		if(n == 0) {
          return;
        } 
      	
      for(int i = 0; i < n; i++) {
        System.out.print("*");
      }
      System.out.println();
      printStar(n - 1);
      
    }
  
	public static void main(String[] args) {
	  Scanner scanner = new Scanner(System.in);
      int n = scanner.nextInt();
      scanner.nextLine();
      printStar(n);
	}

}