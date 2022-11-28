package com.rk;

import java.util.Scanner;

public class SwapUsingThirdVariable {

	public static void main(String[] args) {
		
		System.out.println("Enter First Number.. ");
		Scanner scan = new Scanner(System.in);
		int a= scan.nextInt();
		System.out.println("Enter Second Number");
		int b= scan.nextInt();
		
		System.out.println("First Number before swapping "+a);
		System.out.println("Second Number before swapping "+b);
		
		System.out.println("**********************************");
		
		int c=a;
		a=b;
		b=c;
		
		System.out.println("First Number After swapping "+a);
		System.out.println("Second Number After swapping "+b);
	}

}
