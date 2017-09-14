package Demo;

import java.util.ArrayList;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo4 {

	static WebDriver driver = null;
	
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://sparshv2/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();		

		driver.findElement(By.partialLinkText("Web Apps")).click();
		System.out.println(driver.getTitle());
		
		driver.findElement(By.partialLinkText("Directory sys")).click();
		System.out.println(driver.getTitle());

		pointTo("Directory System");
		pointTo("Web Apps");
		driver.findElement(By.partialLinkText("Email")).click();
		pointTo("Email");
		driver.close();
		driver.quit();
	}
	
	public static void pointTo(String windowTitle){
		Set<String>allhandles = driver.getWindowHandles();
		ArrayList<String> list= new ArrayList<String>();
		list.addAll(allhandles);
		
		boolean flag = false;
		String title="";
		
		for (String windowHandle:list){
			driver.switchTo().window(windowHandle);
			title = driver.getTitle();
			if (title.equalsIgnoreCase(windowTitle)){
				flag = true;
				break;
			}
		}

		if (!flag){
			System.out.println("There is no window with title: "+ windowTitle);
		}
		else{
			System.out.println("Currently driver points to: "+title);
		}
		
	}

}
