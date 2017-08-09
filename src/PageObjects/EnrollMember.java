package PageObjects;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.MoveMouseAction;
import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.ui.Select;

import ApplicationMap.ReadSheet;
import DataMap.Data_Map_Tryout;

public class EnrollMember  {

	Map<String, Map<String, ByAll>> input = new TreeMap<String, Map<String, ByAll>>();
	Map<String,Integer> memberGroup=new TreeMap<String, Integer>();
	ReadSheet rd1 = new ReadSheet();
	public void RegisterMember(WebDriver driver) throws Exception{
	try {
		input = rd1.readsheet();			
		Data_Map_Tryout dm = new Data_Map_Tryout();

        driver.findElement(By.xpath(".//*[@id='menu5']/span[2]")).click();
        driver.findElement(By.xpath(".//*[@id='submenu5.0']/span[2]")).click();
        
        WebElement Createmem = driver.findElement(By.xpath(".//*[@id='newMemberGroup']"));
     /*   List<WebElement> Creatememdrop = driver.findElements(By.tagName("option"));
        for (WebElement webElement : Creatememdrop) {
			System.out.println(webElement.getAttribute("value")+"::"+webElement.getText());
			if (webElement.getAttribute("value").isEmpty()){;
				
				continue;}
			else
				memberGroup.put(webElement.getText(), Integer.parseInt(webElement.getAttribute("value")));
		}		
		*/
        
       // Creatememdrop.get(6).click();
       
        
       new Select(Createmem).selectByVisibleText("Full members"); 
       
       //   new Select(Createmem).selectByValue("5");
           
           
        
    //    driver.findElement(By.xpath(".//*[@id='tdContents']/form/table/tbody/tr[2]/td/table/tbody/tr[6]/td[3]/input")).click();
        
	//	driver.findElement(input.get("RegisterMember").get("LNK_ManageMembers")).click();
	//	driver.findElement(input.get("RegisterMember").get("TXB_permissiongroups")).click();
        Thread.sleep(1000);
        
        System.out.println("inside this");
        //driver.findElement(By.xpath(".//*[@id='tdContents']/form/table[1]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("Jenny");
		//System.out.println("text"+input.get("RegisterMember").get("TXB_LoginName"));
        driver.findElement(input.get("RegisterMember").get("TXB_LoginName")).sendKeys("John");
		driver.findElement(input.get("RegisterMember").get("TXB_FullName")).sendKeys("Abraham");
		driver.findElement(input.get("RegisterMember").get("TXB_Email")).sendKeys("Abraham@gmail.com");
		driver.findElement(input.get("RegisterMember").get("DOB")).sendKeys("11/11/1990");
		driver.findElement(input.get("RegisterMember").get("M_Gender")).click();
		driver.findElement(input.get("RegisterMember").get("TXB_Address")).sendKeys("Mysore");
		driver.findElement(input.get("RegisterMember").get("TXB_PostalCode")).sendKeys("570012");
		driver.findElement(input.get("RegisterMember").get("TXB_City")).sendKeys("Mysore");
		driver.findElement(input.get("RegisterMember").get("TXB_Phone")).sendKeys("98765432190");
		driver.findElement(input.get("RegisterMember").get("TXB_MobilePhone")).sendKeys("9876570012");
		driver.findElement(input.get("RegisterMember").get("Checkbox")).click();
		driver.findElement(input.get("RegisterMember").get("TXB_password")).sendKeys("infy@123");
		driver.findElement(input.get("RegisterMember").get("TXB_confirmpassword")).sendKeys("infy@123");
		Thread.sleep(1000);
		driver.findElement(input.get("RegisterMember").get("BTN_submit")).click();
		Thread.sleep(1000);
		
			
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
      



}
