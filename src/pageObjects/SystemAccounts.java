package pageObjects;

import applicationMap.Locator;
import utils.BaseClass;
import utils.WebTable;

public class SystemAccounts extends BaseClass {
	
	public static double extractDebitAccountBalance(){
		
		Locator rd1 = new Locator("SystemAccounts");
		
		WebTable table = new WebTable(rd1.getLocator("TBL_SYSTEM_ACCOUNTS"));
				
		double debitAccountBalance=0.0;
				
		for (int rowIndex=1; rowIndex<table.getRowSize();rowIndex++){
			if (table.getData(rowIndex, 0).equalsIgnoreCase("Debit account")){
				String balance = table.getData(rowIndex, 1);
				debitAccountBalance = Double.parseDouble(balance.substring(0, balance.length()-4).replace(",", ""));
				break;
			}		
		}
		return debitAccountBalance;
	}
}

