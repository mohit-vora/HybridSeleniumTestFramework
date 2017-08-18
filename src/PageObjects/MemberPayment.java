package PageObjects;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ApplicationMap.ReadLocators;
import DataMap.ReadData;

public class MemberPayment {
    ReadLocators rd1 = new ReadLocators("MemberPayment");

    public void PopulatePaymenttoMember(WebDriver driver, String dsid1, String dsid2) throws IOException, InterruptedException {

	
			ReadData dm1 = new ReadData("MemberDetails", dsid1);
			
			ReadData dm2 = new ReadData("TransactionData", dsid2);
			


        driver.findElement(rd1.getLocator("TXB_Name")).sendKeys(dm1.getData("LOGIN_NAME"));
        Thread.sleep(1000);
        driver.findElement(rd1.getLocator("TXB_Amount")).sendKeys(dm2.getData("TRANSACTION_AMOUNT"));
        WebElement we = driver.findElement(rd1.getLocator("LST_Transaction_Type"));
        //List<WebElement> list12 = we.findElements(By.tagName("option"));
        new Select(we).selectByVisibleText(dm2.getData("TRANSACTION_TYPE"));
        driver.findElement(rd1.getLocator("TXB_Description")).sendKeys(dm2.getData("TRANSACTION_DESCRIPTION"));

        driver.findElement(rd1.getLocator("BTN_Submit")).click();
        driver.findElement(rd1.getLocator("BTN_Success_Submit")).click();

    }

}