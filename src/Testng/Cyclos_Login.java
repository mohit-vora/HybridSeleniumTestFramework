package Testng;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.omg.Messaging.SyncScopeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Cyclos_Login {

	WebDriver driver;
	String url = "http://10.67.89.41:8080/do/login";

	By TXB_LOGINNAME = By.id("cyclosUsername"); 
   
	By TXB_PASSWORD = By.id("cyclosPassword");
	By BTN_LOGINNAME = By.xpath("//*[@id='cyclosLogin']/table/tbody/tr[3]/td/input");
	By ELM_LOGGEDUSER = By.xpath("//*[@id='loginDataBar']/span[1]");
	By ELM_ACCOUNTINFORMATION = null;
	By ELM_ACCOUNTS = null;
	By ELM_USERSGROUPS = null;
	By ELM_MANAGEMEMBERS = null;
	By ELM_MEMBERPAYMENT = null;
	By ELM_LOGOUT = null;

	
@BeforeSuite
public void before(){
	System.setProperty("webdriver.ie.driver","D:\\Reference Selenium\\Jars and Drivers\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe");
	driver = new InternetExplorerDriver();
	driver.get(url);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
}

@AfterSuite
public void CloseBrowser(){
	driver.quit();
}

@Test
	public void Login(){
		driver.findElement(TXB_LOGINNAME).sendKeys("admin") ;
		driver.findElement(TXB_PASSWORD).sendKeys("1234");
		driver.findElement(BTN_LOGINNAME).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Accounts')]")).click();
		List<WebElement> uli= driver.findElements(By.tagName("ul"));
			for (WebElement subm : uli){
			List<WebElement> list = subm.findElements(By.tagName("li"));
			for (WebElement listsub : list){
				List<WebElement> list11 = subm.findElements(By.tagName("span"));
				for (WebElement webElement : list11) {
					System.out.println(webElement.getText());
					
				}
			}
			
			
			}
		}
 
/*String UserLogin = "admin";
String UserName = "Administrator4";
@Parameters({"UserLogin","UserName"})
@Test(dependsOnMethods={"Login"})
public void HomePage(String UserLogin, String UserName){
				
		String Logged_user = driver.findElement(ELM_LOGGEDUSER).getText();
		System.out.println(Logged_user);
		String[] User = Logged_user.split(" ",5);
//		for (int i=0;i<=4;i++){
//			System.out.println(User[i]);
//		}
		Assert.assertNotEquals(User[2], UserLogin);
		Assert.assertNotEquals(User[4], UserName);
}
	
//	public void NavigateTo(By left_nav_pane){
//		//type casting code should come here 
//		String option_name = left_nav_pane.toString();
//		switch(option_name){
//		case "ELM_ACCOUNTINFORMATION" :
//			driver.findElement(ELM_ACCOUNTS).click();
//			driver.findElement(ELM_ACCOUNTINFORMATION).click();
//		case "ELM_MANAGEMEMBERS":
//			driver.findElement(ELM_USERSGROUPS).click();
//			driver.findElement(ELM_MANAGEMEMBERS).click();
//		case "ELM_MEMBERPAYMENT":
//			driver.findElement(ELM_ACCOUNTS).click();
//			driver.findElement(ELM_MEMBERPAYMENT).click();
//		case "ELM_LOGOUT":
//			driver.findElement(ELM_LOGOUT).click();
//
//		}
//	}
*/
}
 	