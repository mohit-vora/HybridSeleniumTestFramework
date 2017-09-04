package PageObjects;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ApplicationMap.ReadLocators;
import DataMap.ReadData;
import Utils.BrowserUtils;
import Utils.ReportLogger;

public class EnrollMember extends BrowserUtils {

	public static void RegisterMember(String dsid) throws InterruptedException, IOException {
			ReadLocators rd1 = new ReadLocators("RegisterMember");

			WebElement Createmem = driver.findElement(By.xpath(".//*[@id='newMemberGroup']"));

			new Select(Createmem).selectByVisibleText("Full members");

			Thread.sleep(1000);
			// ReadData dm = new ReadData("MemberDetails","MEM001");
			ReadData dm = new ReadData("MemberDetails", dsid);
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
			Thread.sleep(1000);
			driver.findElement(rd1.getLocator("BTN_submit")).click();
			Thread.sleep(1000);

			ReportLogger.info("In EnrollMember PageObjects");
			ReportLogger.pass("Registration of new member page populated successfully");



	}
}