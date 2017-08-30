package TestCases;

import java.io.IOException;

import org.testng.annotations.Test;

import PageObjects.EnrollMember;
import PageObjects.MemberPayment;
import PageObjects.MemberPaymentConfirmation;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;

public class AllTestCasesGoHere extends BrowserUtils{

	@Test(dataProvider = "dp")
    public void EnrollNewMember(String dsid2,String dsid) throws Exception {
        LeftNavigationPane .NavigateTo("Users & Groups", "Manage Members");
        EnrollMember.RegisterMember(dsid);
        PopUpAccept();
    }
	
	@Test(dataProvider = "dp")
    public void MemberPayments(String fromMemDSId, String toMemDSId, String TXNDSId) throws IOException, InterruptedException{
        LeftNavigationPane.NavigateTo("Account", "Member Payment");
        MemberPayment.PopulatePaymenttoMember(toMemDSId, TXNDSId);
        MemberPaymentConfirmation.verifyPaymentToMember(toMemDSId,TXNDSId);
    }
}
