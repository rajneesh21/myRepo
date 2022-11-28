package com.rk;

import java.util.Scanner;

public class SwapWithoutThirdVariable {

	public static void main(String[] args) {

		System.out.println("Enter First Number.. ");
		Scanner scan = new Scanner(System.in);
		int a= scan.nextInt();
		System.out.println("Enter Second Number");
		int b= scan.nextInt();
		
		System.out.println("First Number before swapping "+a);
		System.out.println("Second Number before swapping "+b);
		
		a=a+b;
		b=a-b;
		a=a-b;
		
		System.out.println("First Number After swapping "+a);
		System.out.println("Second Number After swapping "+b);
	}

}
