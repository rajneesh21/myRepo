package com.rk;

public class NoOfChar2 {

	public static void main(String[] args) {

		String str="Welcome to Java";
		int count=0;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)!=' ')
				count++;
			
		}
		
		System.out.println("No. of caracter in given string is: "+count);
	}

}
