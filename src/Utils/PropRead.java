package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropRead {

    public static String getVal(String parm) {
//        File file = new File(System.getProperty("user.dir") + "\\resources\\path.properties");

    	File file = new File(System.getProperty("user.dir") + "\\Resources\\path.properties");
    	
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Properties prop = new Properties();

        try {
            prop.load(fileInput);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return prop.getProperty(parm);
    }

}