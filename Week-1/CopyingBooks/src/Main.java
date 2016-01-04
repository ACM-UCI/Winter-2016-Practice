
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
			
			sol1(bookSizes, numParts); // O(n^2) solution
			sol2(bookSizes, numParts); // O(n \log n) solution
		}
		sc.close();
	}
	
	public static void sol1(int[] bookSizes, int numParts)
	{
		//Solution 1: Try each initial starting sequence until we find one that works.
		//The maximum page assignment is one less than the sum of pages in the first (i + 1) books.
		//Complexity: O(n^2)
		int time = -1;
		for(int i = 1; i < bookSizes.length; i++)
		{
			time = assignBooks1(bookSizes, i, numParts);
			if(time > 0)
				break;
		}
		System.out.println(time);
	}
	
	//Returns -1 if this is not a solution, or this is a solution.
	public static int assignBooks1(int[] bookSizes, int startingBooks, int numParts)
	{
		int currPages = 0;
		for(int i = 0; i < startingBooks; i++)
		{
			currPages += bookSizes[i]; //Add the first startingBooks books for the first scribe
		}
		//If some scribe writes as much as if the first scribe took another book, then the first scribe
		//could just have taken another book anyway.
		int maxPages = currPages + bookSizes[startingBooks];
		
		//Assign all of the books. We never add a new book to the current scribe if be would surpass maxPages.
		int scribes = 1;
		int pagesNeeded = currPages;
		currPages = 0;
		for(int i = startingBooks; i < bookSizes.length; i++)
		{
			if(currPages + bookSizes[i] < maxPages)
				currPages += bookSizes[i]; //Add book for the current scribe
			else //Start over with new scribe
			{
				pagesNeeded = Math.max(pagesNeeded, currPages);
				currPages = bookSizes[i];
				scribes++;
			}
		}
		pagesNeeded = Math.max(pagesNeeded, currPages);

		//If we needed too many scribes, we return -1. Otherwise, we return our solution.
		if(scribes > numParts)
			return -1;
		else
			return pagesNeeded;
	}
	
	public static void sol2(int[] bookSizes, int numParts)
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
			int numScribes = assignBooks2(bookSizes, currPages);

			//If there is no solution, or if we need too many scribes, increase the number of pages.
			if(numScribes < 0 || numScribes > numParts)
				start = currPages;
			//Otherwise, decrease the number of pages.
			else
				end = currPages;
		}
		
		//If the start number of pages works, we print it.
		int checkScribesStart = assignBooks2(bookSizes, start);
		if(checkScribesStart < 0 || checkScribesStart > numParts)
			System.out.println(end);
		//Otherwise, we print the end number of pages.
		else
			System.out.println(start);
	}
	
	public static int assignBooks2(int[] bookSizes, int maxPages)
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
