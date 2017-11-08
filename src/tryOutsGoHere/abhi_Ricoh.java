package tryOutsGoHere;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.BaseClass;

public class abhi_Ricoh extends BaseClass{
	
	WebDriver driver=null;
	
	@BeforeClass
	public void openBrowser() {
    	
    		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\TestResources\\driverExecutable\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("http://10.217.9.5");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
    	
    }
	
	@Test
	public void test1(){
		
		System.out.println(driver.getTitle());
		
		driver.switchTo().frame("header");
		
		driver.findElement(By.xpath("//*[@id='rightAreaBox']/div[1]/ul/li[3]/a/span")).click();
		
		driver.findElement(By.name("userid_work")).sendKeys("admin");
		driver.findElement(By.name("password_work")).sendKeys("infy123");
		driver.findElement(By.xpath("/html/body/table/tbody/tr[6]/td/table/tbody/tr/td[2]/table/tbody/tr[6]/td[2]/input")).click();
	}

	@AfterClass
    public void closeBrowser() throws InterruptedException, IOException {
    	
    		Thread.sleep(3000);
            driver.quit();
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        

    }
}
