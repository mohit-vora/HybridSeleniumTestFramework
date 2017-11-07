package applicationMap;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByAll;

import utils.BaseClass;
import utils.ReportLogger;

public class Locator extends BaseClass{
    private static HashMap <String, HashMap<String,ByAll>> testSpecificMap = new HashMap<String,HashMap<String,ByAll>>();

    
    String sheetName = null;
    /*Parameterized constructor which initializes the sheet name*/
    public Locator(String sname) {
        sheetName = sname; 
    }
   /*This method invokes getLocator from BaseClass to fetch the corresponding Locator values*/
    public ByAll getLocator(String elementName) {
    	return testSpecificMap.get(sheetName.toLowerCase()).get(elementName.toLowerCase());    }
    
	private static XSSFCell elementName;
	private static XSSFSheet sheet;
	
	
	public static void readAll(){
		
		if (preExecutionCheck){
			try
			{
		        FileInputStream mapSheet = new FileInputStream(System.getProperty("user.dir") + getPropVal("ApplicationMap"));
		        XSSFWorkbook workBook = new XSSFWorkbook(mapSheet);
		        
		        int noOfSheets = workBook.getNumberOfSheets();
		        
		        for (int sheetIndex=0;sheetIndex<noOfSheets;sheetIndex++)
		        {
					HashMap < String, ByAll > locatorMap = new HashMap < String, ByAll > ();
		        	sheet = workBook.getSheetAt(sheetIndex);
		        	XSSFCell mainLocator, mainValue, alternateLocator, alternateValue;
		            int rowIterator;
		            int rownum = sheet.getLastRowNum() - sheet.getFirstRowNum();

		            for (rowIterator = 1; rowIterator <= rownum; rowIterator++) {
		            	List < By > locators = new ArrayList < By > ();
		                elementName = sheet.getRow(rowIterator).getCell(0);
		                mainLocator = sheet.getRow(rowIterator).getCell(1);
		                mainValue = sheet.getRow(rowIterator).getCell(2);
		                alternateLocator = sheet.getRow(rowIterator).getCell(3);
		                alternateValue = sheet.getRow(rowIterator).getCell(4);
		                if (mainLocator != null && mainValue != null) {
		                    locators.add(generator(mainLocator.getStringCellValue(), mainValue.getStringCellValue()));
		                }
		                else{
		                	preExecutionCheck=false;
		                	ReportLogger.preExecutionFail("Main Locator cell is empty for element["+elementName.getStringCellValue()+"] inside AMap sheet ["+sheet.getSheetName()+"].");
		                }
		   

		                if (alternateLocator != null && alternateValue != null) {
		                    locators.add(generator(alternateLocator.getStringCellValue(), alternateValue.getStringCellValue()));

		                }
		                locatorMap.put(elementName.getStringCellValue().toLowerCase(), generatorAll(locators));
		            }
		            testSpecificMap.put(sheet.getSheetName().toLowerCase(), locatorMap);
		            
		        }
		        ReportLogger.info("Corresponding Element Locators Fetched");
		        
		        

		        workBook.close();
		        mapSheet.close();
		        
			}
			
			catch (Exception exception)
			{
				preExecutionCheck=false;
				ReportLogger.preExecutionFail(exception);
			}
		}
		
		
		
    }	
	 /* With the value retrieved from the Application Map By instances are created based on its Locator*/
	private static By generator(String locator, String value) {
        By by = null;
        if (!locator.equals("")) {
            switch (locator.toLowerCase()) {
                case "id":
                	by = By.id(value);
                    break;
                case "xpath":
                	by = By.xpath(value);
                    break;
                case "css":
                	by = By.cssSelector(value);
                    break;
                case "name":
                	by = By.name(value);
                	break;

                default:
                    ReportLogger.fatal("Something is wrong in application map "+sheet.getSheetName()+" for object: " + elementName);
                    break;

            }
        }

        return by;
    }
/*with the By instance created from generator method  ByAll insatnce is created*/
    private static ByAll generatorAll(List<By> byList) {
        if (byList.size() == 1) {
            return new ByAll(byList.get(0));
        } 
        else if(byList.size() == 2) {
            return new ByAll(byList.get(0), byList.get(1));
        }
        else{	
        	ReportLogger.fatal("ByAll couldn't be generated");
        	return null;
        }
    }

}