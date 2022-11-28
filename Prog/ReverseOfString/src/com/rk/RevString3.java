package com.rk;

public class RevString3 {

	public static void main(String[] args) {

		String st="Rajneesh";
		StringBuilder sb=new StringBuilder(st);
		sb.reverse();
		st=sb.toString();
		System.out.println(st);
	}

}
