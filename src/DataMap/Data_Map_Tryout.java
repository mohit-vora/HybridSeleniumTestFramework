package DataMap;
import java.awt.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Data_Map_Tryout {
	 static Map<String, List> MEM = new HashMap<String, List>();
	 static Map<String, String> ACC = new HashMap<String, String>();
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
	 
	 public Data_Map_Tryout() throws IOException
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
		 XSSFSheet memsheet = workbook.getSheet("MemberDetails");
		 int rowCount = memsheet.getLastRowNum() - memsheet.getFirstRowNum();
		 int colCount = memsheet.getRow(0).getLastCellNum();
		 
		 DataFormatter df = new DataFormatter();
		
		 	for (int i = 0; i <= rowCount; i++) {
				List li = new List();
				for (int j=1;j<colCount;j++)
					li.add(df.formatCellValue(memsheet.getRow(i).getCell(j)));

				if (i==0)
					MEM.put(df.formatCellValue(memsheet.getRow(i).getCell(0)),li);
				else
					MEM.put(df.formatCellValue(memsheet.getRow(i).getCell(0)).substring(3),li);

			}
		 
		 memsheet = workbook.getSheet("UserAccountTypes");
		 rowCount = memsheet.getLastRowNum() - memsheet.getFirstRowNum();
					 
			for (int i = 0; i <= rowCount; i++) {
				if (i==0)
					ACC.put(df.formatCellValue(memsheet.getRow(i).getCell(0)),df.formatCellValue(memsheet.getRow(i).getCell(1)));
				else
					ACC.put(df.formatCellValue(memsheet.getRow(i).getCell(0)).substring(3),df.formatCellValue(memsheet.getRow(i).getCell(1)));
			}	
			
		 workbook.close();
//		 List li = new List();
//		 li.add("Full name");
//		 li.add("Login Name");
//		 li.add("Password");
//		 MEM.put("COLName",li);
//		 li = new List();
//		 li.add("Nick Parsons");
//		 li.add("Nick01");
//		 li.add("infy@123");
//		 MEM.put("001",li);
//		 li = new List();
//		 li.add("Raman Jha");
//		 li.add("Ram01");
//		 li.add("infy@123");
//		 MEM.put("002",li);
//		 li = new List();
//		 li.add("John Cage");
//		 li.add("Cage01");
//		 li.add("infy@123");
//		 MEM.put("003",li); 
//		 
//		 
//
//		 ACC.put("COLName","Account type");
//		 ACC.put("001","Savings Account");
//		 ACC.put("002","Fixed Deposit Account");
//		 ACC.put("003","Recurring Deposit Account"); 
//		 ACC.put("004","Current Account"); 
		 
	 }
}
