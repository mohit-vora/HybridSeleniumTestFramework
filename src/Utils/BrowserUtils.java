package Utils;

import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class BrowserUtils extends BaseClass{
	
	
	/*
	 * this method annotates testNG data provider which returns two dimensional object array
	 * It returns the test data object array */
    @DataProvider(name="dp")
    public Object[][] dptryout(Method method){  	
		testIterationNumber=0;
    	
    	Object[][] returnObject = new Object[][]{};
    	
    	try
    	{
    		if(preExecutionCheck){
    			Object[][] obj = getYesTestDetails(method.getName());
    			if (checkDataProviderSanity(obj, method)){
    				System.out.println(method.getName());
    				returnObject = obj;
    			}
        	}
    	}
    	catch(Exception e){
    		ReportLogger.preExecutionFail(e);   		
    	}
    		
    	return returnObject; 	
    }   

    
    /*executionSetUp method is annotated with @BeforeClass annotation 
     * and it will be run before the first test method in the current class is invoked. 
    */
    @BeforeClass
    public void executionSetUp() throws IOException {
    	createExtentInstance(System.getProperty("user.dir") + "/test-output/AutomationReport.html");
        test = extent.createTest("preExecution-Log");  

	        readTestCaseSheet();
	        
	        if (testCaseCount>0)
	        {
	            ReadAllLocators();
	         	ReadAllData();
	         	openBrowser();
	        }
	        
	        else
	        {
	        	ReportLogger.warn("No Testcases have been selected");
	        }
    }
    
    
    
    //Annotates methods that will be run before each test method.
    //This method will run before each test method
    //this method creates separate reporting method of each test

    @BeforeMethod
    public void beforeMethod(Method method) throws Exception {
    	System.out.println("before method");
        test = extent.createTest(method.getName()+": "+getCurrentIterationTestData(method));
      }

    
    //Annotates methods that will be run after each test method.
    //this method will invoke after each test method.
    //ITestResult describes the result of @Test annotation method in testNG 
    // based on the ItestResult the results are logged in the report
    
    @AfterMethod
    public void afterMethod(ITestResult result){
    	
    	if (result.getStatus() == ITestResult.FAILURE) {
    		
    		ReportLogger.fail(result.getName() + " Test case FAILED due to below issues:",result.getThrowable());

            LeftNavigationPane.logOutOfApplication();

         } else if (result.getStatus() == ITestResult.SUCCESS) {
        	 ReportLogger.pass(result.getName() + " Test Case PASSED");
         } else {
        	ReportLogger.skip(result.getName() + " Test Case SKIPPED", result.getThrowable());
         }
    }
    //This method is executed after executing the all test cases present in the test suite.
    //This method closes the browser after executing all the test methods
    @AfterClass
    public void closeBrowser() throws InterruptedException, IOException {
    	if (testCaseCount>0 && preExecutionCheck)
        {
    		Thread.sleep(3000);
            driver.quit();
            ReportLogger.info("WebDriver Session ended");
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        }
        extent.flush();
System.out.println("fummy");

    }

    
    
   
    
   
 
   
}