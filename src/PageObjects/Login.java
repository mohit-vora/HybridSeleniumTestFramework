package PageObjects;

import java.io.IOException;

import org.testng.Assert;

import ApplicationMap.ReadLocators;
import DataMap.ReadData;
import Utils.BaseClass;
import Utils.ReportLogger;

public class Login extends BaseClass {

	public static void performLogin(String dsid) throws IOException {
		ReadLocators rd1 = new ReadLocators("Login");
		ReadData dm = new ReadData("MemberDetails", dsid);
		ReportLogger.info("In Login Page");
		driver.findElement(rd1.getLocator("TXB_LOGINNAME")).sendKeys(dm.getData("LOGIN_NAME"));
		driver.findElement(rd1.getLocator("TXB_PASSWORD")).sendKeys(dm.getData("PASSWORD"));
		driver.findElement(rd1.getLocator("BTN_LOGIN")).click();
		try{
		
		String loggedUser = driver.findElement(rd1.getLocator("ELM_LOGGEDUSER")).getText();
		System.out.println(loggedUser);
		String[] userDetails = loggedUser.split(" ", 5);
		if (userDetails[2].contains(dm.getdata("LOGIN_NAME"))) {
			ReportLogger.pass("Login Action Performed");
		}
		}catch (Exception e) {
			Assert.fail("LoginFails");
			// TODO: handle exception
		}
		
	}

}