package PageObjects;

import Utils.BrowserUtils;

public class VerifyPopUp extends BrowserUtils{

    public void PopUpAccept() {
        try {
            String PopUpMessage = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            logInfo("Accepting Popup: " + PopUpMessage);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // System.out.println(PopUpMessage);
        // return PopUpMessage;
    }
}