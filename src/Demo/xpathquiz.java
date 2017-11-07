package Demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class xpathquiz {

	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\mohit_vora\\Desktop\\Selenium\\Workspace\\Framework\\TestResources\\driverExecutable\\chromedriver.exe" );
		driver = new ChromeDriver();
		driver.get("C:\\Users\\mohit_vora\\Desktop\\Selenium\\Workspace\\Framework\\Documentation\\Ver0.1\\WebDriver API\\Quizzez\\html.html");
		driver.manage().window().maximize();
		
		clickEdit("Selenium IDE");
		
		Thread.sleep(3000);
		driver.quit();
	}
	
	
	public static void clickEdit(String linkName){

		WebElement table = driver.findElement(By.id("”BankList”"));
		
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		
		for (int i = 1; i<rows.size();i++)
		{
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			if (cols.get(1).getText().equalsIgnoreCase(linkName)){
				cols.get(0).click();
				break;
			}
		}
	}

}
