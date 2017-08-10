package PageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LeftNavigationPane;

public class AccountBalance {

	public static void VerifyAccountBalance(WebDriver driver){
		LeftNavigationPane leftpane = new LeftNavigationPane();
		leftpane.NavigateTo(driver, "Account", "Account Information");
		Map<String, Double> AccountType = new HashMap<String, Double>();
		if(driver.findElement(By.xpath("html/body/div[2]/div/div/div/div[3]/table/tbody/tr[1]/td[1]")).getText().contains("My accounts")){
			WebElement Accntypes = driver.findElement(By.xpath("html/body/div[2]/div/div/div/div[3]/table/tbody/tr[2]/td/table"));
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
	


	public double XtractAccountBalance(double Amount) {

		return Amount;
	}
}
