package exercise;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class GrantLoanWithPOI {
	public static void main(String args[]) throws InterruptedException, IOException {
		WebDriver driver = null;
		long startTime = System.nanoTime();
		openAMap("C:\\Users\\mohit_vora\\Desktop\\AMap_Exercise.xlsx");
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mohit_vora\\Desktop\\Selenium\\Workspace\\Framework\\TestResources\\driverExecutable\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.67.89.40:8080/do/login");
		


		driver.findElement(getLocator("TXB_LOGINNAME")).sendKeys("admin");
		driver.findElement(getLocator("TXB_PASSWORD")).sendKeys("1234");
		driver.findElement(getLocator("BTN_LOGIN")).click();
		
		driver.findElement(getLocator("TXB_MEMBER_LOGIN")).sendKeys("TestUser01");
		Thread.sleep(2000);
		List<WebElement> suggestions = driver.findElements(getLocator("SUG_MEMBERLIST"));

		for (WebElement ele: suggestions){
			if (ele.getText().contains("TestUser01")){
				ele.click();
				break;
			}
		}
		
		driver.findElement(getLocator("BTN_SUBMIT_GRANT_LOAN")).click();
		
		new Select(driver.findElement(getLocator("LST_LOAN_GROUP"))).selectByVisibleText("Personal");
		
		new Select(driver.findElement(getLocator("LST_TRANSFER_TYPE"))).selectByVisibleText("Loan to Savings");
		
		driver.findElement(getLocator("TXB_AMOUNT")).sendKeys("1000");
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 3);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String futureDate = dateFormat.format(c.getTime());
		
		driver.findElement(getLocator("DAT_FIRST_REPAYMENT")).sendKeys(futureDate);
		
		driver.findElement(getLocator("TXB_NO_REPAYMENTS")).sendKeys("1");
		
		driver.findElement(getLocator("TXB_description")).sendKeys("this is automated loan granted");
		
		driver.findElement(getLocator("BTN_SUBMIT_LOANS_PAGE")).click();

		driver.findElement(getLocator("BTN_CONFIRM_LOAN_DATA")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		
		if (alertText.equalsIgnoreCase("The loan was successfully granted")){
			System.out.println("Loan granted successfully.");
			alert.accept();
		}
		else{
			System.out.println("Loan not granted");
			alert.dismiss();
		}
		
		
		driver.findElement(getLocator("LNK_LOGOUT")).click();
		driver.switchTo().alert().accept();
		
		long stopTime = System.nanoTime();
		System.out.println(stopTime - startTime);
		
		Thread.sleep(3000);
		
		driver.quit();
		closeAMap();
		
	}
	
	
	static XSSFWorkbook locatorWorkbook;
	static XSSFSheet locatorSheet;
	
	public static void openAMap(String aMapLocation) throws IOException{
		locatorWorkbook = new XSSFWorkbook(aMapLocation);
		locatorSheet = locatorWorkbook.getSheetAt(0);
			
	}
	
	public static By getLocator(String elementID){
		int noOfLocators = locatorSheet.getLastRowNum()-locatorSheet.getFirstRowNum();
		By byObject = null;
		
		for (int currentRrow=1;currentRrow <=noOfLocators;currentRrow++){
			String idInCurrentRow = locatorSheet.getRow(currentRrow).getCell(0).getStringCellValue();
			if (idInCurrentRow.equalsIgnoreCase(elementID)){
				String locatorType = locatorSheet.getRow(currentRrow).getCell(1).getStringCellValue();
				String locatorValue = locatorSheet.getRow(currentRrow).getCell(2).getStringCellValue();
				switch(locatorType.toLowerCase()){
				case "xpath":
					byObject = By.xpath(locatorValue);
					break;
				case "id":
					byObject = By.id(locatorValue);
					break;
				case "name":
					byObject = By.name(locatorValue);
					break;
				case "css":
					byObject = By.cssSelector(locatorValue);
					break;
				default:
					System.out.println("Error in Applciation Map sheet");
				}
			}
		}
		return byObject;
		
	}
	
	
	public static void closeAMap() throws IOException{
		locatorWorkbook.close();
		locatorWorkbook = null;
		locatorSheet = null;
	}

}