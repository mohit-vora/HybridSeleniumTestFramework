package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import PageObjects.Login;
import PageObjects.VerifyPopUp;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;

public class MemberPayment {

	WebDriver driver = null;
	BrowserUtils bu = new BrowserUtils();
	VerifyPopUp popup = new VerifyPopUp();

	@BeforeMethod
	public void beforeMethod() throws Exception {
		Login dd = new Login();
		dd.performLogin(driver,"MEM005");

	}
	@Test
	public void paymentToMember()
	{
		LeftNavigationPane lnp = new LeftNavigationPane();
		lnp.NavigateTo(driver, "Account","Member Payment");	
		
		PageObjects.MemberPayment em=new PageObjects.MemberPayment();
		em.PopulatePaymenttoMember(driver);
		
	}

	@BeforeSuite
	public void Browser() {
		// BrowserUtils bu = new BrowserUtils();-----
		this.driver = bu.openbrowserChrome();
		System.out.println("inside before method");
	}
	@AfterMethod
	public void afterMethod() {
		LeftNavigationPane lnp = new LeftNavigationPane();
		lnp.NavigateTo(driver, "Logout");
		popup.PopUpAccept(driver);

	}
	
	@AfterSuite
	public void CloseBrowser() throws InterruptedException {
		bu.Closebrowser();
		System.out.println("inside aftersuit method");
	}

}
