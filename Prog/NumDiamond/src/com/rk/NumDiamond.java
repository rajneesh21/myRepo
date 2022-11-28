package com.rk;

import java.util.Scanner;

public class NumDiamond 
{
	public static void main(String[] args) 
	{
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter odd number");
		int n= sc.nextInt();
		int spaces=n/2;
		int stars=1;
		
		
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=spaces;j++)
			{
				System.out.print(" ");
			}
			
			for(int k=1;k<=stars;k++)
			{ 
				
			System.out.print(k);
			
			}
			System.out.println();
			if(i<=n/2)
			{
				spaces--;
				stars+=2;
			}
			else
			{
				spaces++;
				stars-=2;
			}
		}
}
}