package PageObjects;

import ApplicationMap.*;
import DataMap.*;
import Utils.dummy;

import java.io.IOException;
import org.openqa.selenium.WebDriver;

public class Login  {

	
	   	
	    //@Test
        public void performLogin(WebDriver driver) throws IOException {	

        	System.out.println("Login");
        	
//			ReadSheet rd1 = new ReadSheet(this.toString());
			

        	
        	
			Data_Map_Tryout dm = new Data_Map_Tryout();
			
			driver.findElement(dummy.map.get("TXB_LOGINNAME")).sendKeys(dm.getData("MEM004", "Login_Name"));
			driver.findElement(dummy.map.get("TXB_PASSWORD")).sendKeys(dm.getData("MEM004", "Password"));
			driver.findElement(dummy.map.get("BTN_LOGIN")).click();





	}
                         

}
