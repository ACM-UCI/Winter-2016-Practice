
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int numTests = sc.nextInt();
		for(int test = 1; test <= numTests; test++)
		{
			int numBoxes = sc.nextInt();
			int maxSum = sc.nextInt();
			int boxVals[] = new int[numBoxes];
			for(int i = 0; i < numBoxes; i++)
			{
				boxVals[i] = sc.nextInt();
			}
			
			//Two pointers
			int p1 = 0;
			int p2 = 0;
			long currSum = boxVals[0];
			long sequences = 0;
			while(p2 < numBoxes)
			{
				if(currSum > maxSum)
				{
					//Count the number of valid sequences that start at p1
					sequences += (p2 - p1);
					currSum -= boxVals[p1];
					p1++;
				}
				else
				{
					p2++;
					if(p2 < numBoxes)
						currSum += boxVals[p2];
				}
			}
			
			//Every sequence starting at pointer at least p1 is now valid.
			long startsLeft = numBoxes - p1;
			sequences += (startsLeft*(startsLeft + 1))/2;
			
			System.out.println("Case #" + test + ": " + sequences);
		}
	}
}
