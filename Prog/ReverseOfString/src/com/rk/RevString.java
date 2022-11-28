package com.rk;

public class RevString {

	public static void main(String[] args) {

		String str="abcdefghijkl";
		char[] ch=str.toCharArray();
		for(int i=0;i<ch.length/2;i++) {
			char t=ch[i];
			ch[i]=ch[ch.length-1-i];
			ch[ch.length-1-i]=t;
			
		}
		
		str=new String(ch);
		System.out.println("Reverse of string is: "+str);
	}

}
