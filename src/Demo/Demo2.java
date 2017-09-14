package Demo;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Demo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String url = "http://10.67.89.40:8080/do/login";
		driver.get(url);
		driver.manage().window().maximize();
		
		driver.findElement(By.id("cyclosUsername")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("1234");
		driver.findElement(By.xpath("//*[@id=\"cyclosLogin\"]/table/tbody/tr[3]/td/input")).click();
		driver.findElement(By.xpath("//*[@id=\"menu5\"]/span[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"submenu5.0\"]/span[2]")).click();
		
		WebElement groupList=driver.findElement(By.xpath(".//*[@id='newMemberGroup']"));
		
		List<WebElement> grouplistd= new Select(groupList).getOptions();
		
		for (WebElement webElement : grouplistd) {
			System.out.println(webElement.getText());
		}
		
		//driver.findElement(By.xpath(".//*[@id='newMemberGroup']")).sendKeys("Savings");;
//		WebElement groupList=driver.findElement(By.xpath(".//*[@id='newMemberGroup']"));
		
		new Select(groupList).selectByVisibleText("Savings");
		new Select(groupList).selectByIndex(13);
		new Select(groupList).selectByValue("20");
		
		
		
//		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("TraineeUser03");
//		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/input")).sendKeys("TraineeUser03");
//		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[4]/td[2]/input")).sendKeys("TraineeUser03@gmail.com");
//		driver.findElement(By.xpath("html/body/div[2]/div/div/div/div[3]/form/table[1]/tbody/tr[2]/td/table/tbody/tr[5]/td[2]/input[3]")).sendKeys("14/07/1995");
//
//		driver.findElement(By.xpath("//*[@id='_radio_2_1']")).click();
//		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[7]/td[2]/input[3]")).sendKeys("Bangalore");
//		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[8]/td[2]/input[3]")).sendKeys("570012");
//		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[9]/td[2]/input[3]")).sendKeys("Bangalore");
//		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[10]/td[2]/input[3]")).sendKeys("3453453450");
//		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[11]/td[2]/input[3]")).sendKeys("1444422050");
//		driver.findElement(By.id("assignPasswordCheck")).click();
//		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[16]/td[2]/input")).sendKeys("infy@123");
//		driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[17]/td[2]/input")).sendKeys("infy@123");
//		driver.findElement(By.xpath("//*[@id='saveAndNewButton']")).click();
//		
//		Alert alertObject = driver.switchTo().alert();
//		System.out.println(alertObject.getText());
////		driver.switchTo().alert().accept();
//		alertObject.accept();
		
		driver.findElement(By.xpath("//span[contains(text(),'Logout')]")).click();
		
		driver.close();
		driver.quit();

	}

}
