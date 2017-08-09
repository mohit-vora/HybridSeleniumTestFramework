package Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserUtils {
	public static WebDriver driver = null;
	

	String Url = PropRead.getVal("url");


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
	    		"C:\\Users\\mohit_vora\\Desktop\\Selenium\\jars\\new ones\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
}
  
  
 public void Closebrowser() throws InterruptedException{
	 Thread.sleep(3000);
		driver.quit();
		
		try {
		    Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		} catch (IOException e) {
		    e.printStackTrace();
		}

 }
	
}

