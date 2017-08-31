package DataMap;

import java.io.IOException;

public class ReadData extends Utils.BaseClass{    

    public ReadData(String name, String id) throws IOException {
        DSName = name;
        this.id = id;
        
    }

    public String getData(String col) {
 
        return getdata(col);
    }

    

}