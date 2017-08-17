package Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import tryOutsGoHere.InheritThis;

public class OneClickRun extends InheritThis {

    public static void main(String[] args) throws IOException {

        runTestNG();
    }

    public static void runTestNG() throws IOException {





        FileInputStream mapsheet = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\TestCaseSheet.xlsx");
        XSSFWorkbook WorkBook = new XSSFWorkbook(mapsheet);

        XSSFSheet sheet = WorkBook.getSheet("TestCases");

        int i;
        int rownum = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (i = 1; i <= rownum; i++) {
            String runStatus = sheet.getRow(i).getCell(0).getStringCellValue();
            if (runStatus.equalsIgnoreCase("Yes")) {
                XmlSuite suite = new XmlSuite();
                suite.setName("MyTestSuite");

                List < XmlClass > classes = new ArrayList < XmlClass > ();
                String tcName = sheet.getRow(i).getCell(2).getStringCellValue();
                classes.add(new XmlClass("TestCases." + tcName));

                InheritThis in = new InheritThis(); 
                in .setArgs(sheet.getRow(i).getCell(3).getStringCellValue());

                XmlTest test = new XmlTest(suite);
                test.setName("demotest");
                test.setXmlClasses(classes);

                List < XmlSuite > suites = new ArrayList < XmlSuite > ();
                suites.add(suite);

                TestNG testNG = new TestNG();
                testNG.setXmlSuites(suites);
                testNG.run();

            }



        }


        WorkBook.close();









        //		TestNG runner = new TestNG();
        //		List<String> suitefiles = new ArrayList<String>();
        //		suitefiles.add(System.getProperty("user.dir") + "\\testng.xml");
        //		runner.setTestSuites(suitefiles);
        //		runner.run();

    }
}