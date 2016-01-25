
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int numTests = sc.nextInt();
		for(int test = 1; test <= numTests; test++)
		{
			int numSteps = sc.nextInt();
			int startMoney = sc.nextInt();
			int endMoney = sc.nextInt();
			double range = endMoney - startMoney;
			
			int costs[] = new int[numSteps];
			long totalCosts = 0;
			for(int i = 0; i < numSteps; i++)
			{
				costs[i] = sc.nextInt();
				totalCosts += costs[i];
			}
			
			//First build the number of yachts we can build for certain
			long fullYachts = startMoney/totalCosts;
			startMoney -= totalCosts*fullYachts;
			endMoney -= totalCosts*fullYachts;
			
			//Now trace the building of the next yacht. By the end startMoney will be 0.
			double average = 0;
			for(int i = 0; i < numSteps; i++)
			{
				if(startMoney < costs[i])
				{
					if(endMoney < costs[i])
					{
						average += ((startMoney + endMoney)/2.0)*(endMoney - startMoney)/range;
						startMoney = 0;
						endMoney = 0;
						break;
					}
					else
					{
						average += ((startMoney + costs[i])/2.0)*(costs[i] - startMoney)/range;
						endMoney -= costs[i];
						startMoney = 0;
					}
				}
				else
				{
					startMoney -= costs[i];
					endMoney -= costs[i];
				}
			}
			
			//Next we build as many full yachts as possible wth endMoney.
			if(endMoney > 0)
			{
				double possYachts = endMoney/totalCosts;
				for(int i = 0; i < numSteps; i++)
				{
					//We have a possYachts*costs[i]/range chance of running out with around costs[i]/2 money left
					average += (possYachts*costs[i]/range)*costs[i]/2.0;
				}
				endMoney -= possYachts*totalCosts;
			}
			
			//Now we construct the pieces of the last yacht, as far as we can
			//By the end, endMoney will be 0, and we'll be done.
			for(int i = 0; i < numSteps; i++)
			{
				if(endMoney < costs[i])
				{
					average += endMoney/range*(endMoney/2.0);
					break;
				}
				else
				{
					average += costs[i]/range*(costs[i]/2.0);
					endMoney -= costs[i];
				}
			}
			
			System.out.println("Case #" + test + ": " + average);
		}
	}
}
