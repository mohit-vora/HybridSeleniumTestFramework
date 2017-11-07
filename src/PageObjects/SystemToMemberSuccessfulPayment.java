package PageObjects;

import org.testng.Assert;

import applicationMap.Locator;
import utils.BaseClass;
import utils.ReportLogger;

public class SystemToMemberSuccessfulPayment extends BaseClass{
	
	public static void verifySuccessfulPayment(){
		
		Locator rd = new Locator("PaymentSystemToMember");
		
		if (driver.findElement(rd.getLocator("ele_success_header")).isDisplayed()){
			ReportLogger.info("Amount deposited successfully");
		}
		else{
			Assert.fail("Successful Payment header not displayed!!");
		}
	}

}
