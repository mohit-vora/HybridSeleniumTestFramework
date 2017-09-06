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

    public void EnrollNewMember(String adminCredentials,String memberToBeCreated,String accountType, String successMessage) throws IOException, InterruptedException {

			Login.performLogin(adminCredentials);
	        LeftNavigationPane .NavigateTo("Users & Groups", "Manage Members");
	        EnrollMember.RegisterMember(memberToBeCreated,accountType);
	        PopUpAccept(successMessage);
	        LeftNavigationPane.NavigateTo("Logout");
    }
	
	@Test(dataProvider = "dp")
    public void MemberPayments(String fromMemDSId, String toMemDSId, String TXNDSId) throws Exception {
			
			
				Login.performLogin(toMemDSId);
		    	AccountBalance accnt = new AccountBalance();
		    	accnt.XtractAccountBalance(TXNDSId,"ToAccount"); 
		    	LeftNavigationPane.NavigateTo("Logout");
		        	        
		        Login.performLogin(fromMemDSId);
		    	accnt.XtractAccountBalance(TXNDSId,"FromAccount");   	
		    	LeftNavigationPane.NavigateTo("Acco", "Member Payment");
		        MemberPayment.PopulatePaymenttoMember(toMemDSId, TXNDSId);
		        MemberPaymentConfirmation.verifyPaymentToMember(toMemDSId,TXNDSId);
		        accnt.XtractAccountBalance("FromAccount");
		    	accnt.verifiyDebitAccount();
		    	LeftNavigationPane.NavigateTo("Logout");
		        
		        Login.performLogin(toMemDSId);
		        accnt.XtractAccountBalance("ToAccount");
		    	accnt.verifiyCreditAccount();
		    	LeftNavigationPane.NavigateTo("Logout");
			
			
	}

}
