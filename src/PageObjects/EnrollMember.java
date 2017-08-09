package PageObjects;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ApplicationMap.ReadSheet;
import DataMap.Data_Map_Tryout;


public class EnrollMember  {



	
	public void RegisterMember(WebDriver driver) throws InterruptedException, IOException {

		
		ReadSheet rd1 = new ReadSheet("RegisterMember");
		driver.findElement(By.xpath(".//*[@id='menu5']/span[2]")).click();
        driver.findElement(By.xpath(".//*[@id='submenu5.0']/span[2]")).click();
        
        WebElement Createmem = driver.findElement(By.xpath(".//*[@id='newMemberGroup']"));       
        
       new Select(Createmem).selectByVisibleText("Full members");      

        Thread.sleep(1000);
        Data_Map_Tryout dm = new Data_Map_Tryout("MemberDetails","MEM001");
        driver.findElement(rd1.getLocator("TXB_LoginName")).sendKeys(dm.getData("LOGIN_NAME"));
		driver.findElement(rd1.getLocator( "TXB_FullName")).sendKeys(dm.getData("FULL_NAME"));
		driver.findElement(rd1.getLocator( "TXB_Email")).sendKeys(dm.getData("EMAIL_ADDRESS"));
		driver.findElement(rd1.getLocator("DOB")).sendKeys(dm.getData("DATE_OF_BIRTH"));
		driver.findElement(rd1.getLocator("M_Gender")).click();
		driver.findElement(rd1.getLocator("TXB_Address")).sendKeys(dm.getData("ADDRESS"));
		driver.findElement(rd1.getLocator("TXB_PostalCode")).sendKeys(dm.getData("POSTAL_CODE"));
		driver.findElement(rd1.getLocator("TXB_City")).sendKeys(dm.getData("CITY"));
		driver.findElement(rd1.getLocator("TXB_Phone")).sendKeys(dm.getData("PHONE_NUMBER"));
		driver.findElement(rd1.getLocator("TXB_MobilePhone")).sendKeys(dm.getData("MOBILE_NUMBER"));
		driver.findElement(rd1.getLocator("Checkbox")).click();
		driver.findElement(rd1.getLocator("TXB_password")).sendKeys(dm.getData("PASSWORD"));
		driver.findElement(rd1.getLocator("TXB_confirmpassword")).sendKeys(dm.getData("PASSWORD"));
		Thread.sleep(1000);
		driver.findElement(rd1.getLocator("BTN_submit")).click();
		Thread.sleep(1000);
		

}
      



}
