package com.rk;

import java.util.Scanner;

public class Joking {
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int a=8;
		int b=4;
		int result=a/b;
		try {
			System.out.println("Enter your answer");
			int res=sc.nextInt();
			if(result!=res) {
			throw new FunnyException();
			}
			else {
				System.out.println("Great You have done it");
			}
		}
		catch(FunnyException fe) {
			fe.printStackTrace();
		}
		
		
		
		Joking j= new Joking();
		j=null;
	
		System.gc();
		
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("finalized called");
	}
	
	
}

