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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import Interface.CommonInterface;
import PageObjects.Login;


public class BrowserUtils {
    public static WebDriver driver = null;

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
    public Object[][] dptryout(Method m)
    {
    	CommonInterface ci = new CommonInterface();
    	if (ci.checkTestFlag(m.getName()))
    		return CommonInterface.testArgs;
    	
    	else
    	return new Object[][]{};
    }
    
    ////tryout

    
    
    public void runTestNG() throws IOException {

        FileInputStream mapsheet = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\TestCaseSheet.xlsx");
        XSSFWorkbook WorkBook = new XSSFWorkbook(mapsheet);

        XSSFSheet sheet = WorkBook.getSheet("TestCases");

        int i;
        int rownum = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (i = 1; i <= rownum; i++) {
            String runStatus = sheet.getRow(i).getCell(0).getStringCellValue();
            if (runStatus.equalsIgnoreCase("Yes")) {
                CommonInterface ci = new CommonInterface(); 
                ci .setArgs(sheet.getRow(i).getCell(3).getStringCellValue());
            }
        }

        WorkBook.close();
        mapsheet.close();

    }
    
    @BeforeClass
    public void bc() throws IOException
    {
    	System.out.println("inside before class");
    	readEverything();
        runTestNG();
    }
    
    public void readEverything() throws IOException
    {
    	CommonInterface ci = new CommonInterface();
    	ci.ReadAllLocators();
    	ci.ReadAllData();
    }
    
    public void openbrowserChrome() {
        System.setProperty("webdriver.chrome.driver", PropRead.getVal("chromeDriver"));
        driver = new ChromeDriver();
        driver.get(Url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    
    
    //This test method declares that its data should be parameterized by the Data Provider
    // "EnrollMember" is annotation name used in test method to specify the data.
    @DataProvider(name = "dProvider")
    public static Object[][] getRegData() {
        return CommonInterface.testArgs;
    }
    
    //Annotates methods that will be run before each test method.
    //This method will run before Register Method.
    //Operation:- Invoke Login Function
    @BeforeMethod
    public void beforeMethod(Object[] testArgs) throws Exception {
    	String dsid = (String) testArgs[0];
        Login dd = new Login();
        dd.performLogin(dsid);
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
    public void afterMethod() {
        LeftNavigationPane lnp = new LeftNavigationPane();
        lnp.NavigateTo("Logout");
        PopUpAccept();

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