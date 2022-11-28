package com.rk;

public class SimpleBubbleSort {
	
	public static void bubbleSort(int[] arr) {
		
		for(int i=0;i<arr.length-1;i++) {
			
			for(int j=i+1;j<arr.length;j++) {
				
				if(arr[i]>arr[j]) {
					int temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}
			}
		}
		for(int a:arr) {
			System.out.print(a+", ");
		}
		
	}

	public static void main(String[] args) {
		
		int array[]= {7,1,45,49,89,67,3,0,44};
		bubbleSort(array);
		
	}

}
