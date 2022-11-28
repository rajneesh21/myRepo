package com.rk;

public class WordLength2 {

	public static void main(String[] args) {

		String str="welcome to Bengaluru airport";
		char ch[]= str.toCharArray();
		for(int i=0;i<ch.length;i++) {
			String s="";
			while(i<ch.length && ch[i]!=' ') {
				s=s+ch[i];
				i++;
			}
			
			if(s.length()>0)
				System.out.println(s+"--> "+s.length());
		}
		
	}

}
