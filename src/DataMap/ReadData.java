package DataMap;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;

import Utils.BaseClass;
import Utils.ReportLogger;

public class ReadData extends BaseClass{    

    public ReadData(String name, String id) throws IOException {
        dSName = name;
        this.id = id;
        
        ArrayList<String> list = new ArrayList<String>();
        list = testSpecificData.get(dSName).get(id);

    	if (list==null){
    		ReportLogger.fatal(id +" did not match any id in "+ dSName);
    	} 	
        try{
        	for (String element:list){
            	if (!(element.length()>0)){
            		ReportLogger.warn(id +" has empty data columns. Please check DataMap sheet: "+ dSName);
            		break;
            	}
            } 	
        }
        catch (NullPointerException e){
        	Assert.fail("Check above warnings. Problem in: "+ dSName);
        }
            
        
    }

    public String getData(String col) {
 
        return getdata(col);
    }

    

}