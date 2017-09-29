package Demo;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo3 {

	public static void main(String[] args) {
		WebDriver driver;
		String url = "http://iscls1apps/INFYDIR/";
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//*[@id='_ctl0_Place']/a/span/span/span")).click();
		driver.findElement(By.name("_ctl0:ContentPlaceHolder1:txtSearch")).clear();
		driver.findElement(By.name("_ctl0:ContentPlaceHolder1:txtSearch")).sendKeys("mysore");
		driver.findElement(By.id("_ctl0_ContentPlaceHolder1_lnkSearch")).click();

		WebElement tableSearchResults = driver
				.findElement(By.xpath("html/body/form/div[2]/div[2]/div[6]/div[2]/div/div[3]/div[4]"));

		List<WebElement> rows = tableSearchResults.findElements(By.tagName("tr"));

		for (int listindex = 2; listindex < rows.size() - 1; listindex++) {
			List<WebElement> cols = rows.get(listindex).findElements(By.tagName("td"));

			System.out.print("PlaceType: " + cols.get(0).getText());
			System.out.println("  Country: " + cols.get(2).getText());
			System.out.println();
		}
		driver.quit();

	}
}