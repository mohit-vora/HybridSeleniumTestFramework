package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import ApplicationMap.ReadLocators;

public class Transactions {
	ReadLocators rd1 = new ReadLocators("RegisterMember");
	public  void PopulatePaymenttoMember(WebDriver driver){
		String Logged_user = driver.findElement(rd1.getLocator("ELM_LOGGEDUSER")).getText();
		System.out.println(Logged_user);
		String[] User = Logged_user.split(" ",5);
		if (User[2].equalsIgnoreCase("admin")){
			
		}else{
			driver.findElement(rd1.getLocator("TXB_Login")).sendKeys("TestUser01");
			
		}
		
	}




}
