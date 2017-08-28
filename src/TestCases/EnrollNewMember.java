package TestCases;

import org.testng.annotations.Test;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;
import PageObjects.EnrollMember;




/* 
1.Following TestNg Test case pattern, the test methods are included to enroll the member.
 */
public class EnrollNewMember extends BrowserUtils{
  
    //Calling the DataProvider objects with its name "EnrollMember"
    //Operation:- Invoke register function
    @Test(dataProvider = "dProvider")
    public void Register(String dsid2,String dsid) throws Exception {
        LeftNavigationPane .NavigateTo("Users & Groups", "Manage Members");
        EnrollMember.RegisterMember(dsid);
        PopUpAccept();
    }

    
   

    
   
}