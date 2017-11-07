package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByAll;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseClass {
    static int testIterationNumber = 0;
    static ExtentTest test;
    static int testCaseCount = 0;
	protected static WebDriver driver = null;
    protected static boolean preExecutionCheck = true;
    protected static boolean isLoggedIn = false;
    static ExtentReports extent;
    
    
    public static WebTable createWebTable(ByAll bys){
       	return new WebTable(driver.findElement(bys));
    }

	//reading things go here
    /*This Method does the driver setup for the execution*/
    static void openBrowser() {
    	if (preExecutionCheck)
    	{
    		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+getPropVal("chromeDriver"));
            driver = new ChromeDriver();
            driver.get(getPropVal("url"));
            ReportLogger.info("Browser Instance opened");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            ReportLogger.info("Browser Window Maximized");
    	}
    }
 
	
	//this is where driver splitting things end
	//properties file related code
	/*read the path of Application map,datamap,Driver setup file*/
	protected static String getPropVal(String parm) {

		File filePath = new File(System.getProperty("user.dir") + "\\TestResources\\path.properties");
  	
		FileInputStream fileInput = null;
		try {
		    fileInput = new FileInputStream(filePath);
		    ReportLogger.info("Traced Location of Path file");
		} 
		catch (FileNotFoundException fileNotFoundException) {
			  ReportLogger.preExecutionFail(fileNotFoundException);
		}
		
		Properties properties = new Properties();
		
		try {
			properties.load(fileInput);
		    ReportLogger.info("Loaded the path file");
		} 
		catch (IOException ioException) {
			ReportLogger.preExecutionFail(ioException);
		}
		
		return properties.getProperty(parm.toLowerCase());
	}
	
	
	//properties file related code ends here
	
	//this is where application map reading starts
	
	/*ReadAllLocators method reads the application map where the element locators are saved and stores it in a HashMap datastructure*/
	
	
    
    //this is where application map related things end
    
    
    
    
        
    
/*This method initializes the basic configuration needed for the report generation*/
    
}
