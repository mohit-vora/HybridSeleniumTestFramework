package DataMap;
import java.awt.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByAll;


public class Data_Map_Tryout2 {

//	 public static void main(String[] args) throws IOException
//	 {
//		 
//		 init();
//		 String data = getData("MEM003","Login Name");
//		 System.out.println(data);
//		 
//		 data = getData("ACC002");
//		 System.out.println(data);
//
//
//	 }
	 
	 public Data_Map_Tryout2() throws IOException
	 {
		 init();
	 }
	 
	 public String getData(String id, String col)
	 {
		 String value = "";
		 String whichMap = id.substring(0,3);
		 
		 switch (whichMap.toLowerCase())
		 {
		 	case "mem":
		 		List li = MEM.get("DATA_SET_ID");
		 		int i=0;
		 		boolean flag = false;
		 		for (;i<li.getItemCount();i++){
	
		 			if (col.equalsIgnoreCase(li.getItem(i)))
		 				{
		 					flag = true;
		 					break;
		 				}
		 		}
		 		if (flag){
		 		li = MEM.get(id.substring(3));
		 		value = li.getItem(i);
		 		}
		 		else
		 		{
		 			System.out.println("There is nothing like "+col);
		 		}
		 		break;		 	
		 }

		 return value;
	 }
	 
	 String getData(String id)
	 {
		 String value = "";
		 value = ACC.get(id.substring(3));
		 return value;
	 }
	 
	 
	 private void init() throws IOException{
		 
		 FileInputStream fileStream = new FileInputStream(System.getProperty("user.dir")+"\\Resources\\Data.xlsx");
		 XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
		 int NoofSheets = workbook.getNumberOfSheets();

			for (int j=0; j < NoofSheets; j++){
				String SheetName = workbook.getSheetName(j);
				HashMap<String, ByAll> tmap = new HashMap<String, ByAll>();
				List<By> locators = null;
				XSSFCell mLocator, mValue, aLocator, aValue, elementName;
				XSSFSheet sheet = workbook.getSheetAt(j);
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
		 
	 }
}
