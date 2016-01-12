
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int numTests = sc.nextInt();
		for(int test = 1; test <= numTests; test++)
		{
			//Add an extra X for each row so we don't have to deal with special cases
			int length = sc.nextInt() + 1;
			String row1 = sc.next().trim() + "X";
			String row2 = sc.next().trim() + "X";
			
			//Lengths of current section of each row
			int row1Section = 0;
			int row2Section = 0;
			
			//Has the column for the guard in the current section been used?
			boolean row1Used = false;
			boolean row2Used = false;
			
			int guards = 0;
			
			for(int i = 0; i < length; i++)
			{
				if(row1.charAt(i) == '.' && row2.charAt(i) == '.')
				{
					row1Section++;
					row2Section++;
				}
				else if(row1.charAt(i) == 'X' && row2.charAt(i) == 'X')
				{
					if((row1Section == 1 && !row2Used) || (row2Section == 1 && !row1Used))
					{
						guards++;
					}
					else
					{
						if(row1Section > 0)
							guards++;
						
						if(row2Section > 0)
							guards++;
					}
					
					row1Section = 0;
					row2Section = 0;
					row1Used = false;
					row2Used = false;
				}
				else if(row1.charAt(i) == '.' && row2.charAt(i) == 'X')
				{
					if(row2Section == 1 && row1Section > 0 && !row1Used)
					{
						//Assign column for guard in row 1
						row1Used = true;
					}
					else if(row2Section != 0)
					{
						guards++;
					}
					
					row1Section++;
					row2Section = 0;
					row2Used = false;
				}
				else //if(row1.charAt(i) == 'X' && row2.charAt(i) == '.')
				{
					if(row1Section == 1 && row2Section > 0 && !row2Used)
					{
						//Assign column for guard in row 2
						row2Used = true;
					}
					else if(row1Section != 0)
					{
						guards++;
					}
					
					row1Section = 0;
					row1Used = false;
					row2Section++;
				}
			}
			
			System.out.println("Case #" + test + ": " + guards);
		}
	}
}
