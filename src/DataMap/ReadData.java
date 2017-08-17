package DataMap;

import java.awt.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {

    private String DSName = null;
    private String initSheet = null;
    private Map < String, List > data = new HashMap < String, List > ();
    private String id = null;

    public ReadData(String name, String id) throws IOException {
        DSName = name;
        this.id = id;
        init();
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
            li = data.get(id.substring(3));
            value = li.getItem(i);
        } else {
            System.out.println("There is nothing like " + col + " in " + initSheet);
        }

        return value;
    }

    private void init() throws IOException {

        FileInputStream fileStream = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileStream);

        XSSFSheet sheet = workbook.getSheet(DSName);
        initSheet = DSName;

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        DataFormatter df = new DataFormatter();

        for (int i = 0; i <= rowCount; i++) {
            List li = new List();
            for (int j = 1; j < colCount; j++)
                li.add(df.formatCellValue(sheet.getRow(i).getCell(j)));

            if (i == 0)
                data.put(df.formatCellValue(sheet.getRow(i).getCell(0)), li);
            else
                data.put(df.formatCellValue(sheet.getRow(i).getCell(0)).substring(3), li);

        }
        workbook.close();

    }

}