package Demo;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo5 {
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://iscls1apps/INFYDIR/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();		
				
		boolean flag = false;
		
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		for (WebElement ele:allLinks)
		{
			System.out.println(ele.getText());
			if (ele.getText().equals("Place")){
				flag=true;
				break;
			}
		}
		
		if (flag){
			System.out.println("Place link found");
		}
		else{
			System.out.println("Place link not found");
		}
		driver.close();
		driver.quit();
	}

}
