package ApplicationModules;

import org.testng.annotations.Test;
import PageObjects.Driverdemo;
import PageObjects.VerifyPopUp;
import Utils.BrowserUtils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.apache.james.mime4j.message.Message;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;


public class EnrollMember {
private static final String PopUpMsg = null;
WebDriver driver = null;	
BrowserUtils bu = new BrowserUtils(); 
VerifyPopUp popup = new VerifyPopUp();
  @Test
  public void Register() throws Exception {
	  PageObjects.EnrollMember Em = new PageObjects.EnrollMember();
	  Em.RegisterMember(driver);
  }
  @BeforeMethod
  public void beforeMethod() throws Exception {
	  Driverdemo dd=new Driverdemo();
	  dd.Login(driver);
  }
  
  @BeforeSuite
  public void Browser(){
	  //BrowserUtils bu = new BrowserUtils();
	  this.driver = bu.openbrowserChrome();
	  System.out.println("inside before method");
  }

  @AfterMethod
  public void afterMethod() {
	 String Msg = popup.PopUpAccept(driver,PopUpMsg);
	 System.out.println(Msg);
	
	  
  }

  @AfterSuite()
  public void CloseBrowser(){
		  this.driver = bu.Closebrowser();
		  System.out.println("inside before method");
	  }
  }

