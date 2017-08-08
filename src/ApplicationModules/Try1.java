package ApplicationModules;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.*;
import Utils.BrowserUtils;



public class Try1 {
	
	WebDriver driver = null;	
	
	@BeforeMethod
	public void browser(){
		BrowserUtils bu = new BrowserUtils();
		this.driver = bu.openbrowserie();
		System.out.println("inside before method");
	}
    
//	@Test
//	public static void main(String[] args) throws Exception  {
//		Driverdemo dd=new Driverdemo();
//		dd.Login();
//		
//	}

}
