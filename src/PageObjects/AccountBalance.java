package PageObjects;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ApplicationMap.ReadLocators;
import DataMap.ReadData;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;
import Utils.ReportLogger;

public class AccountBalance extends BrowserUtils {
	// account type is hardcoded...
	// (doubt how the method will be if sender and receiver will have differnt
	// accnt type????)
	
	DecimalFormat df = new DecimalFormat(".##");
	static String fromaccnt = null;
	static String toaccnt = null;
	static double amountDebit = 0;
	static double amountCredit = 0;
	static double transactionAmount = 0;
	Map<String, Double> fromAccountType = new HashMap<String, Double>();
	Map<String, Double> toAccountType = new HashMap<String, Double>();

	// This is to extract an amount and account type for user who having more
	// than one account type.
	// And perform credit and debt from the account.(doubt to be a separate
	// function or not?????)
	// for single account user yet to be coded.....
	public void XtractAccountBalance(String dsid1, String account, String calculate) throws Exception {

		ReadLocators rd1 = new ReadLocators("RegisterMember");
		ReadData dm1 = new ReadData("TransactionData", dsid1);
		ReadData dm2 = new ReadData("TransactionType", dm1.getData("TRANSACTION_TYPE"));
		fromaccnt = dm2.getData("From_Account");
		toaccnt = dm2.getData("To_Account");
		transactionAmount = Double.parseDouble((dm1.getData("TRANSACTION_AMOUNT")));
		LeftNavigationPane.NavigateTo("Account", "Account Information");
		if (driver.findElement(rd1.getLocator("ELM_AccountPane")).getText().contains("My accounts")) {
			WebElement Accntypes = driver.findElement(rd1.getLocator("TBL_MyAccountsInner"));
			List<WebElement> rows = Accntypes.findElements(By.tagName("tr"));
			for (int i = 1; i < rows.size(); i++) {
				List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
				String Amount = cols.get(2).getText();
				String SubstringAmount = Amount.substring(0, Amount.length() - 4).replace(",", "");
				Double Amountdb = Double.parseDouble(SubstringAmount);
				if (account.equalsIgnoreCase("FromAccount")) {
					fromAccountType.put(cols.get(0).getText(), Amountdb);
				} else if (account.equalsIgnoreCase("ToAccount")) {
					toAccountType.put(cols.get(0).getText(), Amountdb);
				}
			}
		} else if (driver.findElement(rd1.getLocator("ELM_SingleAccountPane")).getText().contains("Account balance")) {
			String AccountTab = driver.findElement(rd1.getLocator("ELM_SingleAccountType")).getText();
			String Amount = driver.findElement(rd1.getLocator("ELM_SingleAccountBalance")).getText();
			String SubstringAmount = Amount.substring(0, Amount.length() - 4).replace(",", "");
			Double Amountdb = Double.parseDouble(SubstringAmount);
			String[] AccountName = AccountTab.split(" ");
			String AccountType = "";
			for (int i = 3; i < AccountName.length; i++) {
				AccountType = AccountType + AccountName[i] + " ";
			}
			if (account.equalsIgnoreCase("FromAccount")) {
				fromAccountType.put(AccountType.trim(), Amountdb);
			} else if (account.equalsIgnoreCase("ToAccount")) {
				toAccountType.put(AccountType.trim(), Amountdb);
			}
		}
		ReportLogger.info("Account Balance has been extracted before transactions");

		if (calculate.equalsIgnoreCase("Calculate") && (account.equalsIgnoreCase("FromAccount"))) {
			for (Entry<String, Double> entry : fromAccountType.entrySet()) {
				if (entry.getKey().equalsIgnoreCase(fromaccnt)) {
					amountDebit = entry.getValue() - transactionAmount;
				}
			}
		} else if (calculate.equalsIgnoreCase("Calculate") && (account.equalsIgnoreCase("ToAccount"))) {
			for (Entry<String, Double> entry : toAccountType.entrySet()) {
				if (entry.getKey().equalsIgnoreCase(toaccnt)) {
					amountCredit = entry.getValue() + transactionAmount;
				}
			}
		} 
		ReportLogger.info("In extract accountbalance method of AccountBalance PageObjects");
		ReportLogger.pass("Credited or Debited amount has been calculated for transaction verfication");
	}

