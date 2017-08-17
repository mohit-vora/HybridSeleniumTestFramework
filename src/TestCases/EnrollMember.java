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


/* 
1.Following TestNg Test case pattern, the test methods are included to enroll the member.
 */
public class EnrollMember extends InheritThis {
    WebDriver driver = null;
    BrowserUtils bu = new BrowserUtils();
    VerifyPopUp popup = new VerifyPopUp();
    
    
    //This test method declares that its data should be parameterized by the Data Provider
    // "EnrollMember" is annotation name used in test method to specify the data.
    @DataProvider(name = "EnrollMember")
    public static Object[][] getRegData() {
        return InheritThis.args;
    }

    //Calling the DataProvider objects with its name "EnrollMember"
    //Operation:- Invoke register function
    @Test(dataProvider = "EnrollMember")
    public void Register(String dsid) throws Exception {
        LeftNavigationPane lnp = new LeftNavigationPane();
        lnp.NavigateTo(driver, "Users & Groups", "Manage Members");
        PageObjects.EnrollMember Em = new PageObjects.EnrollMember();
        Em.RegisterMember(driver, dsid);
        popup.PopUpAccept(driver);
    }

    //Annotates methods that will be run before each test method.
    //This method will run before Register Method.
    //Operation:- Invoke Login Function
    @BeforeMethod
    public void beforeMethod() throws Exception {
        Login dd = new Login();
        dd.performLogin(driver, "MEM004");
    }

    
    //BeforeSuite: This method is executed before executing the all test cases present in the test suite.
    //Opening the browser is prerequisite for all TestCases. 
    //hence, this method will be executed before all test methods and tests. 
    @BeforeSuite
    public void Browser() {
        // BrowserUtils bu = new BrowserUtils();-----
        this.driver = bu.openbrowserChrome();
    }
    
    
    //Annotates methods that will be run after each test method.
    //Operation:- Invoke logout function.
    @AfterMethod
    public void afterMethod() {
        LeftNavigationPane lnp = new LeftNavigationPane();
        lnp.NavigateTo(driver, "Logout");
        popup.PopUpAccept(driver);

    }

    //This method is executed after executing the all test cases present in the test suite.
    //Closing the browser is necessary at end of the each test case
    @AfterSuite()
    public void CloseBrowser() throws InterruptedException {
        bu.Closebrowser();
    }
}