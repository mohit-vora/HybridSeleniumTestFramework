package Utils;


import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
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
    	if(preExecutionCheck){
    		return getYesTestDetails(m.getName());
    	}
    	return new Object[][]{};
    	
    }   
    
    @BeforeClass
    public void bc() throws IOException {
    	BaseClass.extent = createInstance(System.getProperty("user.dir") + "/test-output/AutomationReport.html");
        test = extent.createTest("preExecution-Log");    
        openBrowserChrome();
        ReadAllLocators();
    	ReadAllData();
    	readTestCaseSheet();
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
    @AfterClass
    public void Closebrowser() throws InterruptedException, IOException {
        Thread.sleep(3000);
        driver.quit();
        ReportLogger.info("WebDriver Session ended");
        System.out.println("we are here");
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        

    }

    
    
   
    
   
 
   
}