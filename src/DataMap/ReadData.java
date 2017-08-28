package DataMap;

import java.io.IOException;
import Interface.CommonInterface;

public class ReadData extends CommonInterface{

    

    public ReadData(String name, String id) throws IOException {
        DSName = name;
        this.id = id;
        
    }

    public String getData(String col) {
 
        return getdata(col);
    }

    

}