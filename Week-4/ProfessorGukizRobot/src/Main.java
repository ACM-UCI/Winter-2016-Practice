
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int x1 = sc.nextInt();
		int y1 = sc.nextInt();
		int x2 = sc.nextInt();
		int y2 = sc.nextInt();
		sc.close();
		
		System.out.println(Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1)));
	}
}
