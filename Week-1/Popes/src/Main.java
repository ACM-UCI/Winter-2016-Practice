
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int years = sc.nextInt();
		int numPopes = sc.nextInt();
		int popeStarts[] = new int[numPopes];
		for(int i = 0; i < numPopes; i++)
		{
			popeStarts[i] = sc.nextInt();
		}
		sc.close();
		
		sol1(popeStarts, years); // O(n^2) solution (brute force)
		sol2(popeStarts, years); // O(n \log n) solution (binary search)
		sol3(popeStarts, years); // O(n) solution (two pointers)
	}
	
	//Brute force, O(n^2)
	public static void sol1(int[] popeStarts, int years)
	{
		//Assign each pope to count for the next set of years
		int maxYear = popeStarts[popeStarts.length - 1];
		int popesInEndYear[] = new int[maxYear + 1];
		for(int i = 0; i < popeStarts.length; i++)
		{
			for(int j = 0; j < years; j++)
			{
				int currYear = popeStarts[i] + j;
				if(currYear <= maxYear)
				{
					popesInEndYear[currYear]++;
				}
			}
		}
		
		//Find the best interval
		int maxPopes = 0;
		for(int i = 0; i <= maxYear; i++)
		{
			if(popesInEndYear[i] > maxPopes)
				maxPopes = popesInEndYear[i];
		}
		
		System.out.println(maxPopes);
	}
	
	
	//Binary search, O(n \log n)
	public static void sol2(int[] popeStarts, int years)
	{
		
	}
	
	//Two pointers, O(n)
	public static void sol3(int[] popeStarts, int years)
	{
		
	}
}
