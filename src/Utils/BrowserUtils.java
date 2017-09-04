package Utils;


import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class BrowserUtils extends BaseClass{
    
    
    ////tryout
    
    @DataProvider(name="dp")
    public Object[][] dptryout(Method m){  	
    	return getYesTestDetails(m.getName());
    }   
    
    @BeforeClass
    public void bc() throws IOException
    {
    	readEverything();
        runTestNG();
    }
    
    
    
    //Annotates methods that will be run before each test method.
    //This method will run before Register Method.
    //Operation:- Invoke Login Function

    @BeforeMethod
    public void beforeMethod(Method method) throws Exception {
        test = extent.createTest(getClass().getName()+ ":"+method.getName()+" DataSet:write something here");    
    }

    
    //BeforeSuite: This method is executed before executing the all test cases present in the test suite.
    //Opening the browser is prerequisite for all TestCases. 
    //hence, this method will be executed before all test methods and tests. 
    @BeforeSuite
    public void Browser() {
    	BaseClass.extent = createInstance(System.getProperty("user.dir") + "/test-output/AutomationReport.html");
        test = extent.createTest("preExecution-Log");    
        openBrowserChrome();
        System.out.println("we are in before suite");
    }
    
    
    //Annotates methods that will be run after each test method.
    //Operation:- Invoke logout function.

    @AfterMethod
    public void afterMethod(ITestResult result) {
    	if (result.getStatus() == ITestResult.FAILURE) {

            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
                                            ExtentColor.RED));
            test.fail(result.getThrowable());
            LeftNavigationPane.NavigateTo("Logout");
            PopUpAccept();

         } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
         } else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
         }

        extent.flush();

    }
    
   
    
    //This method is executed after executing the all test cases present in the test suite.
    //Closing the browser is necessary at end of the each test case
    @AfterSuite()   
    public void Closebrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
        System.out.println("we are here");

        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}