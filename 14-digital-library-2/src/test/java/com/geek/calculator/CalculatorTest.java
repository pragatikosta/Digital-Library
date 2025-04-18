package com.geek.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
class CalculatorTest {
	public CalculatorTest() {
		System.out.println("test");
		// TODO Auto-generated constructor stub
	}
	static Calculator c;//=new Calculator();
	@BeforeEach
	public void beforeEachTest() {
		System.out.println("before each");
		//some b logic
		//c=new Calculator();
	}
	@BeforeAll
	public static void beforeAll() {
		c=new Calculator();
	}
	@Test
	@DisplayName("TESTING ADD METHOD")
	void testAdd() {
	int r=c.add(1,2,3,4,5);
	assertEquals(15, r);
	}
	
	@Test
	@DisplayName("TESTING DIV METHOD Positive")
	void testDiv() {
		int r=c.div(10, 2);
		assertEquals(5, r);
	}
	@Test
	@DisplayName("TESTING DIV METHOD -ve")
	void testDivNegative() {
		assertThrows(RuntimeException.class, ()->c.div(10, 0) );
	}
	//@Test
	@ParameterizedTest
	@CsvSource({"5,5,10","3,5,8"})
	void testWithParams(int x,int y, int result) {
		assertEquals(result, c.add(x,y));
	}
	@ParameterizedTest
	@CsvFileSource(files = "mytest.csv",numLinesToSkip = 1)
	void testWithParamsWithCSVFile(int x,int y, int result) {
		assertEquals(result, c.add(x,y));
	}
}
