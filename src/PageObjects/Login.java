package PageObjects;

import DataMap.*;
import Utils.dummy;
import java.io.IOException;
import org.openqa.selenium.WebDriver;

import ApplicationMap.ReadLocators;

public class Login  {

	
	   	
	    //@Test
        public void performLogin(WebDriver driver) throws IOException {	

        	System.out.println("Login");
        	
			ReadLocators rd1 = new ReadLocators("Login");
			

        	
        	
			ReadData dm = new ReadData("MemberDetails","MEM004");
			
			driver.findElement(rd1.getLocator("TXB_LOGINNAME")).sendKeys(dm.getData("Login_Name"));
			driver.findElement(rd1.getLocator("TXB_PASSWORD")).sendKeys(dm.getData("Password"));
			driver.findElement(rd1.getLocator("BTN_LOGIN")).click();





	}
                         

}
