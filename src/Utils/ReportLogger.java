package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportLogger extends BaseClass{
	

	static void createExtentInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Test Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Cyclos Test Run");
        
        extent = new ExtentReports();
        extent.setSystemInfo("OS", System.getProperty("os.name")+" "+System.getProperty("os.arch"));
        try {
			extent.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			ReportLogger.fatal("Host name could not be found: "+e);
		}
        extent.setSystemInfo("Environment", System.getProperty("java.runtime.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));

        extent.attachReporter(htmlReporter);
        

    }
	
	/*This method logs a particular information with a message to the Extent report*/
	 public static void info(String logMessage)
	    {
	    	test.log(Status.INFO, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.GREY));
	    	
	    }
	 /*This method logs a particular information with a message and its Exception stack trace to the Extent report*/
	 public static void info(Exception e,String logMessage)
	    {
		 test.log(Status.INFO, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.GREY));
	    	test.log(Status.INFO, 
	        		e);
	    	
	    }
	 /*This method logs a Pass Status with a message to the Extent report*/
	 public static void pass(String logMessage)
	    {
	    	test.log(Status.PASS, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.GREEN));
	    }

	 /*This method logs a fail Status with a message to the Extent report*/
	 public static void fail(String logMessage)
	    {
	    	test.log(Status.FAIL, MarkupHelper.createLabel(logMessage, ExtentColor.RED));
	    }
	 
	 /*This method logs a fail Status with a message to the Extent report*/
	 public static void fail(String logMessage, Throwable t)
	    {
		 test.log(Status.FAIL, MarkupHelper.createLabel(logMessage, ExtentColor.RED));
		 test.fail(t);
	    }
	 
	 /*This method logs fail status whenever there is an exception in the execution setup methods of the framework with its exception trace to the Extent report*/
	 public static void preExecutionFail(String errorMsg)
	    {
		 test.log(Status.FATAL, MarkupHelper.createLabel(errorMsg, ExtentColor.RED));
	    }
	 
	 /*This method logs fail status whenever there is an exception in the execution setup methods of the framework with its exception trace to the Extent report*/
	 public static void preExecutionFail(Exception exception)
	    {
		 test.log(Status.FAIL, MarkupHelper.createLabel("Pre-Execution Exception", ExtentColor.RED));
		 
		 test.log(Status.FATAL, exception);
	    }
	 
	 /*This method logs fatal status when there is intervention in the execution with a message to the Extent report*/
	 public static void fatal(String logMessage)
	    {
	    	test.log(Status.FATAL, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.RED));
	    }
	 
	 /*This method logs skip status along with a message to the Extent report and skips the further execution of the testcase*/
	 public static void skip(String logMessage)
	    {
	    	test.log(Status.SKIP, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.TEAL));
	    }
	 
	 /*This method logs skip status along with a message to the Extent report and skips the further execution of the testcase*/
	 public static void skip(String logMessage, Throwable t)
	    {
	    	test.log(Status.SKIP, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.TEAL));
	    	test.skip(t);
	    }
	 
	 /*This method logs warning with a message to the Extent report*/
	 public static void warn(String logMessage)
	    {
	    	test.log(Status.INFO, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.CYAN));
	    }
}
