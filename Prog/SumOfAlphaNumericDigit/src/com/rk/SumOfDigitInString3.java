package com.rk;

public class SumOfDigitInString3 {

	public static void main(String[] args) {

		String str="rajneesh017";
		int sum=0;
		char[] ch=str.toCharArray();
		for(int i=0;i<=ch.length-1;i++) {
			if(ch[i]>=48 && ch[i]<=57) {
				sum+=ch[i]-48;
			}
		}
		
		System.out.println(sum);
	}

}
