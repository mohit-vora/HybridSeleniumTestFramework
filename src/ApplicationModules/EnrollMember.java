package ApplicationModules;

import org.testng.annotations.Test;
import PageObjects.Driverdemo;
import PageObjects.VerifyPopUp;
import Utils.BrowserUtils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
<<<<<<< HEAD
import org.testng.annotations.BeforeTest;
import org.apache.james.mime4j.message.Message;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
=======
>>>>>>> 1993f27ae47b7cb6392532e9fe938c8e7d60d68d
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;


public class EnrollMember {
private static final String PopUpMsg = null;
WebDriver driver = null;	
BrowserUtils bu = new BrowserUtils(); 
VerifyPopUp popup = new VerifyPopUp();
Driverdemo dd=new Driverdemo();
  @Test
  public void Register() throws Exception {
	  PageObjects.EnrollMember Em = new PageObjects.EnrollMember();
	  Em.RegisterMember(driver);
  }
  @BeforeTest
  public void beforeMethod() throws Exception {
	 
	  dd.Login(driver);
  }
  
  @BeforeSuite
  public void Browser(){
	  //BrowserUtils bu = new BrowserUtils();-----
	  this.driver = bu.openbrowserChrome();
	//  System.out.println("inside before method");
  }

  @Test(dependsOnMethods={"Register"})
  public void PopUpMethod() {
//	 popup.PopUpAccept(driver);
	// System.out.println(Msg);
	 }

  
  @Test(dependsOnMethods={"PopUpMethod"})
  public void leftNavigate(){
	  LeftNavigationPane leftpane = new LeftNavigationPane();
	  leftpane.NavigateTo(driver, "Logout", "");
  }
  @Test(dependsOnMethods={"leftNavigate"})
  public void PopUpMethod1() {	  
	 popup.PopUpAccept(driver);
//	 System.out.println(Msg);
	 }
  @Test (dependsOnMethods={"PopUpMethod1"})
  public void Login() throws Exception{
	  dd.Login(driver);	  
  }
  @AfterSuite()
  public void CloseBrowser() throws InterruptedException{
		  bu.Closebrowser();
		  System.out.println("inside aftersuit method");
	  }
  }

