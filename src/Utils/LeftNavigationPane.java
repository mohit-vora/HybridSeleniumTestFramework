package Utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeftNavigationPane {

	public void NavigateTo(WebDriver driver, String mainMenu, String subMenu) {
		List<WebElement> listMainMenu = driver.findElements(By.xpath("//span[@class='menuText']"));
		for (int i = 1; i < listMainMenu.size(); i++) {
			System.out.println("text" + listMainMenu.get(i).getText());
			if (mainMenu.equals("Logout")) {
				String clickmainmenu = "//span[contains(text(),'" + mainMenu + "')]";
				driver.findElement(By.xpath(clickmainmenu)).click();
				break;
			} else if (listMainMenu.get(i).getText().equals(mainMenu)) {
				String clickmainmenu = "//span[contains(text(),'" + mainMenu + "')]";
				System.out.println(clickmainmenu);
				driver.findElement(By.xpath(clickmainmenu)).click();
				String submenuxpath = "//ul[@id='subMenuContainer" + i + "']//li//span[@class='subMenuText']";
				List<WebElement> list12 = driver.findElements(By.xpath(submenuxpath));
				for (WebElement webElement1 : list12) {
					System.out.println("text" + webElement1.getText());
					if (webElement1.getText().equals(subMenu)) {
						String clicksubmenu = "//span[contains(text(),'" + subMenu + "')]";
						driver.findElement(By.xpath(clicksubmenu)).click();
					}
				}

			}
		}

	}

	public void NavigateTo(WebDriver driver, String mainMenu) {

		if (mainMenu.equals("Logout")) {
			String clickmainmenu = "//span[contains(text(),'" + mainMenu + "')]";
			driver.findElement(By.xpath(clickmainmenu)).click();

		}

	}

}
