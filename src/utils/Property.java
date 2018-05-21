package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property extends BaseClass{
	/*read the path of Application map,datamap,Driver setup file*/
	public static String getValueOf(String parm) {

		File filePath = new File(System.getProperty("user.dir") + "\\TestResources\\path.properties");
  	
		FileInputStream fileInput = null;
		try {
		    fileInput = new FileInputStream(filePath);
		    ReportLogger.info("Traced Location of Path file");
		} 
		catch (FileNotFoundException fileNotFoundException) {
			  ReportLogger.preExecutionFail(fileNotFoundException);
		}
		
		Properties properties = new Properties();
		
		try {
			properties.load(fileInput);
		    ReportLogger.info("Loaded the path file");
		} 
		catch (IOException ioException) {
			ReportLogger.preExecutionFail(ioException);
		}
		
		return properties.getProperty(parm.toLowerCase());
	}
}
