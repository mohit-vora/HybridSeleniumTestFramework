package PageObjects;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;

import Utils.LeftNavigationPane;

public class AccountBalance {
	public static void VerifyAccountBalance(WebDriver driver){
		LeftNavigationPane leftpane = new LeftNavigationPane();
		leftpane.NavigateTo(driver, "Accounts", "Account Information");
		
	} 
	
	public double XtractAccountBalance(double Amount){
		return Amount;
	}
}
