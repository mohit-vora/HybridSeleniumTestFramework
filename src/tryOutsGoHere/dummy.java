package tryOutsGoHere;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dummy {
	

		
		@BeforeMethod
		public void bm(Method m){
			System.out.println("beforemethod");
			
			Parameter[] p = m.getParameters();
//			System.out.println(p[0].);
		}
		
		
		@AfterMethod
		public void am(){
			System.out.println("aftermethod");
		}
		
		@DataProvider(name="dp")
		public Object[][] dp(Method m){
			
			if (m.getName().equalsIgnoreCase("test1")){
				return new Object[][]{
					{"one"},
					{"two"}
				};
			}
			
			else
				return new Object[][]{
				{"one"},
				{"two"}
			};
			
			
			
		}
		
		@Test(dataProvider="dp")
		public void test1(String text){
			System.out.println("test1"+text);
			if (text.equalsIgnoreCase("one"))
				Assert.fail();
		}
		
		@Test(dataProvider="dp")
		public void test2(String text){
			System.out.println("test2");
		}
	
}
