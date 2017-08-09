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



	
	public void RegisterMember(WebDriver driver) throws InterruptedException, IOException {

		
		ReadSheet rd1 = new ReadSheet("RegisterMember");
		Data_Map_Tryout dm = new Data_Map_Tryout();

        driver.findElement(By.xpath(".//*[@id='menu5']/span[2]")).click();
        driver.findElement(By.xpath(".//*[@id='submenu5.0']/span[2]")).click();
        
        WebElement Createmem = driver.findElement(By.xpath(".//*[@id='newMemberGroup']"));       
        
       new Select(Createmem).selectByVisibleText("Full members");      

        Thread.sleep(1000);

        driver.findElement(rd1.get("TXB_LoginName")).sendKeys("John");
		driver.findElement(rd1.get( "TXB_FullName")).sendKeys("Abraham");
		driver.findElement(rd1.get( "TXB_Email")).sendKeys("Abraham@gmail.com");
		driver.findElement(rd1.get("DOB")).sendKeys("11/11/1990");
		driver.findElement(rd1.get("M_Gender")).click();
		driver.findElement(rd1.get("TXB_Address")).sendKeys("Mysore");
		driver.findElement(rd1.get("TXB_PostalCode")).sendKeys("570012");
		driver.findElement(rd1.get("TXB_City")).sendKeys("Mysore");
		driver.findElement(rd1.get("TXB_Phone")).sendKeys("98765432190");
		driver.findElement(rd1.get("TXB_MobilePhone")).sendKeys("9876570012");
		driver.findElement(rd1.get("Checkbox")).click();
		driver.findElement(rd1.get("TXB_password")).sendKeys("infy@123");
		driver.findElement(rd1.get("TXB_confirmpassword")).sendKeys("infy@123");
		Thread.sleep(1000);
		driver.findElement(rd1.get("BTN_submit")).click();
		Thread.sleep(1000);
		

}
      



}
