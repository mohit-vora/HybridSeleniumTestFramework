package TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import PageObjects.Login;
import PageObjects.VerifyPopUp;
import Utils.BrowserUtils;
import Utils.dummy;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;


public class EnrollMember {
private static final String PopUpMsg = null;
WebDriver driver = null;	
BrowserUtils bu = new BrowserUtils(); 
VerifyPopUp popup = new VerifyPopUp();

@DataProvider(name="EnrollMember")
public static Object[][] getRegData() {
	 
    return new Object[][] { { "MEM001"},{"MEM001"}};

}

  @Test(dataProvider="EnrollMember")
  public void Register(String dsid) throws Exception {
	  PageObjects.EnrollMember Em = new PageObjects.EnrollMember();
	  Em.RegisterMember(driver,dsid);
  }
  
  
  @BeforeMethod
  public void beforeMethod() throws Exception {
	  Login dd=new Login();
	  dd.performLogin(driver);

  }
  
  @BeforeSuite
  public void Browser(){
	  //BrowserUtils bu = new BrowserUtils();-----
	  this.driver = bu.openbrowserChrome();
	  System.out.println("inside before method");
  }

  @AfterMethod
  public void PopUpMethod() {
	 popup.PopUpAccept(driver);
	 this.driver.findElement(By.cssSelector("#menu15 > span.menuText")).click();
	 popup.PopUpAccept(driver);

	 }

/*  @Test(dependsOnMethods={"PopUpMethod"})
  public void leftNavigate(){
	  LeftNavigationPane leftpane = new LeftNavigationPane();
	  leftpane.NavigateTo(driver, "Logout", "");
  }
  @Test(dependsOnMethods={"leftNavigate"})
  public void PopUpMethod1() {
	 popup.PopUpAccept(driver);
//	 System.out.println(Msg);
	 }*/
  @AfterSuite()
  public void CloseBrowser() throws InterruptedException{
		  bu.Closebrowser();
		  System.out.println("inside aftersuit method");
	  }
  }

