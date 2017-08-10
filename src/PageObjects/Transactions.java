package PageObjects;

import org.openqa.selenium.WebDriver;

public class Transactions {

	public  void PopulatePaymenttoMember(WebDriver driver){
		String Logged_user = driver.findElement(rd1.getLocator("ELM_LOGGEDUSER")).getText();
		System.out.println(Logged_user);
		String[] User = Logged_user.split(" ",5);
		if (User[2].equalsIgnoreCase("admin")){
			
		}else{
			
		}
		
	}



}
