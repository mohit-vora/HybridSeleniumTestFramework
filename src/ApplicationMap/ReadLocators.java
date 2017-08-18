package ApplicationMap;


import org.openqa.selenium.support.pagefactory.ByAll;
import Interface.CommonInterface;

public class ReadLocators extends CommonInterface{

    
    String sheetName = null;
    
    public ReadLocators(String sname) {
        sheetName = sname;
       
    }

   
    public ByAll getLocator(String parm) {

        return getLocator(sheetName, parm);

    }

}