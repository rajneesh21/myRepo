package com.rk;
import java.util.Scanner;
public class EquiInvertTria 
{
public static void main(String[] args) 
{
	Scanner sc= new Scanner(System.in);
	System.out.println("Enter Limit");
	int n=sc.nextInt();
	
	for(int i=0;i<=n;i++)
	{
		
		for(int k=1;k<=i;k++)
		{
			System.out.print(" ");
		}
		
		for(int j=1;j<=n-i;j++)
		{
			System.out.print("* ");
		}
		
		System.out.println();
	}
}
}
