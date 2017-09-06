package TestCases;

import java.io.IOException;
import org.testng.annotations.Test;
import PageObjects.AccountBalance;
import PageObjects.EnrollMember;
import PageObjects.Login;
import PageObjects.MemberPayment;
import PageObjects.MemberPaymentConfirmation;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;

public class AllTestCasesGoHere extends BrowserUtils {

	@Test(dataProvider = "dp")

    public void EnrollNewMember(String dsid1,String dsid2,String dsid3) throws IOException, InterruptedException {

			Login.performLogin(dsid1);
	        LeftNavigationPane .navigateTo("Users & Groups", "Manage Members");
	        EnrollMember.RegisterMember(dsid2);
	        PopUpAccept(dsid3);
	        LeftNavigationPane.navigateTo("Logout");
    }
	
	@Test(dataProvider = "dp")
    public void MemberPayments(String fromMemDSId, String toMemDSId, String TXNDSId) throws Exception {
			
			
				Login.performLogin(toMemDSId);
		    	AccountBalance accnt = new AccountBalance();
		    	accnt.XtractAccountBalance(TXNDSId,"ToAccount"); 
		    	LeftNavigationPane.navigateTo("Logout");
		        	        
		        Login.performLogin(fromMemDSId);
		    	accnt.XtractAccountBalance(TXNDSId,"FromAccount");   	
		    	LeftNavigationPane.navigateTo("Acco", "Member Payment");
		        MemberPayment.PopulatePaymenttoMember(toMemDSId, TXNDSId);
		        MemberPaymentConfirmation.verifyPaymentToMember(toMemDSId,TXNDSId);
		        accnt.XtractAccountBalance("FromAccount");
		    	accnt.verifiyDebitAccount();
		    	LeftNavigationPane.navigateTo("Logout");
		        
		        Login.performLogin(toMemDSId);
		        accnt.XtractAccountBalance("ToAccount");
		    	accnt.verifiyCreditAccount();
		    	LeftNavigationPane.navigateTo("Logout");
			
			
	}

}
