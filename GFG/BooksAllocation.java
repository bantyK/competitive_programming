import java.util.*;
// Books allocation problem using Binary search
// https://practice.geeksforgeeks.org/problems/allocate-minimum-number-of-pages/0
public class BooksAllocation {
    public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
        scanner.nextLine();

        for(int x = 0; x < t; x++) {
            int n = scanner.nextInt();
            scanner.nextLine();

            long[] books = new long[n];

            String[] line = scanner.nextLine().split(" ");
            for(int i = 0; i < n; i++) {
                books[i] = Long.parseLong(line[i]);
            }

            int k = scanner.nextInt();


            System.out.println(binarySearch(books,n, k));
        }
        scanner.close();
	}

	private static long binarySearch(long[] books, int n, int students) {
        long low = 0;
	    long totalPages = 0;

	    for(int i = 0; i < n; i++) {
	        totalPages += books[i];
	        low = Math.max(low, books[i]);
	    }

	    long high = totalPages;

	    long possibleAns = 0;
	    while(low < high) {
	        long mid = low + (high - low) / 2;

	        if(valid(books, students, mid)) {
	            possibleAns = mid;
	            high = mid - 1;
	        } else {
	            low = mid + 1;
	        }
	    }
	    return possibleAns;
	}

	private static boolean valid(long[] books, int k, long max) {
	    int students = 1;
	    long currentPages = 0;

	    for(int i = 0; i < books.length; i++) {
	        if(currentPages + books[i] > max) {
	            students++;
	            currentPages = books[i];
	            if(students > k) return false;
	        } else {
	            currentPages += books[i];
	        }
	    }
	    return true;
	}

}