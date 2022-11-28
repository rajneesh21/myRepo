package com.rk;
import java.util.Scanner;
public class PalindromeDiamond 
{
public static void main(String[] args) 
{
	Scanner sc= new Scanner(System.in);
	System.out.println("Enter  odd Number");
	int n= sc.nextInt();
	int sp=(n+1)/2;
	
	for(int i=1;i<=sp;i++)
	{
		for(int j=1;j<=n-i;j++)
		{
			System.out.print(" ");
		}
		
		for(int k=1;k<=i;k++)
		{
			
			System.out.print(k);
		}
		
		for(int m=i-1;m>=1;m--)
		{
			System.out.print(m);
		}
		System.out.println();
	}
	
	for(int i=sp-1;i>=1;i--)
	{
		for(int j=1;j<=n-i;j++)
		{
			System.out.print(" ");
		}
		
		for(int k=1;k<=i;k++)
		{
			System.out.print(k);
		}
		
		for(int m=i-1;m>=1;m--)
		{
			System.out.print(m);
		}
		System.out.println();
	}
}
}
