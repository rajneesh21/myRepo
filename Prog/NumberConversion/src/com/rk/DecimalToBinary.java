package com.rk;

public class DecimalToBinary {

	public static void main(String[] args) {

		int num=24;
		String bin="";
		while(num>0) {
			int r= num%2;
			bin=r+bin;
			num=num/2;
		}
		System.out.println(bin);
	}

}
