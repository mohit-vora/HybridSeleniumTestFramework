package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import ApplicationMap.ReadLocators;
import DataMap.ReadData;

public class MemberPaymentConfirmation {
	public void verifyPaymentToMember(WebDriver driver, String dsid) throws IOException {
		/**/
		ReadData dm = new ReadData("MemberPayment", dsid);
		ReadLocators rd1 = new ReadLocators("MemberPayment");

		driver.findElement(rd1.getLocator(""));
		driver.findElement(rd1.getLocator("ELM_TransactionAmount")).getText().equals(dm.getData("Transaction_Amount"));
		driver.findElement(rd1.getLocator("ELM_TransactionType")).getText().equals(dm.getData("Transaction_Type"));
		// List<WebElement> list12 = we.findElements(By.tagName("option"));
		new Select(we).selectByVisibleText("Savings to Current");
		driver.findElement(rd1.getLocator("ELM_TransactionDescription")).getText().equals(dm.getData("Transaction_Description7"));
		driver.findElement(rd1.getLocator("BTN_Submit")).click();
		driver.findElement(rd1.getLocator("BTN_Success_Submit")).click();

	}

}
