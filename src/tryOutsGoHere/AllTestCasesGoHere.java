package tryOutsGoHere;

import org.testng.annotations.Test;

public class AllTestCasesGoHere {

	String data = "thit";
	
	public AllTestCasesGoHere(String d) {
			data = d;
	}
	
	@Test()
	public void test1()
	{
		System.out.println("inside test1: " +data);
	}
	
	@Test
	public void test2()
	{
		System.out.println("inside test2: "+ data);
	}
	
	@Test
	public void test3()
	{
		System.out.println("inside test3: "+data);
	}
	
	
}
