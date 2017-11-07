package dataMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import utils.BaseClass;
import utils.ReportLogger;

public class Data extends BaseClass{ 
	
	String dataSetId = null;
	String sheetName = null;
    private static HashMap <String, LinkedHashMap<String,ArrayList<String>> > testSpecificData = new HashMap < String, LinkedHashMap<String,ArrayList<String>>> ();

/*Parameterized constructor which initializes the datasheet name and the data id and fetches the corresponding data*/
    public Data(String name, String id) {
    	sheetName = name;
    	dataSetId = id;
        ArrayList<String> list = new ArrayList<String>();
        list = testSpecificData.get(sheetName.toLowerCase()).get(id.toLowerCase());

    	if (list==null){
    		ReportLogger.fatal(id +" did not match any id in "+ sheetName.toLowerCase());
    		
    	} 	
        try{
        	for (String element:list){
            	if (!(element.length()>0)){
            		ReportLogger.warn(id +" has empty data columns. Please check DataMap sheet: "+ sheetName);
            		break;
            	}
            } 	
        }
        catch (NullPointerException e){
        	Assert.fail("Check above warnings. Problem in: "+ sheetName);
        }
            
        
    }
    /*This method invokes getdata from */
    public String getData(String col) {
 
    	String dataValue = "";
        String lowerCasedSName = sheetName.toLowerCase();
        String lowerCaseId = dataSetId.toLowerCase(); 
        ArrayList<String> listOfColumns = testSpecificData.get(lowerCasedSName).get(testSpecificData.get(lowerCasedSName).keySet().toArray()[0]);
        int dataColumn = 0;
        boolean flag = false;
        for (; dataColumn < listOfColumns.size(); dataColumn++) {
            if (col.equalsIgnoreCase(listOfColumns.get(dataColumn))) {
                flag = true;
                break;
            }
        }
        
        if (flag) {
            ArrayList<String> dataList = testSpecificData.get(sheetName.toLowerCase()).get(lowerCaseId);
        	dataValue = dataList.get(dataColumn);

        } else {
        	Assert.fail("There is nothing like " + col + " in " + sheetName+ " sheet in file "+ "Data.xlsx in reourxes folder");

        }
        return dataValue;
    }
    
  //this is where data map things start
    
    public static void readAll(){
    	try{
    		if (preExecutionCheck){
        		FileInputStream fileStream = new FileInputStream(System.getProperty("user.dir") + getPropVal("DataMap"));
                XSSFWorkbook workbook = new XSSFWorkbook(fileStream);

                int noOfSheets = workbook.getNumberOfSheets();
                
                
                for (int sheetIndex=0;sheetIndex<noOfSheets;sheetIndex++)
                {  
                	XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                	LinkedHashMap<String,ArrayList<String>> sheetData = new LinkedHashMap<String, ArrayList<String>>();

        	        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        	        int colCount = sheet.getRow(0).getLastCellNum();
        	
        	        DataFormatter dataFormatter = new DataFormatter();
        	
        	        for (int rowIterater = 0; rowIterater <= rowCount; rowIterater++){
        	            ArrayList<String> dataList = new ArrayList<String>();
        	            for (int colIterater = 1; colIterater < colCount; colIterater++){
        	            	dataList.add(dataFormatter.formatCellValue(sheet.getRow(rowIterater).getCell(colIterater)));            	
        	            }
        	            sheetData.put(dataFormatter.formatCellValue(sheet.getRow(rowIterater).getCell(0)).toLowerCase(), dataList);  
        	        }
               
        	        testSpecificData.put(sheet.getSheetName().toLowerCase(),sheetData);
                }
                workbook.close();
        	}
    	}
    	
    	catch (IOException e){
    		preExecutionCheck=false;
			ReportLogger.fatal("problem in ReadAllData "+e);
    	}   
    }

    

}