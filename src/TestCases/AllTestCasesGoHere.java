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
	
	//enrollNewMember method test case invokes all the methods to enroll new member. 

	@Test(dataProvider = "dp")

    public void enrollNewMember(String dsid1,String dsid2,String dsid3) throws IOException, InterruptedException {

			Login.performLogin(dsid1);
	        LeftNavigationPane .navigateTo("Users & Groups", "Manage Members");
	        EnrollMember.registerMember(dsid2);
	        PopUpAccept(dsid3);
	        LeftNavigationPane.navigateTo("Logout");
    }
	
	//memberPayments method test case invokes all the methods to complete a transaction from member. 
	
	@Test(dataProvider = "dp")
    public void memberPayments(String fromMemDSId, String toMemDSId, String TXNDSId) throws Exception {
			
			
				Login.performLogin(toMemDSId);
		    	AccountBalance accnt = new AccountBalance();
		    	accnt.xtractAccountBalance(TXNDSId,"ToAccount"); 
		    	LeftNavigationPane.navigateTo("Logout");
		        	        
		        Login.performLogin(fromMemDSId);
		    	accnt.xtractAccountBalance(TXNDSId,"FromAccount");   	
		    	LeftNavigationPane.navigateTo("Acco", "Member Payment");
		        MemberPayment.populatePaymenttoMember(toMemDSId, TXNDSId);
		        MemberPaymentConfirmation.verifyPaymentToMember(toMemDSId,TXNDSId);
		        accnt.xtractAccountBalance("FromAccount");
		    	accnt.verifiyDebitAccount();
		    	LeftNavigationPane.navigateTo("Logout");
		        
		        Login.performLogin(toMemDSId);
		        accnt.xtractAccountBalance("ToAccount");
		    	accnt.verifiyCreditAccount();
		    	LeftNavigationPane.navigateTo("Logout");
			
			
	}

}
