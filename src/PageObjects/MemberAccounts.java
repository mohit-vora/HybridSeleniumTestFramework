package PageObjects;

import org.testng.Assert;

import applicationMap.Locator;
import dataMap.Data;
import utils.BaseClass;
import utils.ReportLogger;
import utils.WebTable;

public class MemberAccounts extends BaseClass {

	static Locator accountLocators = new Locator("AccountOverview");

	
	public static double extractBalance(String TXNID, String accntTypeInTransaction){
		
		double balance = 0.0;
		String account = "";
		
		Data dm1 = new Data("TransactionData", TXNID);
		Data dm2 = new Data("TransactionType", dm1.getData("TRANSACTION_TYPE"));
		
		if (accntTypeInTransaction.equalsIgnoreCase("From")){
			account = dm2.getData("From_Account");
		}
		else if(accntTypeInTransaction.equalsIgnoreCase("To")){
			account = dm2.getData("To_Account");
		}
		else{
			Assert.fail("Incorrect Second argument, expected From/To, actual: "
					+accntTypeInTransaction);
		}
				
		if (driver.findElement(accountLocators.getLocator("ELM_AccountPane")).getText().contains("My accounts")) {
			WebTable accnTypes = new WebTable(driver.findElement(accountLocators.getLocator("TBL_MyAccountsInner")));
			for (int listindex = 1; listindex < accnTypes.getRowSize(); listindex++) {
				
				if (accnTypes.getData(listindex, 0).equalsIgnoreCase(account)) {
					String amount = accnTypes.getData(listindex, 2);
					String subStringAmount = amount.substring(0, amount.length() - 4).replace(",", "");
					balance = Double.parseDouble(subStringAmount);;
					break;
				}
			}
		} else if (driver.findElement(accountLocators.getLocator("ELM_SingleAccountPane")).getText()
				.contains("Account balance")) {
			String amount = driver.findElement(accountLocators.getLocator("ELM_SingleAccountBalance")).getText();
			String subStringAmount = amount.substring(0, amount.length() - 4).replace(",", "");
			Double amountDb = Double.parseDouble(subStringAmount);
			balance = amountDb;
		}
		ReportLogger.info("Account Balance has been extracted before transactions");
		
		return balance;
	}
}
