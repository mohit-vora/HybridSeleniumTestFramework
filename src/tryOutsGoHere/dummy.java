package tryOutsGoHere;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dummy {

	
	
	@DataProvider(name="dp")
	public Object[][] getmeData(Method m)
	{	
		if (m.getName().equalsIgnoreCase("TEST1"))
			return new Object[][]{};
		else
		{		
		Object[][] dataArray = {
				{"TEST2"}
		};
		return dataArray;
		}	
	}
	@BeforeClass
	public void bc()
	{
		try{
			System.out.println("before class");
			int x=1/0;
		}
		
		catch(Exception e)
		{
			System.out.println("i am here");
			return;
		}

	}
	
	@AfterClass
	public void ac()
	{
		System.out.println("after class");
	}
	
	@BeforeSuite
	public void bfs()
	{
		System.out.println("inside bfs");
	}
	
	@AfterSuite
	public void afs()
	{
		System.out.println("inside afs");
	}
	
	@BeforeMethod
	public void bfm(){
		System.out.println("inside bfm");
	}
	
	@Test(dataProvider="dp")
	public void TEST1(String parm)
	{
		System.out.println("inside TEST1");
	}
	
	@Test(dataProvider="dp")
	public void TEST2(String parm)
	{		
		System.out.println("inside TEST2");
	}
	
	@Test(dataProvider="dp")
	public void TEST3(String parm)
	{
		System.out.println("inside TEST3");
	}
	
	@AfterMethod()
	public void afm()
	{
		System.out.println("inside after method");
	}
}
