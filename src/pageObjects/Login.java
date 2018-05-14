package pageObjects;

import applicationMap.Locator;
import dataMap.Data;
import utils.BaseClass;
import utils.LeftNavigationPane;
import utils.ReportLogger;

public class Login extends BaseClass {
	
	/*
	 *This method performs Login function of cyclos application.
	 *Parameter - data set id from test case 
	 * */

	public static void performLogin(String dsid) {          
		Locator rd1 = new Locator("Login");
		
		Data dm = new Data("MemberDetails", dsid);
		ReportLogger.info("In Login Page");
		driver.findElement(rd1.getLocator("TXB_LOGINNAME")).sendKeys(dm.getData("LOGIN_NAME"));
		driver.findElement(rd1.getLocator("TXB_PASSWORD")).sendKeys(dm.getData("PASSWORD"));
		driver.findElement(rd1.getLocator("BTN_LOGIN")).click();
		
		LeftNavigationPane.checkLoginSuccessful();
	}
	
	}


