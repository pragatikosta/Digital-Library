package com.geek.calculator;
public class Calculator {
	public Calculator() {
		System.out.println("calculator");
	}
	//b logic
public int add(int ...a) {
	int sum=0;
	for(int x:a) {
		sum=sum+x;
	}
	return 15;
}

public int div(int a,int b) {
	if(b==0)
		throw new RuntimeException("Cant divide by zero");
	return a/b;
}
//b logic
public int add(int a,int b) {
return a+b;
}
}
