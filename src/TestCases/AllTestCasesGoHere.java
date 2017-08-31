package TestCases;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import PageObjects.EnrollMember;
import PageObjects.Login;
import PageObjects.MemberPayment;
import PageObjects.MemberPaymentConfirmation;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;

public class AllTestCasesGoHere extends BrowserUtils{

	@Test(dataProvider = "dp")
    public void EnrollNewMember(String dsid1,String dsid2) {
		try{
			Login.performLogin(dsid1);
	        LeftNavigationPane .NavigateTo("Users & Groups", "Manage Members");
	        EnrollMember.RegisterMember(dsid2);
	        PopUpAccept();
	        LeftNavigationPane.NavigateTo("Logout");
	        PopUpAccept();
		}
		catch (Exception e)
		{
			test.log(Status.FAIL, e);
		}
		
    }
	
	@Test(dataProvider = "dp")
    public void MemberPayments(String fromMemDSId, String toMemDSId, String TXNDSId){
		try{
			Login.performLogin(fromMemDSId);
			LeftNavigationPane.NavigateTo("Account", "Member Payment");
	        MemberPayment.PopulatePaymenttoMember(toMemDSId, TXNDSId);
	        MemberPaymentConfirmation.verifyPaymentToMember(toMemDSId,TXNDSId);
	        LeftNavigationPane.NavigateTo("Logout");
	        PopUpAccept();
		}
		
		catch (Exception e)
		{
			test.log(Status.FAIL, "problem in Member Payments");
		}
        
    }
	
	
}
