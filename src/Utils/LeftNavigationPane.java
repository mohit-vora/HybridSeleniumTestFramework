package Utils;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LeftNavigationPane extends BaseClass {
	

	/*This method helps in navigating to different links with sublink in the application */

	public static void navigateTo(String mainMenu, String subMenu) {
		String clicksubmenu = "//span[contains(text(),'" + subMenu + "')]";
		boolean flagSubMenu = false;
		boolean flagMainMenu = false;

		if (driver.findElement(By.xpath(clicksubmenu)).isDisplayed()) {
			driver.findElement(By.xpath(clicksubmenu)).click();
			flagSubMenu = true;
			flagMainMenu = true;
		} 
		else {
			List<WebElement> listMainMenu = driver.findElements(By.xpath("//span[@class='menuText']"));
			String submenuxpath = null;
			int lengthMainMenu = mainMenu.length();

			for (int listIndex = 1; listIndex < listMainMenu.size(); listIndex++) {
				if (listMainMenu.get(listIndex).getText().length() >= lengthMainMenu) {

					if (listMainMenu.get(listIndex).getText().substring(0, lengthMainMenu).equals(mainMenu)) {
						String clickmainmenu = "//span[contains(text(),'" + mainMenu + "')]";
						driver.findElement(By.xpath(clickmainmenu)).click();
						flagMainMenu=true;
						submenuxpath = "//ul[@id='subMenuContainer" + listIndex + "']//li//span[@class='subMenuText']";
						List<WebElement> allSubMenusInMainMenu = driver.findElements(By.xpath(submenuxpath));
						for (WebElement webElement : allSubMenusInMainMenu) {
							int lengthSubMenu = subMenu.length();
							if (webElement.getText().substring(0, lengthSubMenu).equals(subMenu)) {
								driver.findElement(By.xpath(clicksubmenu)).click();
								flagSubMenu = true;
								ReportLogger.info("Navigating to " + subMenu + " under " + mainMenu);
								break;
							} 
						}
						break;
					}
				}
			}
		}
		if (!flagMainMenu) {
			Assert.fail(mainMenu + " might not be a webelement present in cyclos application");
		}
		if (!flagSubMenu) {
			Assert.fail(subMenu + " might not be a webelement present in cyclos application");
		}
	}
	
/*This method helps in navigating to link without sublink in the application */
	public static void logOutOfApplication() {
		try {
				String logoutXPath = "//span[contains(text(),'Logout')]";
					driver.findElement(By.xpath(logoutXPath)).click();
					validateAndAcceptPopup("MSG002");
					isLoggedIn = false;
					ReportLogger.info("Successfully logged out"); 
			} 
		catch (Exception e) {
			ReportLogger.info("User already logged out");
		}
	}
	
	public static void checkLoginSuccessful(){
		try{
			driver.findElement(By.xpath("//span[contains(text(),'Logout')]"));
			ReportLogger.pass("Login Action Performed");
			isLoggedIn = true;
		}
		catch (Exception e) {
			driver.navigate().back();
			Assert.fail("LoginFails");
		}//handle exception when element not present in web page and when login not performed
	}
}