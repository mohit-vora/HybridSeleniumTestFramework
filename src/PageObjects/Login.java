package PageObjects;

import java.io.IOException;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import ApplicationMap.ReadLocators;
import DataMap.ReadData;
import Utils.BrowserUtils;

public class Login extends BrowserUtils{

    public static void performLogin(String dsid) throws IOException {

//        System.out.println("Login");

        ReadLocators rd1 = new ReadLocators("Login");

        ReadData dm = new ReadData("MemberDetails", dsid);

        driver.findElement(rd1.getLocator("TXB_LOGINNAME")).sendKeys(dm.getData("LOGIN_NAME"));
        driver.findElement(rd1.getLocator("TXB_PASSWORD")).sendKeys(dm.getData("PASSWORD"));
        driver.findElement(rd1.getLocator("BTN_LOGIN")).click();

        logInfo("Login Successful");

    }

}