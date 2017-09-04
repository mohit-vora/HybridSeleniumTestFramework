package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import DataMap.ReadData;

public class BaseClass {
	
	public static WebDriver driver = null;
    public static ExtentTest test;
    protected static int testCaseCount = 0;
    protected static boolean preExecutionCheck = true;


	//reading things go here
    public void openBrowserChrome() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+getPropVal("chromeDriver"));
        driver = new ChromeDriver();
        driver.get(getPropVal("url"));
        ReportLogger.info("Browser Instance opened");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        ReportLogger.info("Browser Window Maximized");
    }
    
    public void readTestCaseSheet() {

    	testCaseCount = 0;
    	try
    	{
    		FileInputStream mapSheet = new FileInputStream(System.getProperty("user.dir") + "\\TestResources\\TestCaseSheet.xlsx");
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
                    setYesTestDetails(testName, dataSetIds);
                }
            }
            ReportLogger.info("Test Cases to be executed retrieved from Testcase Sheet");
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
	public void PopUpAccept() {
        
        	String popUpMessage = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            ReportLogger.info("Popup accepted :"+popUpMessage);
                
    }
	public void PopUpAccept(String dsid) throws IOException {
		ReadData data = new ReadData("PopupMessages", dsid);
    	String popUpMessage = driver.switchTo().alert().getText();
    	
    	if(popUpMessage.equals(data.getData("MESSAGE_TEXT"))){
    		driver.switchTo().alert().accept();
    		ReportLogger.info("Popup accepted :"+popUpMessage);
    	}
    	else{
    		driver.switchTo().alert().accept();
    		ReportLogger.info("Enrollment was not successfull refer to the Stack trace below");
    		Assert.assertEquals(popUpMessage, data.getData("MESSAGE_TEXT"));
    		 
    	}
        
        
            
}
	////this is where Driver Splitting things go
	
	public static LinkedHashMap<String, Object[][]> onlyYesTestCases = new LinkedHashMap<String, Object[][]>();
	
	private  void setYesTestDetails(String yesTestName, String dataSetIds)
	{
		int row=0;
		int col=0;
		
		row=dataSetIds.split(";").length;
		int counter=0;
		for (int i=0;i<dataSetIds.length();i++)
		{
			if (dataSetIds.charAt(i)==';')
				counter++;
		}


		if (counter!=row-1){
			ReportLogger.warn(yesTestName+" ignoring last ; as no dataset ids after that");
		}
	
		col=dataSetIds.split(";")[0].split(",").length;
		System.out.println("i calculated row col");
		Object[][] s1 = new String [row][col];
		System.out.println("row & col"+row+col);
		for (int rowIterator=0;rowIterator<row;rowIterator++)
		{			
			for (int columnIterator=0;columnIterator<col;columnIterator++)
			{
				s1[rowIterator][columnIterator] = dataSetIds.split(";")[rowIterator].split(",")[columnIterator];
			}
		}
		ReportLogger.info("Data Set ID for the chosen TestCase: "+ yesTestName +"is read");
		System.out.println("read ids");
		onlyYesTestCases.put(yesTestName,s1);
	}
	
	public Object[][] getYesTestDetails(String testName)
	{
		Object[][] dataSetIds = new Object[][]{};
		
		if (onlyYesTestCases.containsKey(testName))
			dataSetIds = onlyYesTestCases.get(testName);
		
		return dataSetIds;
	}

	
	//this is where driver splitting things end
	//properties file related code
	public static String getPropVal(String parm) {
//      File file = new File(System.getProperty("user.dir") + "\\resources\\path.properties");

  	File filePath = new File(System.getProperty("user.dir") + "\\TestResources\\path.properties");
  	
      FileInputStream fileInput = null;
      try {
          fileInput = new FileInputStream(filePath);
          ReportLogger.info("Traced Location of Path file");
      } catch (FileNotFoundException fileNotFoundException) {
          
    	 ReportLogger.preExecutionFail(fileNotFoundException);
      }

      Properties properties = new Properties();

      try {
    	  properties.load(fileInput);
          ReportLogger.info("Loaded the path file");
      } catch (IOException ioException) {
    	  ReportLogger.preExecutionFail(ioException);
      }

      return properties.getProperty(parm);
  }
	
	
	
	
	//properties file related code ends here
	
	//this is where application map reading starts
	private static HashMap <String,HashMap<String,ByAll>> testSpecificMap = new HashMap<String,HashMap<String,ByAll>>();
	XSSFCell elementName;
	XSSFSheet sheet;
	
	
	public void ReadAllLocators(){
		try
		{
			HashMap < String, ByAll > locatorMap = new HashMap < String, ByAll > ();
	        FileInputStream mapsheet = new FileInputStream(System.getProperty("user.dir") + "\\TestResources\\ApplicationMap\\AMa.xlsx");
	        XSSFWorkbook WorkBook = new XSSFWorkbook(mapsheet);

	        List < By > locators = null;
	        
	        int noOfSheets = WorkBook.getNumberOfSheets();
	        
	        for (int sheetIndex=0;sheetIndex<noOfSheets;sheetIndex++)
	        {
	        
	        	sheet = WorkBook.getSheetAt(sheetIndex);
	        	XSSFCell mainLocator, mainValue, alternateLocator, alternateValue;
	            int rowIterator;
	            int rownum = sheet.getLastRowNum() - sheet.getFirstRowNum();
	            //		System.out.println(sheetName+rownum);
	            for (rowIterator = 1; rowIterator <= rownum; rowIterator++) {
	                locators = new ArrayList < By > ();
	                elementName = sheet.getRow(rowIterator).getCell(0);
	                mainLocator = sheet.getRow(rowIterator).getCell(1);
	                mainValue = sheet.getRow(rowIterator).getCell(2);
	                alternateLocator = sheet.getRow(rowIterator).getCell(3);
	                alternateValue = sheet.getRow(rowIterator).getCell(4);
	                if (mainLocator != null && mainValue != null) {
	                    locators.add(generator(mainLocator.getStringCellValue().toLowerCase(), mainValue.getStringCellValue()));
	                }
	                else{
	                	ReportLogger.warn("Main Locator cell is empty");
	                	Assert.fail("Main Locator cell is empty");
	                }
	   

	                if (alternateLocator != null && alternateValue != null) {
	                    locators.add(generator(alternateLocator.getStringCellValue().toLowerCase(), alternateValue.getStringCellValue()));

	                }
	                locatorMap.put(elementName.getStringCellValue(), generatorAll(locators));
	            }
	            testSpecificMap.put(sheet.getSheetName(), locatorMap);
	        }
	        ReportLogger.info("Corresponding Element Locators Fetched");
	        
	        

	        WorkBook.close();
		}
		
		catch (Exception exception)
		{
			preExecutionCheck=false;
			ReportLogger.fatal("problem in ReadAllLocators "+exception);
		}
		
    }	
	
	private By generator(String locator, String value) {
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

                default:
                    ReportLogger.fatal("Something is wrong in application map "+sheet+" of object: " + elementName);
                    break;

            }
        }

        return by;
    }

    private ByAll generatorAll(List < By >byList) {
        if (byList.size() == 1) {
            return new ByAll(byList.get(0));
        } else if(byList.size() == 2) {
            return new ByAll(byList.get(0), byList.get(1));
        }
        else{
        	
        	ReportLogger.fatal("ByAll couldn't be generated");
        	return null;
        }

    }
    
    protected ByAll getLocator(String sname, String parm)
    {
		return testSpecificMap.get(sname).get(parm);
    }
    
    //this is where application map related things end
    
    
    
    //this is where data map things start
    private static LinkedHashMap < String, HashMap<String,ArrayList<String>> > testSpecificData = new LinkedHashMap < String, HashMap<String,ArrayList<String>>> ();
    public void ReadAllData() throws IOException {
    	
        FileInputStream fileStream = new FileInputStream(System.getProperty("user.dir") + "\\TestResources\\DataMap\\Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileStream);

        int noOfSheets = workbook.getNumberOfSheets();
        
        
        for (int sheetIndex=0;sheetIndex<noOfSheets;sheetIndex++)
        {
        
        	XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        	LinkedHashMap<String,ArrayList<String>> sheetData = new LinkedHashMap<String, ArrayList<String>>();

	        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
	        int colCount = sheet.getRow(0).getLastCellNum();
	
	        DataFormatter dataFormatter = new DataFormatter();
	
	        for (int rowiterater = 0; rowiterater <= rowCount; rowiterater++){
	            ArrayList<String> list = new ArrayList<String>();
	            for (int coliterater = 1; coliterater < colCount; coliterater++)
	            	list.add(dataFormatter.formatCellValue(sheet.getRow(rowiterater).getCell(coliterater)));            	
	            sheetData.put(dataFormatter.formatCellValue(sheet.getRow(rowiterater).getCell(0)), list);  
	        }
       
	        testSpecificData.put(sheet.getSheetName(),sheetData);
        }
        workbook.close();
       
    }
    
    protected String dSName = null;
    protected String id = null;
    
    public String getdata(String col) {
        String dataValue = "";
               
        ArrayList<String> list = testSpecificData.get(dSName).get(testSpecificData.get(dSName).keySet().toArray()[0]);
        int dataColumn = 0;
        boolean flag = false;
        for (; dataColumn < list.size(); dataColumn++) {
            if (col.equalsIgnoreCase(list.get(dataColumn))) {
                flag = true;
                break;
            }
        }
        
        if (flag) {
            list = testSpecificData.get(dSName).get(id);
            System.out.println(list.get(dataColumn)+"printing list");
            if (list.size()>0){
            	dataValue = list.get(dataColumn);
            }
            else
            	{
            		Assert.fail(id +" did not match any id in "+ dSName);
            	}

        } else {
        	Assert.fail("There is nothing like " + col + " in " + dSName+ " sheet in file "+ "Data.xlsx in reourxes folder");
        }

        return dataValue;
    }
    
    //this is where datamap related things end
    
    
    
    
    ////report related things start here
    
    public static ExtentReports extent;
    
 
    
    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
        
        extent = new ExtentReports();
        extent.setSystemInfo("OS", "Windows 10 x64");
        extent.setSystemInfo("Host Name", "IVS_ETA");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("User Name", "FSM");

        extent.attachReporter(htmlReporter);
        
        return extent;
    }
    
    ////Report related things end here
}
