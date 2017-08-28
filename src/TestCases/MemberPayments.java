package TestCases;

import java.io.IOException;
import org.testng.annotations.Test;

import PageObjects.MemberPayment;
import PageObjects.MemberPaymentConfirmation;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;


public class MemberPayments extends BrowserUtils{


   
    //Calling the DataProvider objects with its name "PayMember"
    //Operation:- Invoke Left navigation and Populate payment to member method.
    @Test(dataProvider = "dProvider")
    public void paymentToMember(String fromMemDSId, String toMemDSId, String TXNDSId) throws IOException, InterruptedException{
        LeftNavigationPane.NavigateTo("Account", "Member Payment");
        MemberPayment.PopulatePaymenttoMember(toMemDSId, TXNDSId);
        MemberPaymentConfirmation.verifyPaymentToMember(toMemDSId,TXNDSId);
    }

   

}