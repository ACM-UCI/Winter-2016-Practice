
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		//Read in input
		Scanner sc = new Scanner(System.in);
		int totBooks = sc.nextInt();
		int numGenres = sc.nextInt();
		int numBooks[] = new int[numGenres];
		for(int i = 0; i < totBooks; i++)
		{
			int newGenre = sc.nextInt();
			numBooks[newGenre]++;
		}
		sc.close();
		
		//Check all pairs of genres
		int numWays = 0;
		for(int i = 0; i < numGenres; i++)
		{
			for(int j = 0; j < i; j++)
			{
				numWays += numBooks[i]*numBooks[j];
			}
		}
		
		System.out.println(numWays);
	}
}
