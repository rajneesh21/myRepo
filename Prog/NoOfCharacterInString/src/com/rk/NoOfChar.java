package com.rk;

public class NoOfChar {

	public static void main(String[] args) {

		String str="Rajneesh";
		str=str.replaceAll("\\s", "");
				
		System.out.println("No. of characters in given string is: "+str.length());
	}

}
