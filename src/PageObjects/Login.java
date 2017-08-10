package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import ApplicationMap.ReadLocators;
import DataMap.ReadData;

public class Login {

	// @Test
	public void performLogin(WebDriver driver, String dsid) throws IOException {

		System.out.println("Login");

		ReadLocators rd1 = new ReadLocators("Login");

		ReadData dm = new ReadData("MemberDetails", dsid);

		driver.findElement(rd1.getLocator("TXB_LOGINNAME")).sendKeys(dm.getData("Login_Name"));
		driver.findElement(rd1.getLocator("TXB_PASSWORD")).sendKeys(dm.getData("Password"));
		driver.findElement(rd1.getLocator("BTN_LOGIN")).click();
	

	}

}
