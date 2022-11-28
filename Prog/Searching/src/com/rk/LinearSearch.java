package com.rk;

public class LinearSearch {
	
	public static int linearSearch(int[] arr, int x) {
		
		for(int i=0;i<=arr.length-1;i++) {
			if(x==arr[i]) {
				return i;
			}
		}
		
		return -1;
	}

	public static void main(String[] args) {
		int[] array= {6,3,9,1,0,83,12,5};
		
		int n= linearSearch(array, 1);
		
		if(n>=0) {
			System.out.println("Element found at index: "+n);
		}
		else {
			System.out.println("Element not found");
		}
		
	}

}
