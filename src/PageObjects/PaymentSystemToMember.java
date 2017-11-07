package PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import applicationMap.Locator;
import dataMap.Data;
import utils.BaseClass;
import utils.Validate;

public class PaymentSystemToMember extends BaseClass {

	public static void populateDetails(String memberId, String TXNID) throws InterruptedException{
		Locator memberPayment = new Locator("PaymentSystemToMember");

		Data memberDetails = new Data("MemberDetails",memberId);
		Data transaction = new Data("TransactionData", TXNID);
		
		driver.findElement(memberPayment.getLocator("TXB_Login")).sendKeys(memberDetails.getData("Login_name"));
		
		Thread.sleep(2000);
		
		//get all the list elements
		WebElement suggestionBox = driver.findElement(memberPayment.getLocator("SUG_Login_Name"));
				
		List<WebElement> suggestions = suggestionBox.findElements(By.tagName("li"));
		
		if (suggestions.size()>1){
			int len = memberDetails.getData("Login_name").length();
			//iterate and click on the correct one
			for (WebElement ele: suggestions){
				if (ele.getText().substring(0, len+1).equalsIgnoreCase(memberDetails.getData("Login_name")+" ")){
					ele.click();
					break;
				}
			}
		}
		
		driver.findElement(memberPayment.getLocator("TXB_amount")).sendKeys(transaction.getData("Transaction_amount"));		
		
		if (Validate.isElementPresent(memberPayment.getLocator("DIS_TXB_TransactionType"))){
			WebElement transactionTypeDropDown = driver.findElement(memberPayment.getLocator("LST_transactiontype"));
			new Select(transactionTypeDropDown).selectByVisibleText(transaction.getData("transaction_type"));
		}
				
		driver.findElement(memberPayment.getLocator("txb_description")).sendKeys(transaction.getData("transaction_description"));
		
		driver.findElement(memberPayment.getLocator("BTN_Submit")).click();
	}

}
