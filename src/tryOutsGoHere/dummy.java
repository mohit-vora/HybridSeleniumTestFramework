package tryOutsGoHere;

import java.lang.reflect.Method;

import org.testng.SkipException;
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
				{"TEST2"},
				{"TEST3"}
		};
		return dataArray;
		}
		
	}
	
	
	
	@Test(dataProvider="dp")
	public void TEST1(String parm)
	{
//		if (!parm.equalsIgnoreCase(Thread.currentThread().getStackTrace()[1].getMethodName()))
//		{
//			throw new SkipException("test1 has no flag");
//		}
		
		System.out.println("inside TEST1");

	}
	
	@Test(dataProvider="dp")
	public void TEST2(String parm)
	{
//		if (!parm.equalsIgnoreCase(Thread.currentThread().getStackTrace()[1].getMethodName()))
//		{
//			throw new SkipException("test2 has no flag");
//		}
		
		System.out.println("inside TEST2");
	}
	
	@Test(dataProvider="dp")
	public void TEST3(String parm)
	{
//		if (!parm.equalsIgnoreCase(Thread.currentThread().getStackTrace()[1].getMethodName()))
//		{
//			throw new SkipException("test3 has no flag");
//		}
//		
		System.out.println("inside TEST3");
	}
}
