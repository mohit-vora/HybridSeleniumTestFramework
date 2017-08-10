package PageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ApplicationMap.ReadLocators;
import Utils.LeftNavigationPane;

public class AccountBalance {
  Map<String, Double> AccountType = new HashMap<String, Double>();
	public void VerifyAccountBalance(){
		
		 } 
	
	


	public void XtractAccountBalance(WebDriver driver) {
		ReadLocators rd1 = new ReadLocators("RegisterMember");
		LeftNavigationPane leftpane = new LeftNavigationPane();
		leftpane.NavigateTo(driver, "Account", "Account Information");		
		if(driver.findElement(rd1.getLocator("ELM_AccountPane")).getText().contains("My accounts")){
			WebElement Accntypes = driver.findElement(rd1.getLocator("TBL_MyAccountsInner"));
			List<WebElement> rows = Accntypes.findElements(By.tagName("tr"));
			for(int i=1; i<rows.size();i++){
				List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
				   String Amount = cols.get(2).getText();
				   String  SubstringAmount = Amount.substring(0, Amount.length()-4).replace(",", "");
				   Double Amountd = Double.parseDouble(SubstringAmount);
				   AccountType.put(cols.get(0).getText(), Amountd);
				}
			}

	}
}
