package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import ApplicationMap.ReadLocators;
import DataMap.ReadData;

public class MemberPaymentConfirmation {
    public void verifyPaymentToMember(WebDriver driver, String dsid) throws IOException {
        /**/
        Boolean flag = true;
        ReadData dm = new ReadData("MemberPayment", dsid);
        ReadLocators rd1 = new ReadLocators("MemberPayment");
        String name[] = driver.findElement(rd1.getLocator("ELM_To")).getText().split("-");

        if (flag) {
            driver.findElement(rd1.getLocator("ELM_TransactionAmount")).getText()
                .equals(dm.getData("Transaction_Amount"));
            driver.findElement(rd1.getLocator("ELM_TransactionType")).getText().equals(dm.getData("Transaction_Type"));

            name[0].trim().equals(dm.getData("Transaction_Login"));
            name[1].trim().equals(dm.getData("Transaction_Name"));
            driver.findElement(rd1.getLocator("ELM_TransactionDescription")).getText()
                .equals(dm.getData("Transaction_Description7"));

        } else {
            flag = false;
        }
        if (flag)
            driver.findElement(rd1.getLocator("BTN_Success_Submit")).click();

        else

            driver.findElement(rd1.getLocator("BTN_Back")).click();
    }

}