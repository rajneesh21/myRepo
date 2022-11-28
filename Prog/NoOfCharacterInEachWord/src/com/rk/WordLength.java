package com.rk;

public class WordLength {

	public static void main(String[] args) {

		String str="Welcome to Bengaluru Airport";
		str=str.concat(" ");
		
		int count=0;
		
		char[] ch=new char[str.length()];
		
		for(int i=0;i<str.length();i++) {

			for(int j=i;j<=i;j++) {
	
				if(str.charAt(i)!=' ') {
						ch[i]=str.charAt(i);
						count++;
						
					}
				else {
					String s= new String(ch).trim();
					System.out.println(s+" : "+count);
					count=0;
					ch=new char[str.length()];
					break;
				}
				
				}
			
		}
		
	
	}
	
	}
