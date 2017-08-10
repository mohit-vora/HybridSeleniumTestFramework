package TestCases;

import org.testng.annotations.Test;
import PageObjects.Login;
import PageObjects.VerifyPopUp;
import Utils.BrowserUtils;
import Utils.LeftNavigationPane;


import org.testng.annotations.BeforeSuite;

import org.testng.annotations.BeforeTest;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;


public class EnrollMember {
private static final String PopUpMsg = null;
WebDriver driver = null;	
BrowserUtils bu = new BrowserUtils(); 
VerifyPopUp popup = new VerifyPopUp();
Login dd=new Login();


  @Test
  public void Register() throws Exception {
	  PageObjects.EnrollMember Em = new PageObjects.EnrollMember();
	  Em.RegisterMember(driver);
  }
  @BeforeTest
  public void beforeMethod() throws Exception {	 
	  dd.performLogin(driver);
  }  
  @BeforeSuite
  public void Browser(){	  
	  this.driver = bu.openbrowserChrome();
	
  }

  @Test(dependsOnMethods={"Register"})
  public void PopUpMethod() {
	 popup.PopUpAccept(driver);
  }
  
  @Test(dependsOnMethods={"PopUpMethod"})
  public void leftNavigate(){
	  LeftNavigationPane leftpane = new LeftNavigationPane();
	  leftpane.NavigateTo(driver, "Logout", "");
  }
  @Test(dependsOnMethods={"leftNavigate"})
  public void PopUpMethod1() {	  
	 popup.PopUpAccept(driver);
	 }
  @Test (dependsOnMethods={"PopUpMethod1"})
  public void Login() throws Exception{
	  dd.performLogin(driver);	  
  }
  @AfterSuite()
  public void CloseBrowser() throws InterruptedException{
		  bu.Closebrowser();
		  System.out.println("inside aftersuit method");
	  }
  }

