package TestCases;

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


    public void enrollNewMember(String adminCredentials,String memberToBeCreated,String accountType, String successMessage) {

			Login.performLogin(adminCredentials);
	        LeftNavigationPane .navigateTo("Users & Groups", "Manage Members");
	        EnrollMember.registerMember(memberToBeCreated,accountType);
	        validateAndAcceptPopup(successMessage);
	        LeftNavigationPane.navigateTo("Logout");
	}
	
	//memberPayments method test case invokes all the methods to complete a transaction from member. 
	
	@Test(dataProvider = "dp")
    public void memberPayments(String fromMemDSId, String toMemDSId, String TXNDSId) {
				
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
