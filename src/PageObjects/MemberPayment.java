package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import ApplicationMap.ReadLocators;
import DataMap.ReadData;

public class MemberPayment {
	ReadLocators rd1 = new ReadLocators("MemberPayment");

	public void PopulatePaymenttoMember(WebDriver driver) {
		String Logged_user = driver.findElement(rd1.getLocator("ELM_LOGGEDUSER")).getText();
		System.out.println(Logged_user);
		
			/*Data Sheet doesnt have relevant data so i have hardcoded --Lakshmi*/
			//ReadData dm = new ReadData("MemberDetails", dsid);
			driver.findElement(rd1.getLocator("TXB_Name")).sendKeys("TestUser41");
			driver.findElement(rd1.getLocator("TXB_Amount")).sendKeys("230.00");
			WebElement we=driver.findElement(rd1.getLocator("LST_Transaction_Type"));
			//List<WebElement> list12 = we.findElements(By.tagName("option"));
			new Select(we).selectByVisibleText("Savings to Current");
			driver.findElement(rd1.getLocator("TXB_Description")).sendKeys("Miscellaneous Expenses");
			driver.findElement(rd1.getLocator("BTN_Submit")).click();
			driver.findElement(rd1.getLocator("BTN_Success_Submit")).click();

		

	}

}
