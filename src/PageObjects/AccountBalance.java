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

public class AccountBalance extends BrowserUtils {

	// account type is hardcoded...
	// (doubt how the method will be if sender and receiver will have differnt
	// accnt type????)
	DecimalFormat df = new DecimalFormat(".##");
	static String Fromaccnt = null;
	static String Toaccnt = null;
	static double AmountDebit = 0;
	static double AmountCredit = 0;
	static double Transaction_Amount = 0;
	Map<String, Double> fromAccountType = new HashMap<String, Double>();
	Map<String, Double> toAccountType = new HashMap<String, Double>();

	// This is to extract an amount and account type for user who having more
	// than one account type.
	// And perform credit and debt from the account.(doubt to be a separate
	// function or not?????)
	// for single account user yet to be coded.....
	public void XtractAccountBalance(String dsid1, String account, String calculate) throws Exception {
		try {
			ReadLocators rd1 = new ReadLocators("RegisterMember");
			ReadData dm1 = new ReadData("TransactionData", dsid1);
			ReadData dm2 = new ReadData("TransactionType", dm1.getdata("TRANSACTION_TYPE"));
			Fromaccnt = dm2.getData("From_Account");
			Toaccnt = dm2.getdata("To_Account");
			Transaction_Amount = Double.parseDouble((dm1.getData("TRANSACTION_AMOUNT")));
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
			} else if (driver.findElement(rd1.getLocator("ELM_SingleAccountPane")).getText()
					.contains("Account balance")) {
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

			if (calculate.equalsIgnoreCase("Calculate") && (account.equalsIgnoreCase("FromAccount"))) {
				for (Entry<String, Double> entry : fromAccountType.entrySet()) {
					if (entry.getKey().equalsIgnoreCase(Fromaccnt)) {
						AmountDebit = entry.getValue() - Transaction_Amount;
					}
				}
			} else if (calculate.equalsIgnoreCase("Calculate") && (account.equalsIgnoreCase("ToAccount"))) {
				for (Entry<String, Double> entry : toAccountType.entrySet()) {
					if (entry.getKey().equalsIgnoreCase(Toaccnt)) {
						AmountCredit = entry.getValue() + Transaction_Amount;
					}
				}
			}

		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void XtractAccountBalance(String dsid1, String account) throws Exception {
		try {
			ReadLocators rd1 = new ReadLocators("RegisterMember");
			ReadData dm1 = new ReadData("TransactionData", dsid1);
			ReadData dm2 = new ReadData("TransactionType", dm1.getdata("TRANSACTION_TYPE"));
			Fromaccnt = dm2.getData("From_Account");
			Toaccnt = dm2.getdata("To_Account");
			Transaction_Amount = Double.parseDouble((dm1.getData("TRANSACTION_AMOUNT")));
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
			} else if (driver.findElement(rd1.getLocator("ELM_SingleAccountPane")).getText()
					.contains("Account balance")) {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// to verify amount is debted or not.........
	public void verifiyDebitAccount() {
		try {
			for (Entry<String, Double> entry : fromAccountType.entrySet()) {
				if (entry.getKey().equalsIgnoreCase(Fromaccnt)) {
					if (df.format(AmountDebit).equals(df.format(entry.getValue()))) {
						System.out.println("Amount has been sucessfully debited from " + Fromaccnt);
					} else {
						System.out.println("Amount has not been debited from " + Fromaccnt);
					}

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// to verify amount is credited or not.........
	public void verifiyCreditAccount() {
		try {
			for (Entry<String, Double> entry : toAccountType.entrySet()) {
				if (entry.getKey().equalsIgnoreCase(Toaccnt)) {
					if (df.format(AmountCredit).equals(df.format(entry.getValue()))) {
						System.out.println("Amount has been sucessfully Credited to" + " " + Toaccnt + " " + "account");
					} else {
						System.out.println("Amount has not been credited to" + " " + Toaccnt + " " + "account");
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}