package PageObjects;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class demoTNG {
	
	@BeforeMethod
	public void bm()
	{
		System.out.println("inside before");
	}
	
	
	
	@AfterMethod
	public void AM()
	{
		System.out.println("inside after method");
	}
	
	
	@Test
	public void test1()
	{
		System.out.println("inside test");
	}
	
	

}
