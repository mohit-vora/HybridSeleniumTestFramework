package Demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo1 {
	public static void main(String args[]) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://sparshv2/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.navigate().to("http://sparshv2/");
//		driver.findElement(By.linkText("Web Apps")).click();
		
		driver.findElement(By.partialLinkText("Web A")).click();

		driver.findElement(By.partialLinkText("Web A")).click();
		
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		driver.navigate().back();
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		driver.navigate().forward();
		System.out.println(driver.getTitle());
		driver.navigate().refresh();
		driver.close();
		driver.quit();
		
	}

}
