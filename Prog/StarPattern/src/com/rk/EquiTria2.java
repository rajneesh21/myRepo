package com.rk;

public class EquiTria2 {

	public static void main(String[] args) {

		int totalStars=5;
		int spaces=totalStars/2;
		int stars=1;
		
		for(int i=0;i<=totalStars/2;i++) {
			
			for(int j=1;j<=spaces;j++) {
				System.out.print(" ");
			}
			
			for(int k=1;k<=stars;k++) {
				System.out.print("*");
			}
			
			System.out.println();
			spaces--;
			stars+=2;
		}
	}

}
