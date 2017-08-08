package Utils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByAll;

import ApplicationMap.ReadSheet;

public class LeftNavigationPane {
	Map<String, Map<String, ByAll>> input = new TreeMap<String, Map<String, ByAll>>();
	ReadSheet rd1 = new ReadSheet();

	public Void NavigateTo(WebDriver driver){
		WebElement leftPanes = driver.findElement(By.xpath("//span[contains(text(),'Accounts')]"));
		List<WebElement> submenu = driver.findElements(By.className("subMenuText"));
		for (WebElement subm : submenu){
			System.out.println(subm);
		}
		return null;
		
	}
}
