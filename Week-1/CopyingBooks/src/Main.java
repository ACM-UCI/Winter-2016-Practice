
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		for(int testNum = 0; testNum < tests; testNum++)
		{
			int numBooks = sc.nextInt();
			int numParts = sc.nextInt();
			int bookSizes[] = new int[numBooks];
			for(int i = 0; i < numBooks; i++)
			{
				bookSizes[i] = sc.nextInt();
			}
			
			solve(bookSizes, numParts); // O(n \log n) solution
		}
		sc.close();
	}
	
	public static void solve(int[] bookSizes, int numParts)
	{
		//Solution 2: Binary search between 0 and total number of pages to find the smallest working solution.
		//Complexity: O(n log P)
		int totalPages = 0;
		for(int i = 0; i < bookSizes.length; i++)
			totalPages += bookSizes[i];
		
		int start = 0;
		int end = totalPages;
		while(end - start > 1)
		{
			int currPages = (start + end)/2;
			int numScribes = assignBooks(bookSizes, currPages);

			//If there is no solution, or if we need too many scribes, increase the number of pages.
			if(numScribes < 0 || numScribes > numParts)
				start = currPages;
			//Otherwise, decrease the number of pages.
			else
				end = currPages;
		}
		
		//If the start number of pages works, we print it.
		int checkScribesStart = assignBooks(bookSizes, start);
		if(checkScribesStart < 0 || checkScribesStart > numParts)
			System.out.println(end);
		//Otherwise, we print the end number of pages.
		else
			System.out.println(start);
	}
	
	public static int assignBooks(int[] bookSizes, int maxPages)
	{
		int numScribes = 0;
		int currPages = 0;
		for(int i = 0; i < bookSizes.length; i++)
		{
			if(bookSizes[i] > maxPages)
			{
				//No solution, since no scribe can do the next book
				return -1;
			}
			else if(currPages + bookSizes[i] <= maxPages)
			{
				//Add current book to current scribe
				currPages += bookSizes[i];
			}
			else
			{
				//Add new scribe with current book
				numScribes++;
				currPages = 0;
			}
		}
		
		return numScribes;
	}
}
