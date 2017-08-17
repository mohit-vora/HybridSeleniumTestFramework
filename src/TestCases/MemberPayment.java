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

    
    //This test method declares that its data should be parameterized by the Data Provider
    // "PayMember" is annotation name used in test method to specify the data.
    @DataProvider(name = "PayMember")
    public static Object[][] getRegData() {

        return new Object[][] {
            {
                "MEM005",
                "MEM006",
                "TXN001"
            }
        };
    }

    //Annotates methods that will be run before each test method.
    //This method will run before PaymentToMember Method.
    //Operation:- Invoke Login Function
    @BeforeMethod
    public void beforeMethod(Object[] testArgs) throws Exception {
        String dsid = (String) testArgs[0];
        Login dd = new Login();
        dd.performLogin(driver, dsid);
    }

    //Calling the DataProvider objects with its name "PayMember"
    //Operation:- Invoke Left navigation and Populate payment to member method.
    @Test(dataProvider = "PayMember")
    public void paymentToMember(String fromMemDSId, String toMemDSId, String TXNDSId) throws IOException{
        LeftNavigationPane lnp = new LeftNavigationPane();
        lnp.NavigateTo(driver, "Account", "Member Payment");
        PageObjects.MemberPayment em = new PageObjects.MemberPayment();
        em.PopulatePaymenttoMember(driver, toMemDSId, TXNDSId);
    }

    
    //BeforeSuite: This method is executed before executing the all test cases present in the test suite.
    //Opening the browser is prerequisite for all TestCases. 
    //hence, this method will be executed before all test methods and tests. 
    @BeforeSuite
    public void Browser() {
        // BrowserUtils bu = new BrowserUtils();-----
        this.driver = bu.openbrowserChrome();
        System.out.println("inside before method");
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
    @AfterSuite
    public void CloseBrowser() throws InterruptedException {
        bu.Closebrowser();
        System.out.println("inside aftersuit method");
    }

}