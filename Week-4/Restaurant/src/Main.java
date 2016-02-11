
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int numRestaurants = sc.nextInt();
		
		Reservation reservations[] = new Reservation[numRestaurants];
		
		int maxRightVal = 0;
		for(int i = 0; i < numRestaurants; i++)
		{
			int leftVal = sc.nextInt();
			int rightVal = sc.nextInt();
			reservations[i] = new Reservation(leftVal, rightVal);
			
			if(rightVal > maxRightVal)
				maxRightVal = rightVal;
		}
		sc.close();
		
		Arrays.sort(reservations);
		
		int maxReservations[] = new int[maxRightVal];
		for(int i = 0; i < maxRightVal; i++)
		{
			
		}
	}
}

class Reservation implements Comparable<Reservation>
{
	int leftVal;
	int rightVal;
	
	public Reservation(int l, int r)
	{
		leftVal = l;
		rightVal = r;
	}
	
	public int compareTo(Reservation otherRes)
	{
		return ((Integer)leftVal).compareTo(otherRes.leftVal);
	}
}
