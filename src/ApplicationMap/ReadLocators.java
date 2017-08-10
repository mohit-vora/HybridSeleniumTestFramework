package ApplicationMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByAll;

public class ReadLocators {

	HashMap<String, ByAll> smap = null;
	String sheetName = null;
	XSSFCell mLocator, mValue, aLocator, aValue, elementName;
	public ReadLocators(String sname) {
		sheetName = sname;
		try {
			init();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void init() throws IOException {
		smap = new HashMap<String, ByAll>();
		FileInputStream mapsheet = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\AMap.xlsx");
		XSSFWorkbook WorkBook = new XSSFWorkbook(mapsheet);

		List<By> locators = null;
		
		XSSFSheet sheet = WorkBook.getSheet(sheetName);
		
		int i;
		int rownum = sheet.getLastRowNum() - sheet.getFirstRowNum();
		System.out.println(sheetName+rownum);
		for (i = 1; i <= rownum; i++) {
			locators = new ArrayList<By>();
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
			smap.put(elementName.getStringCellValue(), generatorAll(locators));
		}

		WorkBook.close();
	}

	public By generator(String locator, String value) {
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
				obj1=By.cssSelector(value);
				break;

			default:
				System.out.println("Something is wrong in application map of this object: "+ elementName);
				break;

			}
		}

		return obj1;
	}

	public ByAll generatorAll(List<By> list) {
		if (list.size() == 1) {
			return new ByAll(list.get(0));
		} else {
			return new ByAll(list.get(0), list.get(1));
		}

	}

	public ByAll getLocator(String parm) {
		
		System.out.println(smap.get("TXB_Name"));
		System.out.println(smap.get(parm));
		
		return smap.get(parm);

	}

}
