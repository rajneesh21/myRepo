package com.rk;

public class BinaryToDecimal {

	public static void main(String[] args) {

		long bin= 11000;
		long dec=0;
		int count=0;
		
		while(bin>0) {
			long r=bin%10;
			dec=dec+r*power(2,count);
			count++;
			bin/=10;
		}
		System.out.println("Decimal Equivalent: "+dec);
	}
	
	static int power(int n, int p) {
		
		int pw=1;
		while(p>0) {
			pw=pw*n;
			p--;
		}
		return pw;
	}

}
