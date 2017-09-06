package PageObjects;

import ApplicationMap.ReadLocators;
import DataMap.ReadData;
import Utils.BaseClass;
import Utils.LeftNavigationPane;
import Utils.ReportLogger;

public class Login extends BaseClass {
	
	/*
	 *This method performs Login function of cyclos application.
	 *Parameter - data set id from test case 
	 * */

	public static void performLogin(String dsid) {
		ReadLocators rd1 = new ReadLocators("Login");
		
		ReadData dm = new ReadData("MemberDetails", dsid);
		ReportLogger.info("In Login Page");
		driver.findElement(rd1.getLocator("TXB_LOGINNAME")).sendKeys(dm.getData("LOGIN_NAME"));
		driver.findElement(rd1.getLocator("TXB_PASSWORD")).sendKeys(dm.getData("PASSWORD"));
		driver.findElement(rd1.getLocator("BTN_LOGIN")).click();
		
		LeftNavigationPane.checkLoginSuccessful();
	}
	
	}
