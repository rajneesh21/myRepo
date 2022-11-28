package com.rk;

import java.util.Scanner;

public class MShape 
{
public static void main(String[] args) 
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter Limit");
	int n=sc.nextInt();
	
	for(int i=0;i<=n;i++)
	{
		for(int j=0;j<=i;j++)
		{
			System.out.print("*");
		}
		
		for(int k=0;k<=n;k++)
		{
			if(i+k>=n)
			{
				System.out.print("*");
			}
			else
			{
				System.out.print(" ");
			}
			
		}
		
		System.out.println();
		n=n-1;
	}
}
}
