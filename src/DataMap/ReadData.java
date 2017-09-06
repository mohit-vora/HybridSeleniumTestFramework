package DataMap;

import java.util.ArrayList;
import org.testng.Assert;
import Utils.BaseClass;
import Utils.ReportLogger;

public class ReadData extends BaseClass{    
/*Parameterized constructor which initializes the datasheet name and the data id and fetches the corresponding data*/
    public ReadData(String name, String id) {
        dSName = name;
        this.id = id;
        
        ArrayList<String> list = new ArrayList<String>();
        list = testSpecificData.get(dSName.toLowerCase()).get(id.toLowerCase());

    	if (list==null){
    		ReportLogger.fatal(id +" did not match any id in "+ dSName.toLowerCase());
    		
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
    /*This method invokes getdata from */
    public String getData(String col) {
 
        return getColumnData(col.toLowerCase());
    }

    

}