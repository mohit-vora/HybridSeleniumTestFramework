package PageObjects;

import java.io.IOException;
import ApplicationMap.ReadLocators;
import DataMap.ReadData;
import Utils.BrowserUtils;

public class MemberPaymentConfirmation extends BrowserUtils{
    public void verifyPaymentToMember(String dsid1, String dsid2) throws IOException {
        /**/
        Boolean flag = true;
        ReadData dm1 = new ReadData("MemberDetails", dsid1);
        ReadData dm2 = new ReadData("TransactionData", dsid2);
        ReadLocators rd1 = new ReadLocators("MemberPayment");
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String name[] = driver.findElement(rd1.getLocator("ELM_To")).getText().split("-");
        if (flag) {
            if (driver.findElement(rd1.getLocator("ELM_TransactionAmount")).getText()
                .equals(dm2.getData("Transaction_Amount"))) {
                if (driver.findElement(rd1.getLocator("ELM_TransactionDescription")).getText()
                    .equals(dm2.getData("Transaction_Description7"))
                ) {
                    if (driver.findElement(rd1.getLocator("ELM_TransactionType")).getText().equals(dm2.getData("Transaction_Type"))) {
                    	
                    	System.out.println(name[0]+"--"+name[1]);
                    	
                        if (name[0].trim().equals(dm1.getData("Transaction_Login"))) {
                            if (name[1].trim().equals(dm1.getData("Transaction_Name"))) {
                            } else {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    } else {
                    }
                } else {
                    flag = false;
                }
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
    if (flag)
            driver.findElement(rd1.getLocator("BTN_Success_Submit")).click();
    else
            driver.findElement(rd1.getLocator("BTN_Back")).click();
    }
}