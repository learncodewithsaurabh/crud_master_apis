package com.codewithsaurabh.crud_master_apis.controller;

//Java Program for Copy Constructor
import java.io.*;

class Test {
	// data members of the class.
	String name;
	int id;

	// Parameterized Constructor
	Test(String name, int id) {
		this.name = name;
		this.id = id;
	}

	// Copy Constructor
	Test(Test obj2) {
		this.name = obj2.name;
		this.id = obj2.id;
	}
}

class Demo {
	public static void main(String[] args) {
		// This would invoke the parameterized constructor.
		System.out.println("First Object");
		Test test1 = new Test("avinash", 68);
		System.out.println("TestName :" + test1.name + " and TestId :" + test1.id);

		System.out.println();

		// This would invoke the copy constructor.
		Test test2 = new Test(test1);
		System.out.println("Copy Constructor used Second Object");
		System.out.println("TestName :" + test2.name + " and TestId :" + test2.id);
	}
}
