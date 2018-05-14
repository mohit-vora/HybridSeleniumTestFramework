package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.testng.Assert;

import dataMap.Data;

public class Validate extends BaseClass{
public static boolean balance(String transactionId, double balaceBefore, double balanceAfter, String checkWhat){
		
		boolean status = false;
		double difference = 0.0;
		Data dm1 = new Data("TransactionData", transactionId);
		double transferredAmount = Double.parseDouble(dm1.getData("Transaction_amount"));
		
		if (checkWhat.equalsIgnoreCase("increased")){
			difference = balanceAfter - balaceBefore;
		}
		else if (checkWhat.equalsIgnoreCase("decreased")){
			difference = balaceBefore - balanceAfter;
		}
		
		if (difference == transferredAmount){
			status = true;
		}
		
		return status;
	}

	/*This method handles the popup that occurs during the scenario execution*/
	public static void popupAndAccept(String dsid) {
		Data data = new Data("Messages", dsid);
		
		Alert alert = driver.switchTo().alert();
		
		String popUpMessage = alert.getText();
		
		if(popUpMessage.equals(data.getData("MESSAGE_TEXT"))){
			alert.accept();
			ReportLogger.info("Popup accepted :"+popUpMessage);
		}
		else{
			//for dismissing the popup
			alert.dismiss();
			Assert.fail("Popup message didn't match. Expected: "+data.getData("MESSAGE_TEXT")
			+ "Actual: "+popUpMessage);  
	
		}     
	}
	
	public static boolean isElementPresent(ByAll bys){
		return (driver.findElements(bys).isEmpty());
	}

}
