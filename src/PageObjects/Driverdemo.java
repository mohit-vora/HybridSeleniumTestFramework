package PageObjects;
import ApplicationMap.*;
import DataMap.*;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByAll;

import Utils.*;
public class Driverdemo  {

//WebDriver driver = null;

	//@Test
	//public void thisthis()
	//{
		//System.out.println("another test");
	//}
	/*@BeforeMethod
	public void browser(){
		BrowserUtils bu = new BrowserUtils();
		this.driver = bu.openbrowser();
		System.out.println("inside before method");
	}*/
	
//	@AfterMethod
//	public void AM()
//	{
//		System.out.println("inside after");
//	}
	
	public static Map<String, Map<String, ByAll>> input = new TreeMap<String, Map<String, ByAll>>();
	ReadSheet rd1 = new ReadSheet();
	   	
	
	    //@Test
        public void Login(WebDriver driver) throws Exception {	
		try {
			input = rd1.readsheet();			
			Data_Map_Tryout dm = new Data_Map_Tryout();
			
			driver.findElement(input.get("Login").get("TXB_LOGINNAME")).sendKeys(dm.getData("MEM004", "Login Name"));
			driver.findElement(input.get("Login").get("TXB_PASSWORD")).sendKeys(dm.getData("MEM004", "Password"));
			driver.findElement(input.get("Login").get("BTN_LOGIN")).click();

			//driver.quit();

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
                         

}
