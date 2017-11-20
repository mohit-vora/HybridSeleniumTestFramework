package utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser extends BaseClass{
	/*This Method does the driver setup for the execution*/
    static void open() {
    	if (preExecutionCheck)
    	{
    		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+Property.getValueOf("chromeDriver"));
            driver = new ChromeDriver();
            driver.get(Property.getValueOf("url"));
            ReportLogger.info("Browser Instance opened");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            ReportLogger.info("Browser Window Maximized");
    	}
    }
    
    static void close() throws InterruptedException, IOException{
    	Thread.sleep(3000);
        driver.quit();
        ReportLogger.info("WebDriver Session ended");
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
    }
}
