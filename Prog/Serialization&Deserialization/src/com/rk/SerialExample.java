package com.rk;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialExample {
	
	public static void printdata(Employee e) {
		System.out.println("name= "+e.name);
		System.out.println("age= "+e.age);
		System.out.println("a= "+e.a);
		System.out.println("b= "+e.b);
	}

	public static void main(String[] args) {

		Employee emp= new Employee(2, 100, "Rahul", 32);
		String filename="Abc.txt";
		
		try {
			FileOutputStream file= new FileOutputStream(filename);
			ObjectOutputStream out= new ObjectOutputStream(file);
			out.writeObject(emp);
			out.close();
			file.close();
			
			System.out.println("Serialization done \n"+"Data before deserialization ");
			printdata(emp);
			emp.b=200;
		}
		catch(IOException ex) {
			
			System.out.println("IOException caught");
		}
		
		emp=null;
		
		try {
			FileInputStream file= new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			emp=(Employee)in.readObject();
			in.close();
			file.close();
			
			System.out.println("Deserialization done \n"+" Data after deserialization ");
			printdata(emp);
		}
		catch(IOException ex1) {
			System.out.println("IOException caught");
		}
		catch(ClassNotFoundException ex2) {
			System.out.println("ClassNotFoundException caught");
		}
		
	}

}
