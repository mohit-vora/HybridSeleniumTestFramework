package PageObjects;

import ApplicationMap.ReadLocators;
import Utils.BrowserUtils;

public class Transactions extends BrowserUtils{
    public static void PopulatePaymenttoMember( ) {
        ReadLocators rd1 = new ReadLocators("RegisterMember");

        String Logged_user = driver.findElement(rd1.getLocator("ELM_LOGGEDUSER")).getText();
        System.out.println(Logged_user);
        String[] User = Logged_user.split(" ", 5);
        if (User[2].equalsIgnoreCase("admin")) {

        } else {
            driver.findElement(rd1.getLocator("TXB_Login")).sendKeys("TestUser01");
        }
    }
}