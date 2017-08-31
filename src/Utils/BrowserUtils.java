package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import Interface.CommonInterface;


public class BrowserUtils extends CommonInterface{
    public static WebDriver driver = null;
    public static ExtentTest test;

    String Url = PropRead.getVal("url");

    public void openbrowserie() {
        System.setProperty("webdriver.ie.driver",
            "D:\\Reference Selenium\\Jars and Drivers\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe");
        driver = new InternetExplorerDriver();

        driver.get(Url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
    
    ////tryout
    
    @DataProvider(name="dp")
    public Object[][] dptryout(Method m){  	
    	return getYesTestDetails(m.getName());
    }
    
 
    @BeforeSuite
    public void setUpSuite()
    {
    	this.extent = createInstance(System.getProperty("user.dir") + "/test-output/AutomationReport.html");
    }
    
    
    public void runTestNG() throws IOException {

        FileInputStream mapsheet = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\TestCaseSheet.xlsx");
        XSSFWorkbook WorkBook = new XSSFWorkbook(mapsheet);

        XSSFSheet sheet = WorkBook.getSheet("TestCases");

        int i;
        int rownum = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (i = 1; i <= rownum; i++) {
            String runStatus = sheet.getRow(i).getCell(3).getStringCellValue();
            if (runStatus.equalsIgnoreCase("Yes")) {
            	String testName = sheet.getRow(i).getCell(1).getStringCellValue();
            	String dataSetIDs = sheet.getRow(i).getCell(2).getStringCellValue();
                CommonInterface ci = new CommonInterface(); 
                ci.setYesTestDetails(testName, dataSetIDs);
            }
        }

        WorkBook.close();
        mapsheet.close();

    }
    
    @BeforeClass
    public void bc() throws IOException
    {
    	readEverything();
        runTestNG();
    }
    
    public void readEverything() throws IOException
    {
    	ReadAllLocators();
    	ReadAllData();
    }
    
    public void openbrowserChrome() {
        System.setProperty("webdriver.chrome.driver", PropRead.getVal("chromeDriver"));
        driver = new ChromeDriver();
        driver.get(Url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
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
        // BrowserUtils bu = new BrowserUtils();-----
        openbrowserChrome();
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
    
    protected static void logInfo(String logMessage)
    {
    	test.log(Status.INFO, 
        		MarkupHelper.createLabel(logMessage,
        				ExtentColor.BLUE));
    }


    public void PopUpAccept() {
        try {
//            String PopUpMessage = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // System.out.println(PopUpMessage);
        // return PopUpMessage;
    }
    
    //This method is executed after executing the all test cases present in the test suite.
    //Closing the browser is necessary at end of the each test case
    @AfterSuite()   
    public void Closebrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();

        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}