package TestCases;

import org.testng.annotations.Test;
import PageObjects.Login;
import PageObjects.VerifyPopUp;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

public class EnrollMember {
	WebDriver driver = null;
	BrowserUtils bu = new BrowserUtils();
	VerifyPopUp popup = new VerifyPopUp();

	@DataProvider(name = "EnrollMember")
	public static Object[][] getRegData() {

		return new Object[][] { { "MEM001" }, { "MEM001" } };

	}

	@Test(dataProvider = "EnrollMember")
	public void Register(String dsid) throws Exception {
		LeftNavigationPane lnp = new LeftNavigationPane();
		lnp.NavigateTo(driver, "Users & Groups", "Manage members");
		PageObjects.EnrollMember Em = new PageObjects.EnrollMember();
		Em.RegisterMember(driver, dsid);
		popup.PopUpAccept(driver);

	}

	@BeforeMethod
	public void beforeMethod() throws Exception {
		Login dd = new Login();
		dd.performLogin(driver);

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

	/*
	 * @Test(dependsOnMethods={"PopUpMethod"}) public void leftNavigate(){
	 * LeftNavigationPane leftpane = new LeftNavigationPane();
	 * leftpane.NavigateTo(driver, "Logout", ""); }
	 * 
	 * @Test(dependsOnMethods={"leftNavigate"}) public void PopUpMethod1() {
	 * popup.PopUpAccept(driver); // System.out.println(Msg); }
	 */
	@AfterSuite()
	public void CloseBrowser() throws InterruptedException {
		bu.Closebrowser();
		System.out.println("inside aftersuit method");
	}
}
