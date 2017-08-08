package PageObjects;

import org.openqa.selenium.WebDriver;

public class VerifyPopUp {
	
	public String PopUpAccept(WebDriver driver,String PopUpMessage){
		PopUpMessage = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();	
		//System.out.println(PopUpMessage);
		return PopUpMessage;

		
	}

}
