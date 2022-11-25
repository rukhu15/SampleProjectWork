package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGClass {
	@BeforeClass
	public void beforeClass() {
		System.out.println("before class");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("before method");
	}
	
	@Test
	public void test() {
		System.out.println("test method");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("hi");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("after class");
	}
}
