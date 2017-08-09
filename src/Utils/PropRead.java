package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropRead {
	
	public PropRead(String parm) throws IOException
	{
		File file = new File(System.getProperty("user.dir")+"\\resources\\path.properties");
		  
		FileInputStream fileInput = new FileInputStream(file);
	
		Properties prop = new Properties();

		prop.load(fileInput);
	
		parm = prop.getProperty(parm);
	}

}
