package exercise;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment {

	public static void main(String[] args) throws IOException, InterruptedException {
		//store all locators in treemap
		readAllLocators();
		
		//set webdriver and open url etc
		WebDriver driver = null;
		System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\mohit_vora\\Desktop\\Selenium\\Workspace\\Framework\\TestResources\\driverExecutable\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.67.89.40:8080/do/login");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
		//login with loan user and extract initial balance
		driver.findElement(getLocator("TXB_LOGINNAME")).sendKeys("TestUser01");
		driver.findElement(getLocator("TXB_PASSWORD")).sendKeys("infy@123");
		driver.findElement(getLocator("BTN_LOGIN")).click();
		
		//CLick on Account link
		driver.findElement(getLocator("LNK_CUSTOMER_ACCOUNT")).click();
		
		//click on account information link
		driver.findElement(getLocator("LNK_CUSTOMER_ACCOUNT_INFORMATION")).click();
				
		//get savings balance before granting loan into variable for customers having multiple accounts
		double customerBalanceBeforeGrantingLoan = 0.0;
		if (driver.findElement(getLocator("ELM_AccountPane")).getText().contains("My accounts")) {
			WebElement accnTypes = driver.findElement(getLocator("TBL_MyAccountsInner"));
			List<WebElement> rows = accnTypes.findElements(By.tagName("tr"));
			for (int listindex = 1; listindex < rows.size(); listindex++) {
				List<WebElement> cols = rows.get(listindex).findElements(By.tagName("td"));
				if (cols.get(0).getText().equalsIgnoreCase("Savings")) {
					String amount = cols.get(2).getText();
					String subStringAmount = amount.substring(0, amount.length() - 4).replace(",", "");
					Double balance = Double.parseDouble(subStringAmount);
					customerBalanceBeforeGrantingLoan = balance;
					break;
				}
			}
		}
		
		//get savings balance before granting loan into variable for customers having single account
		else if (driver.findElement(getLocator("ELM_SingleAccountPane")).getText().contains("Account balance")){
			String amount = driver.findElement(getLocator("ELM_SingleAccountBalance")).getText();
			String subStringAmount = amount.substring(0, amount.length() - 4).replace(",", "");
			Double balance = Double.parseDouble(subStringAmount);
			customerBalanceBeforeGrantingLoan = balance;
		}
						
		//logout for loan user
		driver.findElement(getLocator("LNK_CUSTOMER_LOGOUT")).click();
		driver.switchTo().alert().accept();		
				
		//login with admin
		driver.findElement(getLocator("TXB_LOGINNAME")).sendKeys("admin");
		driver.findElement(getLocator("TXB_PASSWORD")).sendKeys("1234");
		driver.findElement(getLocator("BTN_LOGIN")).click();
				
		//click on accounts link in left nav pane
		driver.findElement(getLocator("LNK_ADMIN_ACCOUNTS")).click();
				
		//click on Manage accounts sub link inside Accounts menu
		driver.findElement(getLocator("LNK_ADMIN_SYSTEM_ACCOUNTS")).click();
				
		//scan through all account types, find grant loan, get initial System loan account balance
		WebElement allAccountsTable = driver.findElement(getLocator("TBL_ADMIN_SYSTEM_ACCOUNTS")); 
		List<WebElement> rows = allAccountsTable.findElements(By.tagName("tr"));
				
		double initialSystemBalance = 0.0;
				
		for (int rowIndex=1; rowIndex<rows.size();rowIndex++){
			List<WebElement> cols = rows.get(rowIndex).findElements(By.tagName("td"));
			if (cols.get(0).getText().equalsIgnoreCase("Grant Loan")){
				String balance = cols.get(1).getText();
				initialSystemBalance = Double.parseDouble(balance.substring(0, balance.length()-4).replace(",", ""));
			}
					
		}
				
		//Click on Home link
		driver.findElement(getLocator("LNK_HOME")).click();
				
		//enter customer to which loan has to be granted
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
				
		Thread.sleep(2000);
		
		//click on accounts link in left nav pane
		driver.findElement(getLocator("LNK_ADMIN_ACCOUNTS")).click();
						
		//click on Manage accounts sub link inside Accounts menu
		driver.findElement(getLocator("LNK_ADMIN_SYSTEM_ACCOUNTS")).click();
		
		//scan through all account types, find grant loan, get initial System loan account balance
		allAccountsTable = driver.findElement(getLocator("TBL_ADMIN_SYSTEM_ACCOUNTS")); 
		rows = allAccountsTable.findElements(By.tagName("tr"));
						
		double systemBalanceAfterLoanGranted = 0.0;
						
		for (int rowIndex=1; rowIndex<rows.size();rowIndex++){
			List<WebElement> cols = rows.get(rowIndex).findElements(By.tagName("td"));
			if (cols.get(0).getText().equalsIgnoreCase("Grant Loan")){
				String balance = cols.get(1).getText();
				systemBalanceAfterLoanGranted = Double.parseDouble(balance.substring(0, balance.length()-4).replace(",", ""));
			}				
		}
				
		//click on logout
		driver.findElement(getLocator("LNK_ADMIN_LOGOUT")).click();
		driver.switchTo().alert().accept();
		
		//login with loan user and extract savings balance after loan is granted
		driver.findElement(getLocator("TXB_LOGINNAME")).sendKeys("TestUser01");
		driver.findElement(getLocator("TXB_PASSWORD")).sendKeys("infy@123");
		driver.findElement(getLocator("BTN_LOGIN")).click();
		
		//CLick on Account link
		driver.findElement(getLocator("LNK_CUSTOMER_ACCOUNT")).click();
				
		//click on account information link
		driver.findElement(getLocator("LNK_CUSTOMER_ACCOUNT_INFORMATION")).click();
						
		//get savings balance after granting loan into variable for customers having multiple accounts
		double customerBalanceAfterGrantingLoan = 0.0;
		if (driver.findElement(getLocator("ELM_AccountPane")).getText().contains("My accounts")) {
			WebElement accnTypes = driver.findElement(getLocator("TBL_MyAccountsInner"));
			rows = accnTypes.findElements(By.tagName("tr"));
			for (int listindex = 1; listindex < rows.size(); listindex++) {
				List<WebElement> cols = rows.get(listindex).findElements(By.tagName("td"));
				if (cols.get(0).getText().equalsIgnoreCase("Savings")) {
					String amount = cols.get(2).getText();
					String subStringAmount = amount.substring(0, amount.length() - 4).replace(",", "");
					Double balance = Double.parseDouble(subStringAmount);
					customerBalanceAfterGrantingLoan = balance;
					break;
				}
			}
		}
		//get savings balance after granting loan into variable for customers having single account
		else if (driver.findElement(getLocator("ELM_SingleAccountPane")).getText().contains("Account balance")){
			String amount = driver.findElement(getLocator("ELM_SingleAccountBalance")).getText();
			String subStringAmount = amount.substring(0, amount.length() - 4).replace(",", "");
			Double balance = Double.parseDouble(subStringAmount);
			customerBalanceAfterGrantingLoan = balance;
		}
						
		//logout for loan user
		driver.findElement(getLocator("LNK_CUSTOMER_LOGOUT")).click();
		driver.switchTo().alert().accept();
		
		double changeInSystemAccount = initialSystemBalance-systemBalanceAfterLoanGranted;
		double changeInCustomerBalance = customerBalanceAfterGrantingLoan-customerBalanceBeforeGrantingLoan;
		if (changeInSystemAccount==changeInCustomerBalance){
			System.out.println("GRANT LOAN: passed");
		}
		else{
			System.out.println("GRANT LOAN: failed");
		}
				
		Thread.sleep(3000);
				
		//close the browser
		driver.quit();	
	}
			
			

			
	static TreeMap<String,By> locatorMap = new TreeMap<String, By>();
			
	public static void readAllLocators () throws IOException{
		XSSFWorkbook locatorWorkbook = new XSSFWorkbook("C:\\Users\\mohit_vora\\Desktop\\Selenium\\Workspace\\Exercise\\resources\\AMap_Exercise.xlsx");
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