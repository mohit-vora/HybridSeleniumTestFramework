package utils;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseClass {
    static int testIterationNumber = 0;
    static ExtentTest test;
    static int testCaseCount = 0;
	protected static WebDriver driver = null;
    protected static boolean preExecutionCheck = true;
    protected static boolean isLoggedIn = false;
    static ExtentReports extent;	
}
