package PageObjects;

import DataMap.*;
import Utils.dummy;
import java.io.IOException;
import org.openqa.selenium.WebDriver;

import ApplicationMap.ReadSheet;

public class Login  {

	
	   	
	    //@Test
        public void performLogin(WebDriver driver) throws IOException {	

        	System.out.println("Login");
        	
			ReadSheet rd1 = new ReadSheet("Login");
			

        	
        	
			Data_Map_Tryout dm = new Data_Map_Tryout("MemberDetails","MEM004");
			
			driver.findElement(rd1.getLocator("TXB_LOGINNAME")).sendKeys(dm.getData("Login_Name"));
			driver.findElement(rd1.getLocator("TXB_PASSWORD")).sendKeys(dm.getData("Password"));
			driver.findElement(rd1.getLocator("BTN_LOGIN")).click();





	}
                         

}
