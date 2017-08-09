package ApplicationMap;

import java.io.FileInputStream;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashMap;
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
		HashMap<String, Map<String, ByAll>> smap = new HashMap<String, Map<String, ByAll>>();
		FileInputStream mapsheet = new FileInputStream(System.getProperty("user.dir")+"\\Resources\\AMap.xlsx");
		XSSFWorkbook WorkBook = new XSSFWorkbook(mapsheet);
		int NoofSheets = WorkBook.getNumberOfSheets();

		for (int j=0; j < NoofSheets; j++){
			String SheetName = WorkBook.getSheetName(j);
			HashMap<String, ByAll> tmap = new HashMap<String, ByAll>();
			List<By> locators = null;
			XSSFCell mLocator, mValue, aLocator, aValue, elementName;
			XSSFSheet sheet = WorkBook.getSheetAt(j);
			int i;
			int rownum = sheet.getLastRowNum() - sheet.getFirstRowNum();
			for (i = 1; i < rownum+1; i++) {
				locators = new ArrayList<By>();
				elementName = sheet.getRow(i).getCell(0);
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
		
		return smap;
		
	}
	
	
}
