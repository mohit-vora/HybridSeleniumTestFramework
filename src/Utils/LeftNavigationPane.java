package Utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LeftNavigationPane extends BrowserUtils {

	public static void NavigateTo(String mainMenu, String subMenu) {
		String clicksubmenu = "//span[contains(text(),'" + subMenu + "')]";
		boolean flagsubMenu = true;
		boolean flagMainMenu = true;
		try {
			if (driver.findElement(By.xpath(clicksubmenu)).isDisplayed()) {
				driver.findElement(By.xpath(clicksubmenu)).click();
			} else {
				List<WebElement> listMainMenu = driver.findElements(By.xpath("//span[@class='menuText']"));
				String submenuxpath = null;
				int lengthMainMenu = mainMenu.length();
				for (int i = 1; i < listMainMenu.size(); i++) {
					if (listMainMenu.get(i).getText().subSequence(0, lengthMainMenu).equals(mainMenu)) {
						flagMainMenu = true;
						String clickmainmenu = "//span[contains(text(),'" + mainMenu + "')]";
						driver.findElement(By.xpath(clickmainmenu)).click();
						submenuxpath = "//ul[@id='subMenuContainer" + i + "']//li//span[@class='subMenuText']";
						List<WebElement> list12 = driver.findElements(By.xpath(submenuxpath));
						for (WebElement webElement1 : list12) {
							int lengthSubMenu = subMenu.length();
							if (webElement1.getText().substring(0, lengthSubMenu).equals(subMenu)) {
								driver.findElement(By.xpath(clicksubmenu)).click();
								ReportLogger.info("Navigating to " + subMenu + "under" + mainMenu);
								flagsubMenu = true;
								break;
							} else {
								flagsubMenu = false;
							}
						}
						break;
					} else {
						flagsubMenu = false;
					}
				}
			}
			if (!flagMainMenu) {
				Assert.fail(mainMenu + " " + "might not be a webelement present in cyclos application");
			}
			if (!flagsubMenu) {
				Assert.fail(mainMenu + " " + "might not be a webelement present in cyclos application");
			}
		} catch (Exception NoSuchElementException) {
			ReportLogger.fail("LeftNavigationPane NosuchElement found error");
			Assert.fail(mainMenu + "/" + subMenu + " " + "might not be a webelement in cyclos application");
			// TODO: handle exception
		}
	}

	public static void NavigateTo(String mainMenu) {
		try {

			if (mainMenu.equals("Logout")) {
				String clickmainmenu = "//span[contains(text(),'" + mainMenu + "')]";
				if (driver.findElement(By.xpath(clickmainmenu)).isDisplayed()){
					driver.findElement(By.xpath(clickmainmenu)).click();
					ReportLogger.info("Navigating to " + mainMenu);
				}else{
					Assert.fail(mainMenu + " " + "might not be a present in the current page of cyclos application");
				}


			} else {
				Assert.fail(mainMenu + " " + "might not be a webelement present in cyclos application");
			}
		} catch (Exception e) {
			// TODO: handle exception
			ReportLogger.fail("LeftNavigationPane NosuchElement found error");
			Assert.fail(mainMenu + " " + "might not be a webelement in cyclos application");
		}

	}

}