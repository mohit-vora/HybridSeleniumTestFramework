package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {
	
	public static WebDriver driver = null;
    public static ExtentTest test;

	//reading things go here
	
	public void readEverything() throws IOException
    {
    	ReadAllLocators();
    	ReadAllData();
    }
    
    public void openBrowserChrome() {
        System.setProperty("webdriver.chrome.driver", getPropVal("chromeDriver"));
        driver = new ChromeDriver();
        driver.get(getPropVal("url"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    
    public void runTestNG() {

    	try
    	{
    		FileInputStream mapsheet = new FileInputStream(System.getProperty("user.dir") + "\\TestResources\\TestCaseSheet.xlsx");
            XSSFWorkbook WorkBook = new XSSFWorkbook(mapsheet);

            XSSFSheet sheet = WorkBook.getSheet("TestCases");

            int i;
            int rownum = sheet.getLastRowNum() - sheet.getFirstRowNum();
            for (i = 1; i <= rownum; i++) {
                String runStatus = sheet.getRow(i).getCell(3).getStringCellValue();
                if (runStatus.equalsIgnoreCase("Yes")) {
                	String testName = sheet.getRow(i).getCell(1).getStringCellValue();
                	String dataSetIDs = sheet.getRow(i).getCell(2).getStringCellValue();
                    BaseClass ci = new BaseClass(); 
                    ci.setYesTestDetails(testName, dataSetIDs);
                }
            }

            WorkBook.close();
            mapsheet.close();
    	}
    	catch (Exception e)
    	{
    		StringWriter sw = new StringWriter();
    		PrintWriter pw = new PrintWriter(sw);
    		e.printStackTrace(pw);
    		String StackTrace = sw.toString(); // stack trace as a string
    		ReportLogger.fatal("problem in runTestNG method in BaseClass"+StackTrace);

    		extent.flush();
    	}
        

    }
   
	
	//reading things ends here
	
    
   


    public void PopUpAccept() {
        try {
        	String PopUpMessage = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            ReportLogger.info("Accepting Popup: " + PopUpMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // System.out.println(PopUpMessage);
        // return PopUpMessage;
    }
	
	////this is where Driver Splitting things go
	
	public static LinkedHashMap<String, Object[][]> onlyYesTestCases = new LinkedHashMap<String, Object[][]>();
	
	public void setYesTestDetails(String yesTestName, String dataSetIDs)
	{
		int row=0;
		int col=0;
		
		row=dataSetIDs.split(";").length;
		col=dataSetIDs.split(";")[0].split(",").length;
		
		Object[][] s1 = new String [row][col];
		
		for (int i=0;i<row;i++)
		{			
			for (int j=0;j<col;j++)
			{
				s1[i][j] = dataSetIDs.split(";")[i].split(",")[j];
			}
		}
		onlyYesTestCases.put(yesTestName,s1);
	}
	
	public Object[][] getYesTestDetails(String testName)
	{
		Object[][] dataSetIDs = new Object[][]{};
		
		if (onlyYesTestCases.containsKey(testName))
			dataSetIDs = onlyYesTestCases.get(testName);
		
		return dataSetIDs;
	}

	
	//this is where driver splitting things end
	
	
	//properties file related code
	
	
	public static String getPropVal(String parm) {
//      File file = new File(System.getProperty("user.dir") + "\\resources\\path.properties");

  	File file = new File(System.getProperty("user.dir") + "\\TestResources\\path.properties");
  	
      FileInputStream fileInput = null;
      try {
          fileInput = new FileInputStream(file);
      } catch (FileNotFoundException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
      }

      Properties prop = new Properties();

      try {
          prop.load(fileInput);
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }

      return prop.getProperty(parm);
  }
	
	
	
	
	//properties file related code ends here
	
	//this is where application map reading starts
	private static HashMap <String,HashMap<String,ByAll>> LMap1 = new HashMap<String,HashMap<String,ByAll>>();
	XSSFCell elementName;
	
	

	public void ReadAllLocators(){
		try
		{
			HashMap < String, ByAll > LMap = new HashMap < String, ByAll > ();
	        FileInputStream mapsheet = new FileInputStream(System.getProperty("user.dir") + "\\TestResources\\ApplicationMap\\AMap.xlsx");
	        XSSFWorkbook WorkBook = new XSSFWorkbook(mapsheet);

	        List < By > locators = null;
	        
	        int no_of_sheets = WorkBook.getNumberOfSheets();
	        
	        for (int sheetIndex=0;sheetIndex<no_of_sheets;sheetIndex++)
	        {
	        
	        	XSSFSheet sheet = WorkBook.getSheetAt(sheetIndex);
	        	XSSFCell mLocator, mValue, aLocator, aValue;
	            int i;
	            int rownum = sheet.getLastRowNum() - sheet.getFirstRowNum();
	            //		System.out.println(sheetName+rownum);
	            for (i = 1; i <= rownum; i++) {
	                locators = new ArrayList < By > ();
	                elementName = sheet.getRow(i).getCell(0);
	                mLocator = sheet.getRow(i).getCell(1);
	                mValue = sheet.getRow(i).getCell(2);
	                aLocator = sheet.getRow(i).getCell(3);
	                aValue = sheet.getRow(i).getCell(4);
	                if (mLocator != null && mValue != null) {
	                    locators.add(generator(mLocator.getStringCellValue().toLowerCase(), mValue.getStringCellValue()));
	                }

	                if (aLocator != null && aValue != null) {
	                    locators.add(generator(aLocator.getStringCellValue().toLowerCase(), aValue.getStringCellValue()));

	                }
	                LMap.put(elementName.getStringCellValue(), generatorAll(locators));
	            }
	            LMap1.put(sheet.getSheetName(), LMap);
	        }
	        
	        

	        WorkBook.close();
		}
		
		catch (Exception e)
		{
			ReportLogger.fatal("problem in ReadAllLocators"+e);
		}
		
    }	
	
	private By generator(String locator, String value) {
        By obj1 = null;
        if (!locator.equals("")) {
            switch (locator.toLowerCase()) {
                case "id":
                    obj1 = By.id(value);
                    break;
                case "xpath":
                    obj1 = By.xpath(value);
                    break;
                case "css":
                    obj1 = By.cssSelector(value);
                    break;

                default:
                    System.out.println("Something is wrong in application map of this object: " + elementName);
                    break;

            }
        }

        return obj1;
    }

    private ByAll generatorAll(List < By > list) {
        if (list.size() == 1) {
            return new ByAll(list.get(0));
        } else {
            return new ByAll(list.get(0), list.get(1));
        }

    }
    
    protected ByAll getLocator(String sname, String parm)
    {
		return LMap1.get(sname).get(parm);
    	
    }
    
    //this is where application map related things end
    
    
    
    //this is where data map things start
    private static LinkedHashMap < String, HashMap<String,List<String>> > data = new LinkedHashMap < String, HashMap<String,List<String>>> ();
    public void ReadAllData() throws IOException {

    	
    	
        FileInputStream fileStream = new FileInputStream(System.getProperty("user.dir") + "\\TestResources\\DataMap\\Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileStream);

        int no_of_sheets = workbook.getNumberOfSheets();
        
        
        for (int sheetIndex=0;sheetIndex<no_of_sheets;sheetIndex++)
        {
        
        	XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        	LinkedHashMap<String,List<String>> s_data = new LinkedHashMap<String, List<String>>();

	        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
	        int colCount = sheet.getRow(0).getLastCellNum();
	
	        DataFormatter df = new DataFormatter();
	
	        for (int i = 0; i <= rowCount; i++){
	            List<String> li = new ArrayList<String>();
	            for (int j = 1; j < colCount; j++)
	            	li.add(df.formatCellValue(sheet.getRow(i).getCell(j)));            	
	           s_data.put(df.formatCellValue(sheet.getRow(i).getCell(0)), li);  
	        }
       
	        data.put(sheet.getSheetName(),s_data);
        }
        workbook.close();
       
    }
    
    protected String DSName = null;
    protected String id = null;
    
    public String getdata(String col) {
        String value = "";
               
        List<String> li = data.get(DSName).get(data.get(DSName).keySet().toArray()[0]);
        int i = 0;
        boolean flag = false;
        for (; i < li.size(); i++) {
            if (col.equalsIgnoreCase(li.get(i))) {
                flag = true;
                break;
            }
        }
        
        if (flag) {
            li = data.get(DSName).get(id);
            try{
            value = li.get(i);
            }
            catch (Exception e)
            {
            	System.out.println(id +" did not match any id in "+ DSName);
            }
        } else {
            System.out.println("There is nothing like " + col + " in " + DSName+ " sheet in file "+ "Data.xlsx in reourxes folder");
        }

        return value;
    }
    
    //this is where dapamap related things end
    
    
    
    
    ////report related things start here
    
    public static ExtentReports extent;
    
    public static ExtentReports getInstance() {
    	if (extent == null)
    		createInstance(System.getProperty("user.dir") + "/test-output/AutomationReport.html");
    	
        return extent;
    }
    
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
