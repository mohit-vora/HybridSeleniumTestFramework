package Utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class LeftNavigationPane extends BrowserUtils{

    public static void NavigateTo(String mainMenu, String subMenu) {
    	String clicksubmenu = "//span[contains(text(),'" + subMenu + "')]";
    	if(driver.findElement(By.xpath(clicksubmenu)).isDisplayed()){
    		driver.findElement(By.xpath(clicksubmenu)).click();
    	}
    	else{
        List < WebElement > listMainMenu = driver.findElements(By.xpath("//span[@class='menuText']"));
        String submenuxpath = null;
        for (int i = 1; i < listMainMenu.size(); i++) {
            if (listMainMenu.get(i).getText().equals(mainMenu)) {
                String clickmainmenu = "//span[contains(text(),'" + mainMenu + "')]";
                driver.findElement(By.xpath(clickmainmenu)).click();
                submenuxpath = "//ul[@id='subMenuContainer" + i + "']//li//span[@class='subMenuText']";

                List < WebElement > list12 = driver.findElements(By.xpath(submenuxpath));
                for (WebElement webElement1: list12) {
                    if (webElement1.getText().equals(subMenu)) {
                        driver.findElement(By.xpath(clicksubmenu)).click();
                        logInfo("Navigating to "+ subMenu + "under" + mainMenu);
                        break;
                    }
                }
                break;
            }
        }
    }
    }

    public static void NavigateTo(String mainMenu) {

        if (mainMenu.equals("Logout")) {
            String clickmainmenu = "//span[contains(text(),'" + mainMenu + "')]";
            driver.findElement(By.xpath(clickmainmenu)).click();
            logInfo("Navigating to "+ mainMenu);


        }

    }
 
    

}