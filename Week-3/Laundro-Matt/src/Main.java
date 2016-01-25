
import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int numTests = sc.nextInt();
		for(int test = 1; test <= numTests; test++)
		{
			//Read input
			long numLaundry = sc.nextInt();
			long numWash = sc.nextInt();
			long numDryer = sc.nextInt();
			long dryTime = sc.nextInt();
			
			long washTimes[] = new long[(int)numWash];
			for(int i = 0; i < numWash; i++)
				washTimes[i] = sc.nextInt();
			
			Arrays.sort(washTimes);
			long totWashTime;
			
			//Binary search to find total wash time
			long start = 0;
			long end = numLaundry*washTimes[0];
			while(end - start > 1)
			{
				long curr = (start + end)/2;
				if(canWash(numLaundry, curr, washTimes))
					end = curr;
				else
					start = curr;
			}
			
			if(canWash(numLaundry, start, washTimes))
				totWashTime = start;
			else
				totWashTime = end;
			
			//System.out.println("Total wash time: " + totWashTime);
			
			//Get list of times to start drying
			ArrayList<Long> toDry = new ArrayList<Long>();
			for(int i = 0; i < numWash; i++)
			{
				for(long j = washTimes[i]; j <= totWashTime; j += washTimes[i])
				{
					toDry.add(j);
				}
			}
			
			Collections.sort(toDry); 
			
			//Ignore list after the first numLaundry elements
			PriorityQueue<Long> toDryEvents = new PriorityQueue<Long>();
			for(int i = 0; i < numLaundry; i++)
			{
				toDryEvents.add(toDry.get(i));
				//System.out.println(t1oDry.get(i));
			}
			
			PriorityQueue<Long> dryerFinishEvents = new PriorityQueue<Long>();
			
			int toDryQueue = 0;
			int dryersUsed = 0;
			while(toDryEvents.size() > 0)
			{
				
				if(dryerFinishEvents.size() == 0 || toDryEvents.peek() < dryerFinishEvents.peek())
				{
					long next = toDryEvents.poll();
					if(dryersUsed < numDryer)
					{
						dryersUsed++;
						dryerFinishEvents.add(next + dryTime);
					}
					else
					{
						toDryQueue++;
					}
				}
				else
				{
					long next = dryerFinishEvents.poll();
					if(toDryQueue > 0)
					{
						toDryQueue--;
						dryerFinishEvents.add(next + dryTime);
					}
					else
					{
						dryersUsed--;
					}
				}
			}
			
			//Now we empty the dryer queue
			while(toDryQueue > 0)
			{
				long next = dryerFinishEvents.poll();
				dryerFinishEvents.add(next + dryTime);
				toDryQueue--;
			}
			
			//Now we've put everything into the dryers, and we just wait for it to finish.
			long totTime = 0;
			while(dryerFinishEvents.size() > 0)
				totTime = dryerFinishEvents.poll();
			
			System.out.println("Case #" + test + ": " + totTime);
		}
		
		sc.close();
	}
	
	public static boolean canWash(long numLaundry, long time, long[] washTimes)
	{
		long totWashed = 0;
		for(int i = 0; i < washTimes.length; i++)
		{
			totWashed += time/washTimes[i];
		}
		
		//System.out.println(time + ", " + totWashed);
		return (totWashed >= numLaundry);
	}
}
