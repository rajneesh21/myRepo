package com.rk;

import java.util.Arrays;

public class PanagramOrNot {

	public static void main(String[] args) {
		
		boolean isPanagram=true;

		String setence="Pack my box with five dozen liquor jugs";
		
		String str=setence.replaceAll("\\s", "").toLowerCase();
		
		char[] ch=str.toCharArray();
		Arrays.sort(ch);
		char[] cc=new char[26];
		
		int n=0;
		boolean bool=false;
		for(int w=0;w<=ch.length-1;w++) {
			
			for(int x=0;x<=cc.length-1;x++) {
				
				if(cc[x]==ch[w]) {
					
					bool=true;
					break;
					
				}
				else {
					bool=false;
				}
			}
			if(bool==false) {
					cc[n]=ch[w];
					n++;
					}
			
			if(n>25)
				break;
				
			}
			
				
		
		
		Arrays.sort(cc);
		
		for(int i=0;i<=cc.length-1;i++) {
			
			for(int j=97;j<=122;j++) {
				if(cc[i]==j) {
					isPanagram=true;
					break;
				}else {
					isPanagram=false;
					
				}
				
			}
			if(isPanagram==false)
				break;
			
		}
		
		if(isPanagram==true)
			System.out.println("Panagram");
		else
			System.out.println("Not a Panagram");
	}

}
