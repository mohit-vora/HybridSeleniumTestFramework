package exercise;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class GrantLoanWithPOIHashMap {
	public static void main(String args[]) throws InterruptedException, IOException {
		//store all locators in treemap
		long startTime = System.nanoTime();

		readAllLocators();
		
		//set webdriver and open url etc
		WebDriver driver = null;
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mohit_vora\\Desktop\\Selenium\\Workspace\\Framework\\TestResources\\driverExecutable\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.67.89.40:8080/do/login");
		

		//login with admin
		driver.findElement(getLocator("TXB_LOGINNAME")).sendKeys("admin");
		driver.findElement(getLocator("TXB_PASSWORD")).sendKeys("1234");
		driver.findElement(getLocator("BTN_LOGIN")).click();
		
		
		//enter user to which loan has to be granted
		driver.findElement(getLocator("TXB_MEMBER_LOGIN")).sendKeys("TestUser01");
		
		//wait for list to populate
		Thread.sleep(2000);
		
		//get all the list elements
		List<WebElement> suggestions = driver.findElements(getLocator("SUG_MEMBERLIST"));
		
		//iterate and click on the correct one
		for (WebElement ele: suggestions){
			if (ele.getText().contains("TestUser01")){
				ele.click();
				break;
			}
		}
		
		//click on grant loan submit button
		driver.findElement(getLocator("BTN_SUBMIT_GRANT_LOAN")).click();
		
		//populate appropriate loan data in the page
		new Select(driver.findElement(getLocator("LST_LOAN_GROUP"))).selectByVisibleText("Personal");
		new Select(driver.findElement(getLocator("LST_TRANSFER_TYPE"))).selectByVisibleText("Loan to Savings");
		driver.findElement(getLocator("TXB_AMOUNT")).sendKeys("1000");
		
		//get 3 days from today's date
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 3);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String futureDate = dateFormat.format(c.getTime());
		
		//populate rest of the data
		driver.findElement(getLocator("DAT_FIRST_REPAYMENT")).sendKeys(futureDate);
		driver.findElement(getLocator("TXB_NO_REPAYMENTS")).sendKeys("1");
		driver.findElement(getLocator("TXB_description")).sendKeys("this is automated loan granted");
	
		//click on submit
		driver.findElement(getLocator("BTN_SUBMIT_LOANS_PAGE")).click();
		driver.findElement(getLocator("BTN_CONFIRM_LOAN_DATA")).click();
		
		//handle loan popup
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
		
		
		//click on logout
		driver.findElement(getLocator("LNK_LOGOUT")).click();
		driver.switchTo().alert().accept();
		
		long stopTime = System.nanoTime();
		System.out.println((stopTime - startTime)/9);
		
		
		//close the browser
		driver.quit();

		
	}
	
	

	
	static TreeMap<String,By> locatorMap = new TreeMap<String, By>();
	
	public static void readAllLocators () throws IOException{
		XSSFWorkbook locatorWorkbook = new XSSFWorkbook("C:\\Users\\mohit_vora\\Desktop\\AMap_Exercise.xlsx");
		XSSFSheet locatorSheet = locatorWorkbook.getSheetAt(0);

		int noOfLocators = locatorSheet.getLastRowNum()-locatorSheet.getFirstRowNum();

		By byObject = null;
		
		for (int currentRrow=1;currentRrow <= noOfLocators;currentRrow++){
			String idInCurrentRow = locatorSheet.getRow(currentRrow).getCell(0).getStringCellValue();
			
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
					System.out.println("Error in locator sheet");
				
				}
				
				locatorMap.put(idInCurrentRow, byObject);
			}
		
		locatorWorkbook.close();
	}
	
	public static By getLocator(String columnName){
		return locatorMap.get(columnName);
	}
	
}