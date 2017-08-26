package TestCases;

import org.testng.annotations.Test;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;




/* 
1.Following TestNg Test case pattern, the test methods are included to enroll the member.
 */
public class EnrollMember extends BrowserUtils{
  
    
    

    //Calling the DataProvider objects with its name "EnrollMember"
    //Operation:- Invoke register function
    @Test(dataProvider = "dProvider")
    public void Register(String dsid2,String dsid) throws Exception {
        LeftNavigationPane lnp = new LeftNavigationPane();
        lnp.NavigateTo(driver, "Users & Groups", "Manage Members");
        PageObjects.EnrollMember Em = new PageObjects.EnrollMember();
        Em.RegisterMember(driver, dsid);
        popup.PopUpAccept(driver);
    }

    
   

    
   
}