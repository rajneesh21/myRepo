package com.rk;

public class SumOfDigitInString {

	public static void main(String[] args) {

		String str="R2a4dfg8q1";
		int sum=0;

		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)>='0' && str.charAt(i)<='9') {
				sum+=str.charAt(i)-'0';
			}
		}
		System.out.println("sum of digit: "+sum);
	}

}
