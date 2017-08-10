package PageObjects;

import org.openqa.selenium.WebDriver;

public class VerifyPopUp {

	public void PopUpAccept(WebDriver driver) {
		try {
			String PopUpMessage = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// System.out.println(PopUpMessage);
		// return PopUpMessage;

	}

}
