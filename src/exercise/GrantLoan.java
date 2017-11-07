package exercise;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GrantLoan {
	public static void main(String args[]) throws InterruptedException {
		WebDriver driver = null;
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mohit_vora\\Desktop\\Selenium\\Workspace\\Framework\\TestResources\\driverExecutable\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.67.89.40:8080/do/login");
		driver.findElement(By.id("cyclosUsername")).sendKeys("admin");
		driver.findElement(By.id("cyclosPassword")).sendKeys("1234");
		driver.findElement(By.xpath("//*[@id='cyclosLogin']/table/tbody/tr[3]/td/input")).click();
		
		driver.findElement(By.id("memberUsername")).sendKeys("TestUser01");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='membersByUsername']")));		
		List<WebElement> suggestions = driver.findElements(By.xpath("//*[@id='membersByUsername']/ul/li"));

		for (WebElement ele: suggestions){
			if (ele.getText().contains("TestUser01")){
				ele.click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//*[@id='tdContents']/table[1]/tbody/tr[2]/td/table/tbody/tr[8]/td/fieldset/table/tbody/tr[1]/td[4]/input")).click();
		
		new Select(driver.findElement(By.name("loan(loanGroup)"))).selectByVisibleText("Personal");
		
		new Select(driver.findElement(By.id("transferType"))).selectByVisibleText("Loan to Savings");
		
		driver.findElement(By.id("amount")).sendKeys("1000");
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 3);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String futureDate = dateFormat.format(c.getTime());
		
		driver.findElement(By.name("loan(firstRepaymentDate)")).sendKeys(futureDate);
		
		driver.findElement(By.name("loan(paymentCount)")).sendKeys("1");
		
		driver.findElement(By.id("description")).sendKeys("this is automated loan granted");
		
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		
		if (alertText.equalsIgnoreCase("The loan was successfully granted")){
			System.out.println("Loan granted successfully.");
			alert.accept();
		}
		else{
			System.out.println("Loan not granted");
			alert.dismiss();
		}
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(".//*[@id='menu15']/span[2]")).click();
		driver.switchTo().alert().accept();
		
		Thread.sleep(3000);
		
		driver.quit();
		
	}

}