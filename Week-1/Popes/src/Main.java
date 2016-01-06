
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
		
		//sol1(popeStarts, years); // O(n^2) solution (brute force)
		sol2(popeStarts, years); // O(n) solution (two pointers)
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
	
	//Two pointers, O(n)
	public static void sol2(int[] popeStarts, int years)
	{
		int p1 = 0;
		int p2 = 0;
		int maxPopes = 0;
		int bestStart = -1;
		int bestEnd = -1;
		while(p2 < popeStarts.length)
		{
			if(popeStarts[p2] - popeStarts[p1] < years)
			{
				if(p2 - p1 + 1 > maxPopes)
				{
					bestStart = popeStarts[p1];
					bestEnd = popeStarts[p2];
					maxPopes = p2 - p1 + 1;
				}
				p2++;
			}
			else
			{
				p1++;
			}
		}
		System.out.println(maxPopes + " " + bestStart + " " + bestEnd);
	}
}
