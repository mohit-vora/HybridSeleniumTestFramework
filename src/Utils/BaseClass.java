package Utils;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
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
import org.openqa.selenium.Alert;
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
    protected static int testIterationNumber = 0;
    protected static boolean isLoggedIn = false;

	//reading things go here
    /*This Method does the driver setup for the execution*/
    public void openBrowserChrome() {
    	
    	if (preExecutionCheck){
    		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+getPropVal("chromeDriver"));
            driver = new ChromeDriver();
            driver.get(getPropVal("url"));
            ReportLogger.info("Browser Instance opened");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            ReportLogger.info("Browser Window Maximized");
    	}
    	
        
    }
    /*reads the TestCase sheet to fetch the testcases marked with 'yes'*/
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

	/*This method handles the popup that occurs during the scenario execution*/
	public static void validateAndAcceptPopup(String dsid) {
		ReadData data = new ReadData("PopupMessages", dsid);
		
		Alert alert = driver.switchTo().alert();
		
    	String popUpMessage = alert.getText();
    	
    	if(popUpMessage.equals(data.getData("MESSAGE_TEXT"))){
    		alert.accept();
    		//for dismissing the popup
    		alert.dismiss();
    		ReportLogger.info("Popup accepted :"+popUpMessage);
    	}
    	else{
    		alert.accept();
    		ReportLogger.info("Enrollment was not successfull refer to the Stack trace below");
    		Assert.assertEquals(popUpMessage, data.getData("MESSAGE_TEXT"));    		 
    	}     
}
	/*Checks whether the Object Array passed by dataprovider and no of parameter needed by @test method are equal*/
	protected boolean checkDataProviderSanity(Object[][] obj, Method method){
		
		boolean flag = true;
		
		for (int i=0;i<obj.length;i++){
			if (obj[i].length!=method.getParameterCount()){
				flag=false;
				test = extent.createTest(method.getName()+": "+getCurrentIterationTestData(method));
				ReportLogger.skip("skipped this Test");
				ReportLogger.skip("No of arguments taken by test case ["+method.getName()+"] are not matching test method parameters");
				break;		
			}
		}	
		return flag;
	}
	/*Fetches the data set used for each iteration*/
	protected String getCurrentIterationTestData(Method method){
		testIterationNumber++;
		Object[] argumentObjectArray = getYesTestDetails(method.getName())[testIterationNumber-1];
		String testArguments = "";
		for (int objectIndex=0;objectIndex<argumentObjectArray.length;objectIndex++){
			testArguments=testArguments+argumentObjectArray[objectIndex]+",";
		}
		return testArguments.substring(0, testArguments.length()-1);
	}
	
	
	////this is where Driver Splitting things go
	
	public static LinkedHashMap<String, Object[][]> onlyYesTestCases = new LinkedHashMap<String, Object[][]>();
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
	private  void setYesTestDatasets(String yesTestName, String dataSetIds)
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
		System.out.println("read ids");
		onlyYesTestCases.put(yesTestName,s1);
	}
	/*Returns the test data as object array*/
	public Object[][] getYesTestDetails(String testName)
	{
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

	
	//this is where driver splitting things end
	//properties file related code
	/*read the path of Application map,datamap,Driver setup file*/
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
	
	/*ReadAllLocators method reads the application map where the element locators are saved and stores it in a HashMap datastructure*/
	
	private static HashMap <String,HashMap<String,ByAll>> testSpecificMap = new HashMap<String,HashMap<String,ByAll>>();
	XSSFCell elementName;
	XSSFSheet sheet;
	
	
	public void ReadAllLocators(){
		
		if (preExecutionCheck){
			try
			{
		        FileInputStream mapSheet = new FileInputStream(System.getProperty("user.dir") + "\\TestResources\\ApplicationMap\\AMap.xlsx");
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
		                	ReportLogger.warn("Main Locator cell is empty");
		                	Assert.fail("Main Locator cell is empty");
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
		        workBook = null;
		        mapSheet = null;
		        
			}
			
			catch (Exception exception)
			{
				preExecutionCheck=false;
				ReportLogger.fatal("problem in ReadAllLocators "+exception);
			}
		}
		
		
		
    }	
	 /* With the value retrieved from the Application Map By instances are created based on its Locator*/
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
/*with the By instance created from generator method  ByAll insatnce is created*/
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
    /*This method fetches the corresponding locator value by taking its corresponding sheet name and element name as parameter*/
    protected ByAll getLocator(String sheetName, String elementName) {
		return testSpecificMap.get(sheetName.toLowerCase()).get(elementName.toLowerCase());
    }
    
    //this is where application map related things end
    
    
    
    //this is where data map things start
    protected static HashMap < String, LinkedHashMap<String,ArrayList<String>> > testSpecificData = new HashMap < String, LinkedHashMap<String,ArrayList<String>>> ();
    public void ReadAllData(){
    	try{
    		if (preExecutionCheck){
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
    
    protected String dSName = null;
    protected String id = null;
    /*returns the corresponding value by taking column name as parameter*/
    public String getColumnData(String col) {
        String dataValue = "";
        String lowerCasedSName = dSName.toLowerCase();
        String lowerCaseId = id.toLowerCase(); 
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
            ArrayList<String> dataList = testSpecificData.get(dSName.toLowerCase()).get(lowerCaseId);
        	dataValue = dataList.get(dataColumn);

        } else {
        	Assert.fail("There is nothing like " + col + " in " + dSName+ " sheet in file "+ "Data.xlsx in reourxes folder");

        }
        return dataValue;
    }
    
    //this is where datamap related things end
    
    
    /**
     * Returns an Image object that can then be painted on the screen. 
     * The url argument must specify an absolute {@link URL}. The name
     * argument is a specifier that is relative to the url argument. 
     * <p>
     * This method always returns immediately, whether or not the 
     * image exists. When this applet attempts to draw the image on
     * the screen, the data will be loaded. The graphics primitives 
     * that draw the image will incrementally paint on the screen. 
     *
     * @param  url  an absolute URL giving the base location of the image
     * @param  name the location of the image, relative to the url argument
     * @return      the image at the specified URL
     * @see         Image
     */
     public Image getImage(URL url, String name) {
            try {
                return getImage(new URL(url, name),"");
            } catch (MalformedURLException e) {
                return null;
            }
     }
    
    ////report related things start here
/*This method initializes the basic configuration needed for the report generation*/
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
        extent.setSystemInfo("OS", System.getProperty("os.name")+" "+System.getProperty("os.arch"));
        try {
			extent.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			ReportLogger.fatal("Host name could not be found: "+e);
		}
        extent.setSystemInfo("Environment", System.getProperty("java.runtime.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));

        extent.attachReporter(htmlReporter);
        
        return extent;
    }
    
    ////Report related things end here
}
