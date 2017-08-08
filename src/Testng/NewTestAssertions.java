package Testng;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
public class NewTestAssertions {	
	
	public WebDriver driver;
	String URL = "http://www.gmail.com";

	@BeforeTest
	public void invokeBrowser() {		
		System.getProperty("webdriver.chrome.driver","D:\\Reference Selenium\\Jars and Drivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void testequal() {
		Assert.assertEquals(true, isEqual(10, 1));
		System.out.println("test");
	}

	public boolean isEqual(int a, int b) {
		if (a == b) {
			return true;
		} else {
			return false;
		}
	}

}
