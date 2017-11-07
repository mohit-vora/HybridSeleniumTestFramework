package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import applicationMap.Locator;
import dataMap.Data;
import utils.BaseClass;
import utils.ReportLogger;
import utils.Validate;

public class EnrollMember extends BaseClass {

	/*
	 * RegisterMenber method will enroll a new member from admin 
	 * This method accepts two parameters as details of new members and type of account
	 *   */
	public static void registerMember(String memberDetails, String accountType, String successMessage) {
	
			Locator rd1 = new Locator("RegisterMember");
			Data dm = new Data("MemberDetails", memberDetails);
			Data permissionGroup = new Data("UserAccountTypes",accountType);
			WebElement createMember = driver.findElement(rd1.getLocator("LST_CreateMember"));
			
			new Select(createMember).selectByVisibleText(permissionGroup.getData("ACCOUNT_TYPE"));
			
			driver.findElement(rd1.getLocator("TXB_LoginName")).sendKeys(dm.getData("LOGIN_NAME"));
			driver.findElement(rd1.getLocator("TXB_FullName")).sendKeys(dm.getData("FULL_NAME"));
			driver.findElement(rd1.getLocator("TXB_Email")).sendKeys(dm.getData("EMAIL_ADDRESS"));
			driver.findElement(rd1.getLocator("DOB")).sendKeys(dm.getData("DATE_OF_BIRTH"));
			driver.findElement(rd1.getLocator("M_Gender")).click();
			driver.findElement(rd1.getLocator("TXB_Address")).sendKeys(dm.getData("ADDRESS"));
			driver.findElement(rd1.getLocator("TXB_PostalCode")).sendKeys(dm.getData("POSTAL_CODE"));
			driver.findElement(rd1.getLocator("TXB_City")).sendKeys(dm.getData("CITY"));
			driver.findElement(rd1.getLocator("TXB_Phone")).sendKeys(dm.getData("PHONE_NUMBER"));
			driver.findElement(rd1.getLocator("TXB_MobilePhone")).sendKeys(dm.getData("MOBILE_NUMBER"));
			driver.findElement(rd1.getLocator("Checkbox")).click();
			driver.findElement(rd1.getLocator("TXB_password")).sendKeys(dm.getData("PASSWORD"));
			driver.findElement(rd1.getLocator("TXB_confirmpassword")).sendKeys(dm.getData("PASSWORD"));
		
			driver.findElement(rd1.getLocator("BTN_submit")).click();

			ReportLogger.info("In EnrollMember PageObjects");
			ReportLogger.pass("Registration of new member page populated successfully");
			
			Validate.popupAndAccept(successMessage);
	}
}