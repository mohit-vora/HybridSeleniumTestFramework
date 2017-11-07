package tryOutsGoHere;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Maptryout2 {
	
	public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("T-shirt",3);
        map.put("Shirt", 5);
        map.put("Kurthi", 2);
        map.put("Shirt", 2);
        System.out.println(map);
    }
}
