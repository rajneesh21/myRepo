package com.rk;

public class RevString2 {

	public static void main(String[] args) {

		String str="abcdef";
		String rev="";
		char[] ch=str.toCharArray();
		for(int i=ch.length-1;i>=0;i--) {
			rev+=ch[i];
		}
		
		System.out.println(rev);
	}

}
