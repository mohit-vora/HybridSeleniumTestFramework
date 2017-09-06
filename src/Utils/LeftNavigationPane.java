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
		boolean flagsubMenu = false;
		boolean flagMainMenu = false;
		String dummy = "";
		try{

		if (driver.findElement(By.xpath(clicksubmenu)).isDisplayed()) {
			driver.findElement(By.xpath(clicksubmenu)).click();

		} else {
			List<WebElement> listMainMenu = driver.findElements(By.xpath("//span[@class='menuText']"));
			String submenuxpath = null;
			int lengthMainMenu = mainMenu.length();
			for (int listIndex = 1; listIndex < listMainMenu.size(); listIndex++) {
				if (listMainMenu.get(listIndex).getText().length() >= lengthMainMenu) {
					dummy = listMainMenu.get(listIndex).getText().substring(0, lengthMainMenu);

					if (listMainMenu.get(listIndex).getText().substring(0, lengthMainMenu).equals(mainMenu)) {
						String clickmainmenu = "//span[contains(text(),'" + mainMenu + "')]";
						driver.findElement(By.xpath(clickmainmenu)).click();
						flagMainMenu=true;
						submenuxpath = "//ul[@id='subMenuContainer" + listIndex + "']//li//span[@class='subMenuText']";
						List<WebElement> list12 = driver.findElements(By.xpath(submenuxpath));
						for (WebElement webElement1 : list12) {
							int lengthSubMenu = subMenu.length();
							if (webElement1.getText().substring(0, lengthSubMenu).equals(subMenu)) {
								driver.findElement(By.xpath(clicksubmenu)).click();
								flagsubMenu = true;
								ReportLogger.info("Navigating to " + subMenu + "under" + mainMenu);
								break;
							} 
						}
						break;
					} 
				}
			}
		}
		if (!flagMainMenu) {
			System.out.println(dummy+"--"+mainMenu);
			Assert.fail(mainMenu + " " + "might not be a webelement present in cyclos application");
		}
		if (!flagsubMenu) {
			Assert.fail(subMenu + " " + "might not be a webelement present in cyclos application");
		}
	    } catch (NoSuchElementException Exception) {
		 ReportLogger.fail("LeftNavigationPane NosuchElement found error");
		 Assert.fail(mainMenu + "/" + subMenu + " " + "might not be a webelement in cyclos application");
		 // TODO: handle exception
		 }
	}
/*This method helps in navigating to link without sublink in the application */
	public static void navigateTo(String mainMenu) {
		try {


			if (mainMenu.equals("Logout")&& isLoggedIn) {
				String clickmainmenu = "//span[contains(text(),'" + mainMenu + "')]";
				if (driver.findElement(By.xpath(clickmainmenu)).isDisplayed()){
					driver.findElement(By.xpath(clickmainmenu)).click();
			        LogoutPopUpAccept();
					isLoggedIn = false;
					ReportLogger.info("Navigating to " + mainMenu);
				}else{
					Assert.fail(mainMenu + " " + "might not be a present in the current page of cyclos application");
				}


			} 
		} catch (Exception e) {
			// TODO: handle exception
			ReportLogger.fail("LeftNavigationPane NosuchElement found error");


			Assert.fail(mainMenu + " " + "might not be a webelement in cyclos application");
		}

	}

}