package utils;

import java.io.FileInputStream;
import java.util.LinkedHashMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestCaseSelector extends BaseClass{
	

	   /*reads the TestCase sheet to fetch the testcases marked with 'yes'*/
    static void readSheet() {

    	testCaseCount = 0;
    	try
    	{
    		FileInputStream mapSheet = new FileInputStream(System.getProperty("user.dir") + Property.getValueOf("TestCaseSelector"));
            XSSFWorkbook workBook = new XSSFWorkbook(mapSheet);

            XSSFSheet sheet = workBook.getSheet("TestCases");

            int currentRow;
            int rowNum = sheet.getLastRowNum() - sheet.getFirstRowNum();
            for (currentRow = 1; currentRow <= rowNum; currentRow++) {
                String runStatus = sheet.getRow(currentRow).getCell(3).getStringCellValue();
                if (runStatus.equalsIgnoreCase("Yes")) {
                	testCaseCount++;
                	String testName = sheet.getRow(currentRow).getCell(1).getStringCellValue();
                	String dataSetIds = sheet.getRow(currentRow).getCell(2).getStringCellValue();
                    setYesTestDatasets(testName.toLowerCase(), dataSetIds.toLowerCase());
                }
            }
            ReportLogger.info(testCaseCount+" TestCases to be executed retrieved from Testcase Sheet");
            workBook.close();
            mapSheet.close();
            
    	}
    	catch (Exception exception)
    	{
    		preExecutionCheck=false;
    		ReportLogger.preExecutionFail(exception);

    		extent.flush();
    		
    	}
        

    }
   
	
	//reading things ends here
	
	
	////this is where Driver Splitting things go
	
	private static LinkedHashMap<String, Object[][]> onlyYesTestCases = new LinkedHashMap<>();
	/*Reading the Dataset ids' provided accross each testcase marked as 'Yes'*/
	/**
	 * This method reads the Dataset ids' provided accross each testcase marked as 'Yes'
	 * in testCaseSheet.xlsx
	 * <P>
	 * It also checks for consecutive [,] or [;] and ignores it by giving a 
	 * 
	 * @param yesTestName 
	 * @param dataSetIds
	 */
	private static void setYesTestDatasets(String yesTestName, String dataSetIds)
	{
		int row=0;
		int col=0;
		
		
		int length = dataSetIds.length();
		boolean flag = true;
		for (int i=0;i<length-1;i++)
		{
			if ((dataSetIds.charAt(i)==';' && dataSetIds.charAt(i+1)==';')||
					(dataSetIds.charAt(i)==',' && dataSetIds.charAt(i+1)==',')){
				dataSetIds = dataSetIds.substring(0,i)+dataSetIds.substring(i+1,length);
				length--;
				i--;
				flag=false;
			}	
		}	
		if (dataSetIds.substring(length-1).equals(";")||dataSetIds.substring(length-1).equals(",")){
			dataSetIds=dataSetIds.substring(0, length-1);
			flag=false;
		}
		if (dataSetIds.substring(0,1).equals(";")||dataSetIds.substring(0,1).equals(",")){
			dataSetIds=dataSetIds.substring(1, length);
			flag=false;
		}
		if (!flag){
			ReportLogger.warn("You have probably extra [;] or [,] somewhere in test data for ["+yesTestName+"], assuming: "+dataSetIds);
		}
		row=dataSetIds.split(";").length;
		col=dataSetIds.split(";")[0].split(",").length;
//		System.out.println("i calculated row col");
		Object[][] s1 = new String [row][col];
//		System.out.println("row & col"+row+col);
		for (int rowIterator=0;rowIterator<row;rowIterator++)
		{			
			for (int columnIterator=0;columnIterator<col;columnIterator++)
			{
				s1[rowIterator][columnIterator] = dataSetIds.split(";")[rowIterator].split(",")[columnIterator].toLowerCase();
			}
		}
		ReportLogger.info("Data Set ID for the chosen TestCase: "+ yesTestName +"is read");
//		System.out.println("read ids");
		onlyYesTestCases.put(yesTestName,s1);
	}
	
	
	/*Returns the test data as object array*/
	static Object[][] getYesTestDetails(String testName){
		String lowerCaseTestName = testName.toLowerCase();
		Object[][] dataSetIds = new Object[][]{};
		
		if (onlyYesTestCases.containsKey(lowerCaseTestName)){
			dataSetIds = onlyYesTestCases.get(lowerCaseTestName);
		}
		else{
			ReportLogger.warn("Skipping ["+testName+"].");
		}
		
		return dataSetIds;
	}

	
}
