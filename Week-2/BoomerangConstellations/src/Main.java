
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int numTests = sc.nextInt();
		for(int test = 1; test <= numTests; test++)
		{
			int numStars = sc.nextInt();
			int xStars[] = new int[numStars];
			int yStars[] = new int[numStars];
			//starEdges has a count of the number of adjacent edges to each star of each length
			ArrayList<HashMap<Integer, Integer>> starEdges = new ArrayList<HashMap<Integer,Integer>>();
			for(int i = 0; i < numStars; i++)
			{
				HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
				starEdges.add(temp);
			}
			
			for(int i = 0; i < numStars; i++)
			{
				xStars[i] = sc.nextInt();
				yStars[i] = sc.nextInt();
			}
			
			for(int i = 0; i < numStars; i++)
			{
				for(int j = 0; j < i; j++)
				{
					int xDiff = xStars[i] - xStars[j];
					int yDiff = yStars[i] - yStars[j];
					int distSq = xDiff*xDiff + yDiff*yDiff;
					
					if(starEdges.get(i).containsKey(distSq))
					{
						int prevCount = starEdges.get(i).get(distSq);
						starEdges.get(i).put(distSq, prevCount + 1);
					}
					else
					{
						starEdges.get(i).put(distSq, 1);
					}
					
					if(starEdges.get(j).containsKey(distSq))
					{
						int prevCount = starEdges.get(j).get(distSq);
						starEdges.get(j).put(distSq, prevCount + 1);
					}
					else
					{
						starEdges.get(j).put(distSq, 1);
					}
				}
			}
			
			int countBoom = 0;
			for(HashMap<Integer, Integer> currEdges : starEdges)
			{
				for(Integer length : currEdges.keySet())
				{
					int edgeCount = currEdges.get(length);
					countBoom += ((edgeCount)*(edgeCount - 1))/2;
				}
			}
			System.out.println("Case #" + test + ": " + countBoom);
		}
		sc.close();
	}
}
