package ApplicationMap;

import java.io.FileInputStream;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.poi.poifs.storage.SmallBlockTableWriter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.testng.annotations.Test;

public class ReadSheet {
    
	public String dem;
	
	public By generator(String locator, String value) {		
		By obj1=null;
		if(!locator.equals("")){
		switch (locator.toLowerCase()) {
		case "id":
			obj1 = By.id(value);
			break;
		case "xpath":
			obj1 = By.xpath(value);
			break;

		}
		}
		
		return obj1;
	}
	
	
	public ByAll generatorAll(List<By> list)
	{
		if(list.size()==1)
		{
			return new ByAll(list.get(0));
		}
		else
		{
			return new ByAll(list.get(0),list.get(1));
		}
		
		
	}
	

	
	public Map<String, Map<String, ByAll>> readsheet() throws Exception {	
		TreeMap<String, Map<String, ByAll>> smap = new TreeMap<String, Map<String, ByAll>>();
		FileInputStream mapsheet = new FileInputStream("C:\\Users\\kiruthika.k02\\Desktop\\Selenium_App_Map.xlsx");
		XSSFWorkbook WorkBook = new XSSFWorkbook(mapsheet);
		int NoofSheets = WorkBook.getNumberOfSheets();
		//System.out.println("NoofSheets::" + NoofSheets);	
		//System.out.println(WorkBook.getSheetName(5));
		for (int j=0; j < NoofSheets; j++){
			String SheetName = WorkBook.getSheetName(j);
			TreeMap<String, ByAll> tmap = new TreeMap<String, ByAll>();
			List<By> locators = null;
			XSSFCell mLocator, mValue, aLocator, aValue, elementName;
			XSSFSheet sheet = WorkBook.getSheetAt(j);
			int i;
			int rownum = sheet.getLastRowNum() - sheet.getFirstRowNum();
			//System.out.println(SheetName + "::" + rownum);
			for (i = 1; i < rownum+1; i++) {
				locators = new ArrayList<By>();
				elementName = sheet.getRow(i).getCell(0);
				//System.out.println(elementName);
				mLocator = sheet.getRow(i).getCell(1);
				mValue = sheet.getRow(i).getCell(2);
				aLocator = sheet.getRow(i).getCell(3);
				aValue = sheet.getRow(i).getCell(4);
				if(mLocator!=null && mValue!=null){
					locators.add(generator(mLocator.getStringCellValue().toLowerCase(), mValue.getStringCellValue()));
				}
				
				if(aLocator!=null && aValue!=null)
				{
					locators.add(generator(aLocator.getStringCellValue().toLowerCase(), aValue.getStringCellValue()));
					
				}
				tmap.put(elementName.getStringCellValue(), generatorAll(locators));
			}
			
		 smap.put(SheetName, tmap);
		}
		
/*		for(Map.Entry<String, Map<String, ByAll>> entry : smap.entrySet()){
			System.out.println("sheetname:-"+entry.getKey());
			for(Map.Entry<String, ByAll> entry1 : entry.getValue().entrySet()){
				System.out.println("ElementName:-"+entry1.getKey());
				System.out.println("Elementvalue:-"+entry1.getValue());
			}
		}*/
		//System.out.println(smap);
		
		return smap;
		
	}
	
	/*@Test
	public void test_script() throws Exception{
		Map<String, Map<String, ByAll>> input = new  TreeMap<String, Map<String, ByAll>>();
		ReadSheet rs1 = new ReadSheet(); 
		input = rs1.readsheet();
		
		System.out.println(input.get("Login").get("TXB_LOGINNAME"));
		
	System.out.println("testing");
	}*/
	
	
	/*public Map<String, ByAll> read() throws IOException {
		TreeMap<String, ByAll> tmap = new TreeMap<String, ByAll>();
		List<By> locators = null;
		XSSFCell mLocator, mValue, aLocator, aValue, elementName;
		FileInputStream fileStream = new FileInputStream(
				"\\\\vivsmys-17\\Flight Simulator\\Share\\Flight Simulator Model 2\\Application Map\\AMD_Login_Home_LeftNav_kiruthika.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
		XSSFSheet sheet = workbook.getSheetAt(1);
		try {
			int i;
			int rownum = sheet.getLastRowNum() - sheet.getFirstRowNum();

			for (i = 1; i < rownum+1; i++) {
				locators = new ArrayList<By>();
				elementName = sheet.getRow(i).getCell(2);
				mLocator = sheet.getRow(i).getCell(3);
				mValue = sheet.getRow(i).getCell(4);
				aLocator = sheet.getRow(i).getCell(5);
				aValue = sheet.getRow(i).getCell(6);
				locators.add(generator(mLocator.getStringCellValue(), mValue.getStringCellValue()));

				if(aLocator!=null && aValue!=null)
				{
					locators.add(generator(aLocator.getStringCellValue(), aValue.getStringCellValue()));
					
				}
				
				tmap.put(elementName.getStringCellValue(), generatorAll(locators));
			

			}
			
	
			
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tmap;

	}*/
}
