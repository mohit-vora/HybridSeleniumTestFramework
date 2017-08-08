package ApplicationModules;

import org.testng.annotations.Test;

import PageObjects.Driverdemo;
import Utils.BrowserUtils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

public class Login {
WebDriver driver = null;
  @Test
  public void mainTest() throws Exception{
	  Driverdemo dd=new Driverdemo();
	  dd.Login(driver);
  }
  @BeforeMethod
  public void browser(){
		BrowserUtils bu = new BrowserUtils();
		this.driver = bu.openbrowserChrome();
		//System.out.println("inside before method");
	}

}
