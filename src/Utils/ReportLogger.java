package Utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ReportLogger extends BaseClass{

	
	
	 public static void info(String logMessage)
	    {
	    	test.log(Status.INFO, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.GREY));
	    	
	    }
	 public static void info(Exception e,String logMessage)
	    {
		 test.log(Status.INFO, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.GREY));
	    	test.log(Status.INFO, 
	        		e);
	    	
	    }
	 
	 public static void pass(String logMessage)
	    {
	    	test.log(Status.PASS, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.GREEN));
	    }
	 
	 public static void resultPass(String logMessage)
	    {
	    	test.log(Status.PASS, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.PURPLE));
	    }
	 
	 public static void fail(String logMessage)
	    {
	    	test.log(Status.FAIL, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.ORANGE));
	    }
	 public static void preExecutionFail(Exception exception)
	    {
	    	test.log(Status.FAIL, 
	        		exception);
	    }
	 
	 public static void fatal(String logMessage)
	    {
	    	test.log(Status.FATAL, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.RED));
	    }
	 
	 public static void skip(String logMessage)
	    {
	    	test.log(Status.SKIP, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.LIME));
	    }
	 public static void warn(String logMessage)
	    {
	    	test.log(Status.WARNING, 
	        		MarkupHelper.createLabel(logMessage,
	        				ExtentColor.CYAN));
	    }
}
