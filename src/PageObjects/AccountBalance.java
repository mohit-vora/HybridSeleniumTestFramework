package PageObjects;

import java.text.DecimalFormat;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ApplicationMap.ReadLocators;
import DataMap.ReadData;
import Utils.BaseClass;
import Utils.LeftNavigationPane;
import Utils.ReportLogger;

public class AccountBalance extends BaseClass {
    //DecimalFormat will round of the amount to two decimal values
	DecimalFormat df = new DecimalFormat(".##");
	static String fromaccnt = null;
	static String toaccnt = null;
	static double amountDebit = 0;
	static double amountCredit = 0;
	static double transactionAmount = 0;
	static double fromAccountBalance = 0;
	static double toAccountBalance = 0;

	ReadLocators accountLocators = new ReadLocators("AccountOverview");
	
	/*
	 * XtractAccountBalance method is to extract type of account and its corresponding current balance before transaction
	 * this method calculates the amount to be in current balance after performing transaction for further verification.
	 * */
	public void xtractAccountBalance(String dsid1, String account) {
		
		if (!((account.contentEquals("FromAccount") || account.contentEquals("ToAccount")))) {
			ReportLogger.fail("The second argument in xtractAccount Balance method can be either FromAccount or ToAccount");
			Assert.fail("Fails in argumet passing");
		} else {
			ReadData dm1 = new ReadData("TransactionData", dsid1);
			ReadData dm2 = new ReadData("TransactionType", dm1.getData("TRANSACTION_TYPE"));
			fromaccnt = dm2.getData("From_Account");
			toaccnt = dm2.getData("To_Account");
			transactionAmount = Double.parseDouble((dm1.getData("TRANSACTION_AMOUNT")));
			LeftNavigationPane.navigateTo("Acco", "Account Information");
			if (driver.findElement(accountLocators.getLocator("ELM_AccountPane")).getText().contains("My accounts")) {
				WebElement accnTypes = driver.findElement(accountLocators.getLocator("TBL_MyAccountsInner"));
				List<WebElement> rows = accnTypes.findElements(By.tagName("tr"));
				for (int listindex = 1; listindex < rows.size(); listindex++) {
					List<WebElement> cols = rows.get(listindex).findElements(By.tagName("td"));
					String amount = cols.get(2).getText();
					String subStringAmount = amount.substring(0, amount.length() - 4).replace(",", "");
					Double amountDb = Double.parseDouble(subStringAmount);
					if (account.equalsIgnoreCase("FromAccount")) {
						if (cols.get(0).getText().equalsIgnoreCase(fromaccnt)) {
							fromAccountBalance = amountDb;
							break;
						}
					} else if (account.equalsIgnoreCase("ToAccount")) {
						if (cols.get(0).getText().equalsIgnoreCase(toaccnt)) {
							toAccountBalance = amountDb;
							break;
						}
					}
				}
			} else if (driver.findElement(accountLocators.getLocator("ELM_SingleAccountPane")).getText()
					.contains("Account balance")) {
				String amount = driver.findElement(accountLocators.getLocator("ELM_SingleAccountBalance")).getText();
				String subStringAmount = amount.substring(0, amount.length() - 4).replace(",", "");
				Double amountDb = Double.parseDouble(subStringAmount);
				if (account.equalsIgnoreCase("FromAccount")) {
					fromAccountBalance = amountDb;
				} else if (account.equalsIgnoreCase("ToAccount")) {
					toAccountBalance = amountDb;
				}
			}
			ReportLogger.info("Account Balance has been extracted before transactions");

			if (account.equalsIgnoreCase("FromAccount")) {
				amountDebit = fromAccountBalance - transactionAmount;
			} else if (account.equalsIgnoreCase("ToAccount")) {
				amountCredit = toAccountBalance + transactionAmount;
			}
			ReportLogger.info("In extract accountbalance method of AccountBalance PageObjects");
			ReportLogger.pass("Credited or Debited amount has been calculated for transaction verfication");
		}
		
		
	}

	/*
	 * This is the override method
	 * this method to extract type of account and its corresponding current balance after transaction*/
	public void xtractAccountBalance(String account) {

		if (!((account.contentEquals("FromAccount") || account.contentEquals("ToAccount")))) {
			ReportLogger.fail("The argument in XtractAccount method can be either FromAccount or ToAccount");
			Assert.fail();
		} else {
		LeftNavigationPane.navigateTo("Account", "Account Information");
		if (driver.findElement(accountLocators.getLocator("ELM_AccountPane")).getText().contains("My accounts")) {
			WebElement accnTypes = driver.findElement(accountLocators.getLocator("TBL_MyAccountsInner"));
			List<WebElement> rows = accnTypes.findElements(By.tagName("tr"));
			for (int listindex = 1; listindex < rows.size(); listindex++) {
				List<WebElement> cols = rows.get(listindex).findElements(By.tagName("td"));
				String amount = cols.get(2).getText();
				String subStringAmount = amount.substring(0, amount.length() - 4).replace(",", "");
				Double amountDb = Double.parseDouble(subStringAmount);
				if (account.equalsIgnoreCase("FromAccount")) {
					if (cols.get(0).getText().equalsIgnoreCase(fromaccnt)) {
						fromAccountBalance = amountDb;
						break;
					}

				} else if (account.equalsIgnoreCase("ToAccount")) {
					if (cols.get(0).getText().equalsIgnoreCase(toaccnt)) {
						toAccountBalance = amountDb;
						break;
					}
				}
			}
		} else if (driver.findElement(accountLocators.getLocator("ELM_SingleAccountPane")).getText().contains("Account balance")) {
			String amount = driver.findElement(accountLocators.getLocator("ELM_SingleAccountBalance")).getText();
			String subStringAmount = amount.substring(0, amount.length() - 4).replace(",", "");
			Double amountDb = Double.parseDouble(subStringAmount);
			if (account.equalsIgnoreCase("FromAccount")) {
				fromAccountBalance = amountDb;
			} else if (account.equalsIgnoreCase("ToAccount")) {
				toAccountBalance = amountDb;
			}
		}
		ReportLogger.info("In extract accountbalance method of AccountBalance PageObjects");
		ReportLogger.pass("Account Balance has been extracted after transactions");
	}
   }		

	// verifiyDebitAccount method verifies that amount has been successfully debited or not after transaction
	public void verifiyDebitAccount() {
		if (df.format(amountDebit).equals(df.format(fromAccountBalance))) {
			ReportLogger.resultPass("Amount has been sucessfully debited from " + fromaccnt);
		} else {
			System.out.println(fromAccountBalance + " " + amountDebit);

			Assert.assertEquals(df.format(amountDebit), df.format(fromAccountBalance));
		}

		ReportLogger.info("In verifiyDebitAccount method of AccountBalance PageObjects");
		ReportLogger.pass("Verfication of Debit Account Done Successfully");
	}

	// verifiyCreditAccount method verifies that amount has been successfully credited or not after transaction
	public void verifiyCreditAccount() {

		if (df.format(amountCredit).equals(df.format(toAccountBalance))) {
			ReportLogger.resultPass("Amount has been sucessfully Credited to" + " " + toaccnt + " " + "account");
		} else {
			Assert.assertEquals(df.format(amountCredit), df.format(toAccountBalance));
		}

		ReportLogger.info("In verifiyCreditAccount method of AccountBalance PageObjects");
		ReportLogger.pass("Verfication of Credit Account Done Successfully");
	}
}
