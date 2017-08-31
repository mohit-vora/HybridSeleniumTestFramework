package ApplicationMap;


import org.openqa.selenium.support.pagefactory.ByAll;

public class ReadLocators extends Utils.BaseClass{

    
    String sheetName = null;
    
    public ReadLocators(String sname) {
        sheetName = sname; 
    }
   
    public ByAll getLocator(String parm) {
        return getLocator(sheetName, parm);
    }

}