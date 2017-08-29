package PageObjects;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ApplicationMap.ReadLocators;
import DataMap.ReadData;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;

public class AccountBalance extends BrowserUtils{

    //account type is hardcoded...
    //(doubt how the method will be if sender and receiver will have differnt accnt type????)
    
	
    static String Fromaccnt = null;
    static String Toaccnt = null;
    static double AmountDebit = 0;
    static double AmountCredit = 0;
    static double Transaction_Amount = 0;
    Map < String, Double > AccountType = new HashMap < String, Double > ();
    // This is to extract an amount and account type for user who having more than one account type.
    // And perform credit and debt from the account.(doubt to be a separate function or not?????)  
    // for single account user yet to be coded.....
    public void XtractAccountBalance(String dsid1,String calculate) throws Exception {
                try {
                ReadLocators rd1 = new ReadLocators("RegisterMember");
                ReadData dm1 = new ReadData("TransactionData", dsid1); 
                ReadData dm2 = new ReadData("TransactionType", dm1.getdata("TRANSACTION_TYPE"));    			
    			Fromaccnt = dm2.getData("From_Account");
    			Toaccnt = dm2.getdata("To_Account");
    			Transaction_Amount=  Double.parseDouble((dm1.getData("TRANSACTION_AMOUNT")));
                LeftNavigationPane.NavigateTo("Account", "Account Information");
                if (driver.findElement(rd1.getLocator("ELM_AccountPane")).getText().contains("My accounts")) {
                    WebElement Accntypes = driver.findElement(rd1.getLocator("TBL_MyAccountsInner"));
                    List < WebElement > rows = Accntypes.findElements(By.tagName("tr"));
                    for (int i = 1; i < rows.size(); i++) {
                        List < WebElement > cols = rows.get(i).findElements(By.tagName("td"));
                        String Amount = cols.get(2).getText();
                        String SubstringAmount = Amount.substring(0, Amount.length() - 4).replace(",", "");
                        Double Amountdb = Double.parseDouble(SubstringAmount);
                        AccountType.put(cols.get(0).getText(), Amountdb);
                    }
              System.out.println(AccountType);
              creditDebitCalculation();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    
    public void XtractAccountBalance(String dsid1) throws Exception {
        try {
        ReadLocators rd1 = new ReadLocators("RegisterMember");
        ReadData dm1 = new ReadData("TransactionData", dsid1); 
        ReadData dm2 = new ReadData("TransactionType", dm1.getdata("TRANSACTION_TYPE"));    			
		Fromaccnt = dm2.getData("From_Account");
		Toaccnt = dm2.getdata("To_Account");
		Transaction_Amount=  Double.parseDouble((dm1.getData("TRANSACTION_AMOUNT")));
        LeftNavigationPane.NavigateTo("Account", "Account Information");
        if (driver.findElement(rd1.getLocator("ELM_AccountPane")).getText().contains("My accounts")) {
            WebElement Accntypes = driver.findElement(rd1.getLocator("TBL_MyAccountsInner"));
            List < WebElement > rows = Accntypes.findElements(By.tagName("tr"));
            for (int i = 1; i < rows.size(); i++) {
                List < WebElement > cols = rows.get(i).findElements(By.tagName("td"));
                String Amount = cols.get(2).getText();
                String SubstringAmount = Amount.substring(0, Amount.length() - 4).replace(",", "");
                Double Amountdb = Double.parseDouble(SubstringAmount);
                AccountType.put(cols.get(0).getText(), Amountdb);
            }
          }
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

}
    
    public void creditDebitCalculation(){
        for (Entry < String, Double > entry: AccountType.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(Fromaccnt)) {
                AmountDebit = entry.getValue() -  Transaction_Amount; 
                System.out.println(AmountDebit);
            }
            if (entry.getKey().equalsIgnoreCase(Toaccnt)){
            	AmountCredit = entry.getValue() + Transaction_Amount;
            }
        }
    }
        // to verify amount is debted or not.........
    public void verifiyDebitAccount() {
          for (Entry < String, Double > entry: AccountType.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(Fromaccnt)) {
                	Assert.assertEquals(AmountDebit, entry.getValue());
                }
            }
        }
        // to verify amount is credited or not.........
    public void verifiyCreditAccount() {
        for (Entry < String, Double > entry: AccountType.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(Toaccnt)) {
                Assert.assertEquals(AmountCredit, entry.getValue());
            }
        }
    }
}