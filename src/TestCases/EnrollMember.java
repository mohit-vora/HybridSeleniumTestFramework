package TestCases;

import org.testng.annotations.Test;
import PageObjects.Login;
import PageObjects.VerifyPopUp;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;
import tryOutsGoHere.InheritThis;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

public class EnrollMember extends InheritThis {
    WebDriver driver = null;
    BrowserUtils bu = new BrowserUtils();
    VerifyPopUp popup = new VerifyPopUp();

    @DataProvider(name = "EnrollMember")
    public static Object[][] getRegData() {

        return InheritThis.args;

    }

    @Test(dataProvider = "EnrollMember")
    public void Register(String dsid) throws Exception {
        LeftNavigationPane lnp = new LeftNavigationPane();

        lnp.NavigateTo(driver, "Users & Groups", "Manage Members");

        PageObjects.EnrollMember Em = new PageObjects.EnrollMember();
        Em.RegisterMember(driver, dsid);
        popup.PopUpAccept(driver);

    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        Login dd = new Login();
        dd.performLogin(driver, "MEM004");

    }

    @BeforeSuite
    public void Browser() {
        // BrowserUtils bu = new BrowserUtils();-----
        this.driver = bu.openbrowserChrome();
    }

    @AfterMethod
    public void afterMethod() {
        LeftNavigationPane lnp = new LeftNavigationPane();
        lnp.NavigateTo(driver, "Logout");
        popup.PopUpAccept(driver);

    }

    @AfterSuite()
    public void CloseBrowser() throws InterruptedException {
        bu.Closebrowser();
    }
}