package com.rk;

public class BinarySearch {
	
	public static void binarySearch(int[] arr, int x) {
		int first=0;
		int last= arr.length-1;
		
			while(first<=last) {
			int middle=(first+last)/2;
				if(x==arr[middle]) {
					System.out.println("Element found at index: "+middle);
					break;
				}
				else if(x>arr[middle]) {
					first=middle+1;
				}
				else {
					last=middle-1;
				}
			}
	
		if(first>last) {
			System.out.println("Element not found");
		}
		
	}

	public static void main(String[] args) {
		int array[]= {12,26,27,30,44,67,79,90};
		
		binarySearch(array, 27);
		
	}

}
