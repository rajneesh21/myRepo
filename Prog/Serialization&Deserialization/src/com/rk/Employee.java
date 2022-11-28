package com.rk;

import java.io.Serializable;

public class Employee implements Serializable{

	transient int a;
	static int b;
	String name;
	int age;
	public Employee(int a, int b, String name, int age) {
		super();
		this.a = a;
		this.b=b;
		this.name = name;
		this.age = age;
	}
	
	
}
