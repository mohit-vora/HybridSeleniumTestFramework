package PageObjects;

import ApplicationMap.ReadLocators;
import DataMap.ReadData;
import Utils.BaseClass;
import Utils.ReportLogger;

public class MemberPaymentConfirmation extends BaseClass {
	/*
	 * verifyPaymentToMember method compares the data's populated from dataset and values generated in cyclos web page for payment confirmation
	 * -for successful confirmation this method performs payment else it lead back to member payment page.
	 * */
	public static void verifyPaymentToMember(String dsid1, String dsid2) {
		
		Boolean flag = true;
		ReadData dm1 = new ReadData("MemberDetails", dsid1);
		ReadData dm2 = new ReadData("TransactionData", dsid2);
		ReadLocators rd1 = new ReadLocators("MemberPayment");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			ReportLogger.resultPass("Payment details Confirmed, clicking on Submit button");
		} else {
			driver.findElement(rd1.getLocator("BTN_Back")).click();
			ReportLogger.info("Payment details not matching");
		}
		ReportLogger.info("In MemberPaymentConfirmation PageObjects");
		ReportLogger.pass("Successfully confirms member payment confirmation page");
	}
}