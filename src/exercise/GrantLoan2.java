package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GrantLoan2 {
	public static void main(String args[]) {
		WebDriver driver = null;
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mohit_vora\\Desktop\\Selenium\\Workspace\\Framework\\TestResources\\driverExecutable\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.67.89.40:8080/do/login");
		driver.findElement(By.id("cyclosUsername")).sendKeys("admin");
		driver.findElement(By.id("cyclosPassword")).sendKeys("1234");
		driver.findElement(By.xpath("//*[@id='cyclosLogin']/table/tbody/tr[3]/td/input")).click();
		driver.findElement(By.xpath(".//*[@id='menu5']/span[2]")).click();
		driver.findElement(By.xpath(".//*[@id='submenu5.9']/span[2]")).click();
		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/img[1]")).click();
		driver.findElement(By.xpath(".//*[@id='tdContents']/table[1]/tbody/tr[3]/td/table/tbody/tr[5]/td[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='tdContents']/table[1]/tbody/tr[2]/td/table/tbody/tr[8]/td/fieldset/table/tbody/tr[1]/td[4]/input")).click();
		driver.findElement(By.id("amount")).sendKeys("1000");
		driver.findElement(By.xpath(".//*[@id='calendarTrigger2']")).click();
		driver.findElement(By.xpath("html/body/div[3]/table/tbody/tr[5]/td[3]")).click();
		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[15]/td[2]/input[1]")).sendKeys("10");
		driver.findElement(By.id("description")).sendKeys("Car Loan");
		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[18]/td/input")).click();
		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[7]/td/input")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath(".//*[@id='menu15']/span[2]")).click();
		driver.switchTo().alert().accept();
		
	}

}