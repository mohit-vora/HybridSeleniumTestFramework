package pageObjects;

import org.testng.Assert;

import applicationMap.Locator;
import dataMap.Data;
import utils.BaseClass;
import utils.ReportLogger;
import utils.WebTable;

public class SystemToMemberPaymentConfirmation extends BaseClass {
	
	public static void verifyPaymentDetails(String memberId, String transactionId){
				
		Data dm1 = new Data("MemberDetails", memberId);
		Data dm2 = new Data("TransactionData", transactionId);
		Locator rd1 = new Locator("PaymentSystemToMember");
			
		WebTable table = createWebTable(rd1.getLocator("TBL_Transaction_confirmation"));
						
		String actualTransactionType = "";
		String actualLoginName = "";
		String actualFullName = "";
		String actualAmount = "";
		String actualDescription = "";
		
		for (int rowIndex=0;rowIndex<table.getRowSize();rowIndex++){
			if (table.getColumnSize(rowIndex)>1){
				String secondColumnData = table.getData(rowIndex, 1);
				switch (table.getData(rowIndex, 0).toLowerCase()){
	
					case "transaction type":
						actualTransactionType = secondColumnData;
						break;
						
					case "to":
						String[] to = secondColumnData.split("-");
						actualLoginName = to[0].trim();
						actualFullName = to[1].trim();
						break;
						
					case "amount":
						actualAmount = secondColumnData.split("Rs")[0].trim();
						break;
						
					case "description":
						actualDescription = secondColumnData;
						break;
					default:
						Assert.fail("Something is wrong in the confirmation table");
				
				}
			}
		}
		
		boolean checkTransactionType = dm2.getData("transaction_type").equalsIgnoreCase(actualTransactionType);
		boolean checkAmount = dm2.getData("transaction_amount").equalsIgnoreCase(actualAmount);
		boolean checkDescription = dm2.getData("transaction_description").equalsIgnoreCase(actualDescription);
		boolean checkLoginName = dm1.getData("login_name").equalsIgnoreCase(actualLoginName);
		boolean checkFullName = dm1.getData("full_name").equalsIgnoreCase(actualFullName);
		
		boolean masterCheck = checkTransactionType && checkAmount &&
				checkDescription && checkLoginName && checkFullName;
		if (masterCheck){
			driver.findElement(rd1.getLocator("btn_confirm_submit")).click();
			ReportLogger.info("Debit Amount test: Details confirmed");
		}
		else{
			String problems = "\n\n";
			if (!checkTransactionType){
				problems+="Transaction Type: ["+dm2.getData("transaction_type")+"]["+actualTransactionType+"]\n";
			}
			if (!checkAmount){
				problems+="Amount: ["+dm2.getData("transaction_amount")+"]["+actualAmount+"]\n";
			}
			if (!checkDescription){
				problems+="Description: ["+dm2.getData("transaction_description")+"]["+actualDescription+"]\n";
			}
			if (!checkLoginName){
				problems+="LoginName: ["+dm1.getData("login_name")+"]["+actualLoginName+"]\n";
			}
			if (!checkFullName){
				problems+="FullName: ["+dm1.getData("full_name")+"]["+actualFullName+"]\n";
			}
				
			
			Assert.fail("Debit Amount test failed: details could not be confirmed"+problems);
		}
	}
}
