package PageObjects;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ApplicationMap.ReadLocators;
import DataMap.ReadData;
import Utils.LeftNavigationPane;

public class AccountBalance {

    //account type is hardcoded...
    //(doubt how the method will be if sender and receiver will have differnt accnt type????)
    String accntType = "Savings";
    double AmountDebit = 0;
    double AmountCredit = 0;
    Map < String, Double > AccountType = new HashMap < String, Double > ();
    // This is to extract an amount and account type for user who having more than one account type.
    // And perform credit and debt from the account.(doubt to be a separate function or not?????)  
    // for single account user yet to be coded.....
    public void XtractAccountBalance(WebDriver driver, String accntType, String dsid) throws Exception {
            ReadData dm;
            try {
                dm = new ReadData("MemberDetails", dsid);
                ReadLocators rd1 = new ReadLocators("RegisterMember");
                LeftNavigationPane leftpane = new LeftNavigationPane();
                leftpane.NavigateTo(driver, "Account", "Account Information");
                if (driver.findElement(rd1.getLocator("ELM_AccountPane")).getText().contains("My accounts")) {
                    WebElement Accntypes = driver.findElement(rd1.getLocator("TBL_MyAccountsInner"));
                    List < WebElement > rows = Accntypes.findElements(By.tagName("tr"));
                    for (int i = 1; i < rows.size(); i++) {
                        List < WebElement > cols = rows.get(i).findElements(By.tagName("td"));
                        String Amount = cols.get(2).getText();
                        String SubstringAmount = Amount.substring(0, Amount.length() - 4).replace(",", "");
                        Double Amountd = Double.parseDouble(SubstringAmount);
                        AccountType.put(cols.get(0).getText(), Amountd);
                    }

                }
                for (Entry < String, Double > entry: AccountType.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase(accntType)) {
                        double AmountDebit = entry.getValue() - Double.parseDouble((dm.getData("TRANSACTION_AMOUNT")));
                        double AmountCredit = entry.getValue() + Double.parseDouble((dm.getData("TRANSACTION_AMOUNT")));
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        // to verify amount is debted or not.........
    public void verifiyDebitAccount(String accntType) {
            for (Entry < String, Double > entry: AccountType.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(accntType)) {
                    Assert.assertEquals(AmountDebit, entry.getValue());
                }
            }
        }
        // to verify amount is credited or not.........
    public void verifiyCreditAccount(String accntType) {
        for (Entry < String, Double > entry: AccountType.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(accntType)) {
                Assert.assertEquals(AmountCredit, entry.getValue());
            }
        }
    }
}