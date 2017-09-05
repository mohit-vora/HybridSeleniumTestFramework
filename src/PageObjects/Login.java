package PageObjects;

import java.io.IOException;
import ApplicationMap.ReadLocators;
import DataMap.ReadData;
import Utils.BaseClass;
import Utils.ReportLogger;

public class Login extends BaseClass{

    public static void performLogin(String dsid) throws IOException {
    		ReadLocators rd1 = new ReadLocators("Login");
            ReadData dm = new ReadData("MemberDetails", dsid);

            driver.findElement(rd1.getLocator("TXB_LOGINNAME")).sendKeys(dm.getData("LOGIN_NAME"));
            driver.findElement(rd1.getLocator("TXB_PASSWORD")).sendKeys(dm.getData("PASSWORD"));
            driver.findElement(rd1.getLocator("BTN_LOGIN")).click();
       
            
            ReportLogger.info("User has sucessfully logged-in");
            ReportLogger.pass("Login Action Performed");
       

    }

}