	public void XtractAccountBalance(String dsid1, String account) throws Exception {

		ReadLocators rd1 = new ReadLocators("RegisterMember");
		ReadData dm1 = new ReadData("TransactionData", dsid1);
		ReadData dm2 = new ReadData("TransactionType", dm1.getData("TRANSACTION_TYPE"));
		fromaccnt = dm2.getData("From_Account");
		toaccnt = dm2.getData("To_Account");
		transactionAmount = Double.parseDouble((dm1.getData("TRANSACTION_AMOUNT")));
		LeftNavigationPane.NavigateTo("Account", "Account Information");
		if (driver.findElement(rd1.getLocator("ELM_AccountPane")).getText().contains("My accounts")) {
			WebElement Accntypes = driver.findElement(rd1.getLocator("TBL_MyAccountsInner"));
			List<WebElement> rows = Accntypes.findElements(By.tagName("tr"));
			for (int i = 1; i < rows.size(); i++) {
				List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
				String Amount = cols.get(2).getText();
				String SubstringAmount = Amount.substring(0, Amount.length() - 4).replace(",", "");
				Double Amountdb = Double.parseDouble(SubstringAmount);
				if (account.equalsIgnoreCase("FromAccount")) {
					fromAccountType.put(cols.get(0).getText(), Amountdb);
				} else if (account.equalsIgnoreCase("ToAccount")) {
					toAccountType.put(cols.get(0).getText(), Amountdb);
				}
			}
		} else if (driver.findElement(rd1.getLocator("ELM_SingleAccountPane")).getText().contains("Account balance")) {
			String AccountTab = driver.findElement(rd1.getLocator("ELM_SingleAccountType")).getText();
			String Amount = driver.findElement(rd1.getLocator("ELM_SingleAccountBalance")).getText();
			String SubstringAmount = Amount.substring(0, Amount.length() - 4).replace(",", "");
			Double Amountdb = Double.parseDouble(SubstringAmount);
			String[] AccountName = AccountTab.split(" ");
			String AccountType = "";
			for (int i = 3; i < AccountName.length; i++) {
				AccountType = AccountType + AccountName[i] + " ";
			}
			if (account.equalsIgnoreCase("FromAccount")) {
				fromAccountType.put(AccountType.trim(), Amountdb);
			} else if (account.equalsIgnoreCase("ToAccount")) {
				toAccountType.put(AccountType.trim(), Amountdb);
			}
		}
		ReportLogger.info("In extract accountbalance method of AccountBalance PageObjects");
		ReportLogger.pass("Account Balance has been extracted after transactions");
	}

	// to verify amount is debted or not.........
	public void verifiyDebitAccount() {

		for (Entry<String, Double> entry : fromAccountType.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(fromaccnt)) {
				if (df.format(amountDebit).equals(df.format(entry.getValue()))) {
					ReportLogger.resultPass("Amount has been sucessfully debited from " + fromaccnt);
				} else {
					ReportLogger.info("Amount has not been debited from " + fromaccnt);
				}

			}
		}
		ReportLogger.info("In verifiyDebitAccount method of AccountBalance PageObjects");
		ReportLogger.pass("Verfication of Debit Account Done Successfully");

	}

	// to verify amount is credited or not.........
	public void verifiyCreditAccount() {

		for (Entry<String, Double> entry : toAccountType.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(toaccnt)) {
				if (df.format(amountCredit).equals(df.format(entry.getValue()))) {
					ReportLogger.resultPass("Amount has been sucessfully Credited to" + " " + toaccnt + " " + "account");
				} else {
					ReportLogger.info("Amount has not been credited to" + " " + toaccnt + " " + "account");
				}
			}
		}
		ReportLogger.info("In verifiyCreditAccount method of AccountBalance PageObjects");
		ReportLogger.pass("Verfication of credit Account Done Successfully");
		
	}

}