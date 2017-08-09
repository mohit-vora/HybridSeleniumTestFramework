package PageObjects;

import ApplicationMap.*;
import DataMap.*;
import java.io.IOException;
import org.openqa.selenium.WebDriver;

public class Driverdemo  {

	
	   	
	    //@Test
        public void Login(WebDriver driver) throws Exception {	
		try {
			ReadSheet rd1 = new ReadSheet("Login");
			
			Data_Map_Tryout dm = new Data_Map_Tryout();
			
			driver.findElement(rd1.get("TXB_LOGINNAME")).sendKeys(dm.getData("MEM004", "Login_Name"));
			driver.findElement(rd1.get("TXB_PASSWORD")).sendKeys(dm.getData("MEM004", "Password"));
			driver.findElement(rd1.get("BTN_LOGIN")).click();

			//driver.quit();

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
                         

}
