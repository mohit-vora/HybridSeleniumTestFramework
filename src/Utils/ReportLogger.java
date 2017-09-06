package Utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ReportLogger extends BaseClass{

	
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
	 /*This method logs a Pass Status with a message to the Extent report*/
	 public static void resultPass(String logMessage)
	    {
	    	test.log(Status.PASS, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.PURPLE));
	    }
	 /*This method logs a fail Status with a message to the Extent report*/
	 public static void fail(String logMessage)
	    {
	    	test.log(Status.FAIL, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.ORANGE));
	    }
	 /*This method logs fail status whenever there is an exception in the execution setup methods of the framework with its exception trace to the Extent report*/
	 public static void preExecutionFail(Exception exception)
	    {
	    	test.log(Status.FAIL, 
	        		exception);
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
	 /*This method logs warning with a message to the Extent report*/
	 public static void warn(String logMessage)
	    {
	    	test.log(Status.INFO, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.CYAN));
	    }
}
