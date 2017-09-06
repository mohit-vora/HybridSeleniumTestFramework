package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ApplicationMap.ReadLocators;
import DataMap.ReadData;
import Utils.BaseClass;
import Utils.ReportLogger;

public class MemberPayment extends BaseClass {
	
  /*This method will populate the member payment details in cyclos page.
   * parameters - It accepts two parameters for member details and transaction details.
   */
	
	public static void populatePaymenttoMember(String dsid1, String dsid2){
		ReadLocators rd1 = new ReadLocators("MemberPayment");

		ReadData dm1 = new ReadData("MemberDetails", dsid1);

		ReadData dm2 = new ReadData("TransactionData", dsid2);	
		driver.findElement(rd1.getLocator("TXB_Name")).sendKeys(dm1.getData("LOGIN_NAME"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(rd1.getLocator("TXB_Amount")).sendKeys(dm2.getData("TRANSACTION_AMOUNT"));
		WebElement we = driver.findElement(rd1.getLocator("LST_Transaction_Type"));
		// List<WebElement> list12 = we.findElements(By.tagName("option"));
		new Select(we).selectByVisibleText(dm2.getData("TRANSACTION_TYPE"));
		driver.findElement(rd1.getLocator("TXB_Description")).sendKeys(dm2.getData("TRANSACTION_DESCRIPTION"));

		driver.findElement(rd1.getLocator("BTN_Submit")).click();

		ReportLogger.info("In PopulatePaymenttoMember pageobjects");
		ReportLogger.info("Sucessfully payment to member page has been populated");
		
	}

}