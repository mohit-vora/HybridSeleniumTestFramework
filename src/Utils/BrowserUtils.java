package Utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserUtils {
	public static WebDriver driver = null;
	String Url = "http://10.67.89.40:8080/do/login";
	/*public WebDriver driver(){
		return driver;
	}*/
	
	
  
  public WebDriver openbrowserie() {
	    System.setProperty("webdriver.ie.driver",
				"D:\\Reference Selenium\\Jars and Drivers\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
  }
  
  public WebDriver openbrowserChrome() {
	    System.setProperty("webdriver.chrome.driver",
				"D:\\Reference Selenium\\Jars and Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
}
  
  
 public WebDriver Closebrowser(){
	 driver.quit();
	 return driver;
 }
	
}

