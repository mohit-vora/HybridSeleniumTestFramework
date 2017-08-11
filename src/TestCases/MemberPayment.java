package TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.Login;
import PageObjects.VerifyPopUp;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;

public class MemberPayment {

	WebDriver driver = null;
	BrowserUtils bu = new BrowserUtils();
	VerifyPopUp popup = new VerifyPopUp();

	
	@DataProvider(name = "PayMember")
	public static Object[][] getRegData() {

		return new Object[][] { { "MEM001" }, { "MEM001" } };

	}
	
	@BeforeMethod
	public void beforeMethod(Object[] testArgs) throws Exception {
		String dsid = (String) testArgs[0];
		Login dd = new Login();
		dd.performLogin(driver,dsid);

	}
	@Test(dataProvider="PayMember")
	public void paymentToMember(String toMemDSId, String TXNDSId) throws IOException
	{
		
		
		LeftNavigationPane lnp = new LeftNavigationPane();
		lnp.NavigateTo(driver, "Account","Member Payment");	
		
		PageObjects.MemberPayment em=new PageObjects.MemberPayment();
		em.PopulatePaymenttoMember(driver,toMemDSId,TXNDSId);
		
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
