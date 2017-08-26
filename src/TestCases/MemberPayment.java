package TestCases;

import java.io.IOException;
import org.testng.annotations.Test;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;


public class MemberPayment extends BrowserUtils{


   
    //Calling the DataProvider objects with its name "PayMember"
    //Operation:- Invoke Left navigation and Populate payment to member method.
    @Test(dataProvider = "dProvider")
    public void paymentToMember(String fromMemDSId, String toMemDSId, String TXNDSId) throws IOException, InterruptedException{
        LeftNavigationPane lnp = new LeftNavigationPane();
        lnp.NavigateTo(driver, "Account", "Member Payment");
        PageObjects.MemberPayment em = new PageObjects.MemberPayment();
        em.PopulatePaymenttoMember(driver, toMemDSId, TXNDSId);
    }

   

}