package DataMap;

import java.awt.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.pagefactory.ByAll;

public class ReadData {

    private String DSName = null;
    private String initSheet = null;
    private Map < String, HashMap<String,List> > data = new HashMap < String, HashMap<String,List> > ();
    private String id = null;

    public ReadData(String name, String id) throws IOException {
        DSName = name;
        this.id = id;
        
    }

    public String getData(String col) {
        String value = "";

        List li = data.get("DATA_SET_ID");
        int i = 0;
        boolean flag = false;
        for (; i < li.getItemCount(); i++) {

            if (col.equalsIgnoreCase(li.getItem(i))) {
                flag = true;
                break;
            }
        }
        if (flag) {
            li = data.get(id);
            try{
            value = li.getItem(i);
            }
            catch (Exception e)
            {
            	System.out.println(id +" did not match any id in "+ DSName);
            }
        } else {
            System.out.println("There is nothing like " + col + " in " + initSheet+ "sheet in file "+ "Data.xlsx in reourxes folder");
        }

        return value;
    }

    private void init() throws IOException {

        FileInputStream fileStream = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileStream);

        int no_of_sheets = workbook.getNumberOfSheets();
        
        for (int sheetIndex=0;sheetIndex<no_of_sheets;sheetIndex++)
        {
        
        	XSSFSheet sheet = workbook.getSheetAt(sheetIndex);


	        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
	        int colCount = sheet.getRow(0).getLastCellNum();
	
	        DataFormatter df = new DataFormatter();
	
	        for (int i = 0; i <= rowCount; i++) {
	            List li = new List();
	            for (int j = 1; j < colCount; j++)
	                li.add(df.formatCellValue(sheet.getRow(i).getCell(j))); 
	                data.put(df.formatCellValue(sheet.getRow(i).getCell(0)), li);
	           
	        }
        }
        workbook.close();

    }

}