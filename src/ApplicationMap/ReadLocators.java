package ApplicationMap;


import org.openqa.selenium.support.pagefactory.ByAll;

public class ReadLocators extends Utils.BaseClass{

    
    String sheetName = null;
    /*Parameterized constructor which initializes the sheet name*/
    public ReadLocators(String sname) {
        sheetName = sname; 
    }
   /*This method invokes getLocator from BaseClass to fetch the corresponding Locator values*/
    public ByAll getLocator(String elementName) {
        return getLocator(sheetName, elementName);
    }

}