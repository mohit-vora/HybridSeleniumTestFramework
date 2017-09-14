package tryOutsGoHere;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dummy implements dummy1{
	
	public static void main(String[] args){
		
		System.setProperty("webdriver.chrome.driver", "C:\\DriverExecutable\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		
		System.out.println(driver);
		
//		String data = "one,2,3,,;two;;three;";
//				
//		int row = data.split(";").length;
//		int length = data.length();
//		boolean flag = true;
//		for (int i=0;i<length-1;i++)
//		{
//			if ((data.charAt(i)==';' && data.charAt(i+1)==';')||
//					(data.charAt(i)==',' && data.charAt(i+1)==',')){
//				data = data.substring(0,i)+data.substring(i+1,length);
//				length--;
//				i--;
//				flag=false;
//			}	
//		}	
//		if (data.substring(length-1).equals(";")||data.substring(length-1).equals(",")){
//			data=data.substring(0, length-1);
//			flag=false;
//		}
//		if (!flag){
//			System.out.println("You have probably an extra [;] or [,] somewhere in test data, assuming: "+data);
//		}
	}

	public void gettitle() {

		System.out.println("this is here");
	}

		
//		@BeforeMethod
//		public void bm(Method m){
//			System.out.println("beforemethod");
//			
//			Parameter[] p = m.getParameters();
////			System.out.println(p[0].);
//		}
//		
//		
//		@AfterMethod
//		public void am(){
//			System.out.println("aftermethod");
//		}
//		
//		@DataProvider(name="dp")
//		public Object[][] dp(Method m){
//			
//			if (m.getName().equalsIgnoreCase("test1")){
//				return new Object[][]{
//					{"one"},
//					{"two"}
//				};
//			}
//			
//			else
//				return new Object[][]{
//				{"one"},
//				{"two"}
//			};
//			
//			
//			
//		}
//		
//		@Test(dataProvider="dp")
//		public void test1(String text){
//			System.out.println("test1"+text);
//			if (text.equalsIgnoreCase("one"))
//				Assert.fail();
//		}
//		
//		@Test(dataProvider="dp")
//		public void test2(String text){
//			System.out.println("test2");
//		}
	
}
