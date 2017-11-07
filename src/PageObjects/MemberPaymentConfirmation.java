package PageObjects;

import applicationMap.Locator;
import dataMap.Data;
import utils.BaseClass;
import utils.ReportLogger;

public class MemberPaymentConfirmation extends BaseClass {
	/*
	 * verifyPaymentToMember method compares the data's populated from dataset and values generated in cyclos web page for payment confirmation
	 * -for successful confirmation this method performs payment else it lead back to member payment page.
	 * */
	public static void verifyPaymentToMember(String dsid1, String dsid2) {
		
		Boolean flag = true;
		Data dm1 = new Data("MemberDetails", dsid1);
		Data dm2 = new Data("TransactionData", dsid2);
		Locator rd1 = new Locator("MemberPayment");
		
		String name[] = driver.findElement(rd1.getLocator("ELM_To")).getText().split("-");
		String appValue = driver.findElement(rd1.getLocator("ELM_TransactionAmount")).getText();
		if (flag){			
			if (appValue.substring(0, appValue.length() - 4).equals(dm2.getData("Transaction_Amount"))) {
				if (driver.findElement(rd1.getLocator("ELM_TransactionDescription")).getText()
						.equals(dm2.getData("Transaction_Description"))) {
					if (driver.findElement(rd1.getLocator("ELM_TransactionType")).getText()
							.equals(dm2.getData("Transaction_Type"))) {
						if (name[0].trim().equals(dm1.getData("Login_Name"))) {
							if (name[1].trim().equals(dm1.getData("Full_Name"))) {
							} else {
								flag = false;
							}
						} else {
							flag = false;
						}
					} else {
						flag = false;
					}
				} else {
					flag = false;
				}
			} else {
				flag = false;
			}
		} else {
			flag = false;
		}
		if (flag) {
			driver.findElement(rd1.getLocator("BTN_Success_Submit")).click();
			ReportLogger.pass("Payment details Confirmed, clicking on Submit button");
		} else {
			driver.findElement(rd1.getLocator("BTN_Back")).click();
			ReportLogger.info("Payment details not matching");
		}
		ReportLogger.info("In MemberPaymentConfirmation PageObjects");
		ReportLogger.pass("Successfully confirms member payment confirmation page");
	}
}