package PageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import ApplicationMap.ReadLocators;
import DataMap.ReadData;

public class MemberPayment {
	ReadLocators rd1 = new ReadLocators("MemberPayment");

	public void PopulatePaymenttoMember(WebDriver driver,String dsid) throws IOException {
		
			
			ReadData dm = new ReadData("MemberPayment", dsid);
			driver.findElement(rd1.getLocator("TXB_Name")).sendKeys(dm.getData("Transaction_Login"));
			driver.findElement(rd1.getLocator("TXB_Amount")).sendKeys(dm.getData("Transaction_Amount"));
			WebElement we=driver.findElement(rd1.getLocator("LST_Transaction_Type"));
			//List<WebElement> list12 = we.findElements(By.tagName("option"));
			
			/*Transaction type is hardcoded as option and value is different in source page*/
			new Select(we).selectByVisibleText("Savings to Current");
			driver.findElement(rd1.getLocator("TXB_Description")).sendKeys(dm.getData("Transaction_Description"));
			driver.findElement(rd1.getLocator("BTN_Submit")).click();
			driver.findElement(rd1.getLocator("BTN_Success_Submit")).click();

		

	}

}
